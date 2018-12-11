package org.fkjava.hr.controller;

import java.util.List;

import org.fkjava.common.data.domain.Result;
import org.fkjava.hr.domain.LeaveType;
import org.fkjava.hr.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/human-resource/leave/type")
public class LeaveTypeController {

	@Autowired
	private LeaveService leaveService;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("human-resource/leave/type/index");

		List<LeaveType> types = this.leaveService.findAllTypes();
		mav.addObject("types", types);

		return mav;
	}

	@PostMapping
	public ModelAndView save(LeaveType type) {
		ModelAndView mav = new ModelAndView("redirect:/human-resource/leave/type");

		this.leaveService.save(type);

		return mav;
	}

	@DeleteMapping("{id}")
	@ResponseBody
	public Result delete(@PathVariable("id") String id) {
		return this.leaveService.deleteType(id);
	}

	@GetMapping("available")
	@ResponseBody
	public List<LeaveType> available() {
		return this.leaveService.findAllTypes();
	}
}
