package org.fkjava.workflow.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.fkjava.common.data.domain.Result;
import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.fkjava.identity.service.IdentityService;
import org.fkjava.workflow.service.WorkflowService;
import org.fkjava.workflow.vo.ProcessForm;
import org.fkjava.workflow.vo.TaskForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	private Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private FormService formService;
	@Autowired
	private RuntimeService runtimeService;
	// 待办任务服务
	@Autowired
	private TaskService taskService;
	// 历史服务
	@Autowired
	private HistoryService historyService;
	// 我们自己系统里面的用户权限服务，不使用流程引擎的
	@Autowired
	private IdentityService identityService;

	@Override
	public Result deploy(String name, InputStream in) {
		// 解压缩，里面会包含bpmn、png，甚至有一些其他的文件
		// 这些文件，全部打包成ZIP格式压缩文件，一并上传。
		try (ZipInputStream zipInputStream = new ZipInputStream(in)) {

			DeploymentBuilder builder = this.repositoryService.createDeployment();// 创建部署构建器

			builder.name(name);
			builder.addZipInputStream(zipInputStream);

			// 执行部署
			builder.deploy();

			return Result.ok("流程定义部署成功");

		} catch (IOException e) {
			log.error("流程定义部署失败： " + e.getLocalizedMessage(), e);
			return Result.error("流程定义部署失败：" + e.getLocalizedMessage());
		}
	}

	@Override
	public Page<ProcessDefinition> findDefinitions(String keyword, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 10);// 每页读取10条数据

		// 虽然Page和Pageable是Spring Data里面，但是Activiti没有Spring Data的支持，需要自己查询数据
		// 1.创建流程定义查询对象
		ProcessDefinitionQuery query = this.repositoryService.createProcessDefinitionQuery();
		// 2.设置查询条件
		if (StringUtils.isEmpty(keyword)) {
			// 没有关键字查询
		} else {
			// 有关键字查询
			keyword = "%" + keyword + "%";
			query.processDefinitionNameLike(keyword);
		}
		// 只查询最后一个版本的流程定义（每个KEY对应的最后一个版本的流程定义）
		query.latestVersion();
		// 3.设置排序条件
		query.orderByProcessDefinitionKey().asc();

		// 4.查询总记录数
		long totalRows = query.count();

		// 5.查询一页的数据
		List<ProcessDefinition> content = query.listPage((int) pageable.getOffset(), pageable.getPageSize());

		// 6.构建Page对象
		Page<ProcessDefinition> page = new PageImpl<>(content, pageable, totalRows);
		return page;
	}

	@Override
	public void disableProcessDefinition(String processDefinitionId) {
		this.repositoryService.suspendProcessDefinitionById(processDefinitionId);
	}

	@Override
	public void activeProcessDefinition(String processDefinitionId) {
		this.repositoryService.activateProcessDefinitionById(processDefinitionId);
	}

	@Override
	public ProcessDefinition findDefnitionById(String processDefinitionId) {
		return this.repositoryService.getProcessDefinition(processDefinitionId);
	}

	@Override
	public ProcessForm findDefnitionByKey(String key) {
		ProcessDefinitionQuery query = this.repositoryService.createProcessDefinitionQuery();
		query.latestVersion();// 查询最后一个版本
		query.processDefinitionKey(key);// 以流程定义的KEY查询数据
		ProcessDefinition definition = query.singleResult();

		// 表单内容
		Object content;
		try {
			content = this.formService.getRenderedStartForm(definition.getId());
		} catch (Exception e) {
			// 出现异常，表示没有表单！
			content = null;
		}

		// 表单数据
		FormData formData = this.formService.getStartFormData(definition.getId());

		// 表单名称
		String formKey = this.formService.getStartFormKey(definition.getId());

		// 表单相关的处理，都是在FormService里面的
		ProcessForm form = new ProcessForm();
		form.setContent(content);
		form.setDefinition(definition);
		form.setFormData(formData);
		form.setFormKey(formKey);
		return form;
	}

	@Override
	public Result start(String processDefinitionId, Map<String, String[]> params) {
		// 需要使用RuntimeService来启动实例

		// 1.把请求参数整理一下，如果值只是一个String，那么为了方便就不需要String[]
		Map<String, Object> variables = new HashMap<>();
		params.forEach((key, value) -> {
			if (value.length == 1) {
				// 只有一个值
				variables.put(key, value[0]);
			} else {
				// 有多个值
				variables.put(key, value);
			}
		});
		// remove方法是把key对应的键值对删除，返回key对应的值
		Object tmp = variables.remove("remark");
		String remark = tmp != null ? tmp.toString() : null;

		// 2.根据id查询流程定义，检查流程定义是否存在、是否被停用。
		ProcessDefinition definition = this.findDefnitionById(processDefinitionId);
		if (definition == null) {
			return Result.error("非法请求，流程定义未找到");
		}
		if (definition.isSuspended()) {
			return Result.error("非法请求，流程定义已经被暂停使用");
		}

		// 3.统一保存、更新业务数据
		// 保存业务数据以后，把业务数据的主键的值返回，用于关联流程实例和业务数据
		String businessKey = saveBusinessData(definition, variables);

		// 4.启动流程实例
		// variables: 如果要在流程里面判断流程的走向，经常需要传入一些参数。通常都是通过Map传入的。
		ProcessInstance instance = this.runtimeService//
				.startProcessInstanceById(processDefinitionId, businessKey, variables);

		// 5.记录流程跟踪信息，方便查看每个步骤谁做了什么事情
		saveProcessTrace(definition, instance, null, remark);

		return Result.ok("流程实例启动成功");
	}

	private String saveBusinessData(ProcessDefinition definition, Map<String, Object> variables) {
		// TODO 暂时不保存业务数据
		return null;
	}

	private void saveProcessTrace(ProcessDefinition definition, ProcessInstance instance, Task task, String remark) {
		// TODO 暂时不保存流程跟踪信息，因为需要一个自定义的表来存储
	}

	@Override
	public Page<TaskForm> findTasks(String keyword, String processInstanceId, int pageNumber) {
		User user = UserHolder.get();
		Pageable pageable = PageRequest.of(pageNumber, 10);

		// 1.根据当前用户查询待办任务列表，并且分页查询
		TaskQuery query = this.taskService.createTaskQuery()//
				.orderByTaskCreateTime().desc()// 按时间倒序，最新收到放前面
		;
		if (user != null) {
			query.taskAssignee(user.getId());// 查询当前用户的待办任务
		}
		if (!StringUtils.isEmpty(keyword)) {
			keyword = "%" + keyword + "%";
			query.taskNameLike(keyword);// 有关键词则使用任务的名称来模糊匹配
		}
		if (!StringUtils.isEmpty(processInstanceId)) {
			// 如果有传入流程实例的ID，则根据流程实例查询待办
			query.processInstanceId(processInstanceId);
		}
		// 查询总数
		long total = query.count();
		// 查询一页数据
		List<Task> taskList = query.listPage((int) pageable.getOffset(), pageable.getPageSize());
		// 2.根据得到的任务列表，需要把结果封装成TaskForm对象
		List<TaskForm> content = new LinkedList<>();
		taskList.forEach(task -> {
			TaskForm tf = this.convert2TaskForm(task);
			content.add(tf);
		});
		Page<TaskForm> page = new PageImpl<>(content, pageable, total);
		return page;
	}

	private TaskForm convert2TaskForm(Task task) {
		TaskForm tf = new TaskForm();
		tf.setTask(task);
		// 3.查询任务对应的流程实例
		HistoricProcessInstance instance = this.historyService.createHistoricProcessInstanceQuery()//
				.processInstanceId(task.getProcessInstanceId())//
				.singleResult();
		tf.setInstance(instance);

		// 4.查询流程实例的创始人
		// 默认是没有StartUserId的，必须要在启动流程实例之前，把当前用户告诉流程引擎
		// 通常是增加一个拦截器，再调用Activiti里面的一个静态方法来设置
		// Authentication.setAuthenticatedUserId(authenticatedUserId);
		User initialUser = this.identityService.findUserById(instance.getStartUserId());
		tf.setInitialUser(initialUser);
		// 5.查询任务对应的流程定义
		ProcessDefinition definition = this.findDefnitionById(task.getProcessDefinitionId());
		tf.setDefinition(definition);
		return tf;
	}

	@Override
	public TaskForm getTaskForm(String taskId) {
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskForm tf = this.convert2TaskForm(task);

		// 表单内容
		Object content;
		try {
			content = this.formService.getRenderedTaskForm(taskId);
		} catch (Exception e) {
			// 出现异常，表示没有表单！
			content = null;
		}
		// 表单数据
		FormData formData = this.formService.getTaskFormData(taskId);
		// 表单名称
		String formKey = this.formService.getTaskFormKey(tf.getDefinition().getId(), task.getTaskDefinitionKey());

		tf.setContent(content);
		tf.setFormData(formData);
		tf.setFormKey(formKey);

		return tf;
	}

	@Override
	public void complete(String taskId, Map<String, String[]> params) {
		// 1.获取当前用户
		User user = UserHolder.get();
		// 2.整理请求参数，跟启动流程实例一样
		Map<String, Object> variables = new HashMap<>();
		params.forEach((key, value) -> {
			if (value.length == 1) {
				// 只有一个值
				variables.put(key, value[0]);
			} else {
				// 有多个值
				variables.put(key, value);
			}
		});
		// 3.获取remark参数的值，备注不需要传入流程引擎
		// remove方法是把key对应的键值对删除，返回key对应的值
		Object tmp = variables.remove("remark");
		String remark = tmp != null ? tmp.toString() : null;

		// 4.查询任务的实例，只要Task对象
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		// 5.判断处理人是否为当前用户，如果不是则应该抛出异常
		if (!task.getAssignee().equals(user.getId())) {
			throw new IllegalArgumentException("非法请求：当前用户和任务的处理人不同！");
		}
		// 6.查询流程定义
		ProcessDefinition definition = this.findDefnitionById(task.getProcessDefinitionId());
		// 7.查询流程实例
		ProcessInstance instance = this.runtimeService.createProcessInstanceQuery()//
				.processInstanceId(task.getProcessInstanceId())//
				.singleResult();
		// 8.完成任务，核心代码
		this.taskService.complete(taskId, variables);
		// 9.保存流程跟踪信息
		this.saveProcessTrace(definition, instance, task, remark);
	}
}
