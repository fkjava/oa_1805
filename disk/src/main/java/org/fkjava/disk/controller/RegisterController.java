package org.fkjava.disk.controller;

import org.fkjava.common.data.domain.Result;
import org.fkjava.disk.service.DiskService;
import org.fkjava.identity.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/disk/register")
public class RegisterController {

	@Autowired
	private DiskService diskService;

	@GetMapping
	public String reg() {
		return "disk/register";
	}

	@PostMapping()
	public ModelAndView register(User user) {
		ModelAndView mav = new ModelAndView("disk/prompt");
		this.reg(user);
		return mav;
	}

	@PostMapping(produces = "application/json")
	@ResponseBody
	public Result reg(User user) {

		Result result = Result.ok("注册成功，请登录后使用");
		this.diskService.register(user);
		return result;
	}
}
