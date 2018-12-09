package org.fkjava.workflow.controller;

import org.fkjava.workflow.service.WorkflowService;
import org.fkjava.workflow.vo.ProcessForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/workflow/instance")
public class InstanceController {

	@Autowired
	private WorkflowService workflowService;

	@GetMapping("{key}")
	public ModelAndView start(@PathVariable("key") String key) {
		ModelAndView mav = new ModelAndView("workflow/instance/start");

		ProcessForm form = this.workflowService.findDefnitionByKey(key);
		mav.addObject("form", form);
		return mav;
	}
}
