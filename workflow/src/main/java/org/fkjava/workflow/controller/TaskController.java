package org.fkjava.workflow.controller;

import java.util.Map;

import org.fkjava.common.data.domain.Result;
import org.fkjava.workflow.domain.BusinessData;
import org.fkjava.workflow.service.WorkflowService;
import org.fkjava.workflow.vo.TaskForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/workflow/task")
public class TaskController {

	private Logger log = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private WorkflowService workflowService;
	@Autowired // Spring Boot已经有objectMapper
	private ObjectMapper objectMapper;

	@GetMapping
	public ModelAndView index(//
			@RequestParam(name = "keyword", required = false) String keyword, //
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {

		ModelAndView mav = new ModelAndView("workflow/task/index");
		Page<TaskForm> page = this.workflowService.findTasks(keyword, null, pageNumber);
		mav.addObject("page", page);

		return mav;
	}

	@GetMapping("{id}")
	public ModelAndView details(@PathVariable("id") String taskId) {

		ModelAndView mav = new ModelAndView("workflow/task/details");
		TaskForm form = this.workflowService.getTaskForm(taskId);
		mav.addObject("form", form);

		// 把业务数据转换为JSON，然后返回给JSP
		BusinessData data = form.getData();
		String json;
		try {
			json = objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			log.trace("无法把业务数据转换为JSON：" + e.getLocalizedMessage(), e);
			// 出现异常，直接返回一个空的JSON对象，避免JS语法报错
			json = "{}";
		}
		mav.addObject("json", json);

		return mav;
	}

	@PostMapping("{id}")
	public ModelAndView complete(@PathVariable("id") String taskId, WebRequest request) {

		ModelAndView mav;

		Map<String, String[]> params = request.getParameterMap();
		try {
			this.workflowService.complete(taskId, params);
			// 正常完成。重定向到列表页面
			mav = new ModelAndView("redirect:/workflow/task");
		} catch (Exception e) {
			log.warn("处理任务失败：" + e.getLocalizedMessage(), e);
			// 出现问题，则回到原本的页面，并提供一个result用于提示
			mav = this.details(taskId);

			Result result = Result.error("任务处理失败：" + e.getLocalizedMessage());
			mav.addObject("result", result);
		}

		return mav;
	}
}
