package org.fkjava.hr.controller;

import java.util.List;

import org.fkjava.hr.domain.Department;
import org.fkjava.hr.service.HumanResourceService;
import org.fkjava.identity.domain.User;
import org.fkjava.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/human-resource/department")
public class DepartmentController {

	@Autowired
	private IdentityService identityService;
	@Autowired
	private HumanResourceService humanResourceService;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("human-resource/department/index");

		// 模拟查询，后面要改为AJAX
		Page<User> userPage = this.identityService.findUsers(null, 0);
		List<User> users = userPage.getContent();
		mav.addObject("user", users);

		return mav;
	}

	// 如果客户端要求返回JSON的时候，调用下面这个方法
	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<Department> findTopDepartments() {
		return this.humanResourceService.findTopDepartments();
	}

	@PostMapping
	public String save(Department department) {
		this.humanResourceService.save(department);
		return "redirect:/human-resource/department";
	}
}
