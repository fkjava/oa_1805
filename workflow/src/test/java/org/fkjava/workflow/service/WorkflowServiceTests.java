package org.fkjava.workflow.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.activiti.engine.repository.ProcessDefinition;
import org.fkjava.common.data.domain.Result;
import org.fkjava.workflow.WorkflowConfig;
import org.fkjava.workflow.vo.ProcessForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { WorkflowConfig.class })
public class WorkflowServiceTests
//extends AbstractJUnit4SpringContextTests 
		extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private WorkflowService workflowService;
	private String processDefinitionId;

	// 把准备数据的代码，放入Before里面，这样保证每个@Test都有一份新的数据
	@Before
	public void testDeploySuccess() throws IOException, URISyntaxException {
		String name = "HelloWorld";

		// 把测试文件压缩起来，方便测试
		// 可以直接导出文件，生成zip文件，也可以直接在内存里面压缩
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (ZipOutputStream out = new ZipOutputStream(outputStream);) {
			this.addFile(out, name + ".bpmn");
			this.addFile(out, name + ".png");
		}

		try (InputStream in = new ByteArrayInputStream(outputStream.toByteArray())) {
			Result result = this.workflowService.deploy(name, in);
			Assert.assertEquals(Result.CODE_OK, result.getCode());
		}

		ProcessForm form = this.workflowService.findDefnitionByKey(name);
		ProcessDefinition definition = form.getDefinition();
		if (definition != null) {
			processDefinitionId = definition.getId();
		}
	}

	private void addFile(ZipOutputStream out, String name) throws IOException, URISyntaxException {
		// 写入一个文件的标志
		ZipEntry bpmn = new ZipEntry(name);
		out.putNextEntry(bpmn);
		// 写入内容
		URL bpmnUrl = this.getClass().getResource("/diagrams/" + name);
		File bpmnFile = new File(bpmnUrl.toURI());
		Files.copy(bpmnFile.toPath(), out);
	}

	@Test
	public void testFindDefinitions() {
		// 测试流程定义查询
		String keyword = null;
		int pageNumber = 0;// 第一页
		Page<ProcessDefinition> page = this.workflowService.findDefinitions(keyword, pageNumber);
		Assert.assertNotNull("必须要求返回一页数据", page);
		Assert.assertEquals("预期要有数据，总记录数要大于0", true, page.getTotalElements() > 0);
	}

	@Test
	public void disable() {

		// 禁用流程定义，要求传入一个ID
		// 直接查询一个流程定义来进行模拟，写入@Before方法里面
		// String processDefinitionId = "";

		// 执行禁用
		this.workflowService.disableProcessDefinition(processDefinitionId);

		// 查询流程定义，检查是否禁用成功
		ProcessDefinition definition = this.workflowService.findDefnitionById(processDefinitionId);
		Assert.assertNotNull(definition);
		Assert.assertEquals(true, definition.isSuspended());

		// 执行激活
		this.workflowService.activeProcessDefinition(processDefinitionId);

		// 查询流程定义，检查是否激活成功
		definition = this.workflowService.findDefnitionById(processDefinitionId);
		Assert.assertNotNull(definition);
		Assert.assertEquals(false, definition.isSuspended());
	}

	@Test
	public void start() {

		String key = "HelloWorld";
		// 把表单显示在页面，然后页面填写完成以后，提交到控制器。有控制器启动调用业务逻辑启动实例
		// 一个流程定义，有很多的流程实例。
		ProcessForm form = this.workflowService.findDefnitionByKey(key);
		Assert.assertNotNull(form);

		// 启动流程实例
		// 用户填写的数据，没有流程都不同，所以此时只能获取所有的请求参数，交给业务逻辑层的统一代码去处理
		// request.getParameterMap()返回一个Map，现在这里模拟一个。
		Map<String, String[]> params = new HashMap<>();

		// 根据流程定义的ID来启动流程实例
		String processDefinitionId = form.getDefinition().getId();
		System.out.println("开始启动流程实例");
		Result result = this.workflowService.start(processDefinitionId, params);
		System.out.println("流程实例启动结束");
		Assert.assertNotNull(result);
		Assert.assertEquals(Result.CODE_OK, result.getCode());
	}
}
