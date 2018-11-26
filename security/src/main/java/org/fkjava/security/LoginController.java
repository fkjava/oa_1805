package org.fkjava.security;

import org.fkjava.common.data.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// 登录失败
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@Controller
@RequestMapping("/security/login")
public class LoginController {

	@GetMapping
	public String htmlLogin() {
		return "security/login";
	}

	@GetMapping(produces = { "application/json", "text/javascript" })
	@ResponseBody
	public Result jsonLogin() {
		return Result.ok("用户未登录，请把用户导向到登录界面！");
	}
}
