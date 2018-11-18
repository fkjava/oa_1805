package org.fkjava.cb.controller;

import java.util.List;

import org.fkjava.cb.domain.Notice;
import org.fkjava.cb.domain.NoticeType;
import org.fkjava.cb.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping
	public ModelAndView index(//
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer number, //
			@RequestParam(name = "keyword", required = false) String keyword) {
		ModelAndView mav = new ModelAndView("notice/index");

		Page<Notice> page = this.noticeService.findNotices(number, keyword);
		mav.addObject("page", page);

		return mav;
	}

	@GetMapping("add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("notice/add");
		List<NoticeType> types = noticeService.findAllTypes();
		mav.addObject("types", types);
		return mav;
	}

	@PostMapping()
	public ModelAndView save(Notice notice) {
		// 保存以后，重定向到列表
		ModelAndView mav = new ModelAndView("redirect:/notice");
		this.noticeService.write(notice);
		return mav;
	}
}
