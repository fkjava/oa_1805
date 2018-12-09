package org.fkjava.identity.controller;

import org.fkjava.identity.UserHolder;
import org.fkjava.identity.domain.User;
import org.fkjava.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/identity/profile")
public class ProfileController {

	@Autowired
	private IdentityService identityService;

	@GetMapping(produces = "text/html")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("identity/profile/index");
		mav.addObject("user", this.info());
		return mav;
	}

	@GetMapping(produces = { "application/json", "application/xml", "text/xml" })
	@ResponseBody
	public User info() {
		User user = UserHolder.get();
		if (user != null) {
			String userId = user.getId();
			user = this.identityService.findUserById(userId);
		}
		return user;
	}
}
