package org.fkjava.workflow.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.fkjava.workflow.service.WorkflowService;
import org.fkjava.workflow.vo.ProcessForm;
import org.fkjava.workflow.vo.ProcessImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
@RequestMapping("/workflow/history/instance")
public class HistoryInstanceController {

	@Autowired
	private WorkflowService workflowService;

	@GetMapping()
	public ModelAndView index(//
			@RequestParam(name = "keyword", required = false) String keyword, //
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {

		ModelAndView mav = new ModelAndView("workflow/history/instance/index");

		Page<ProcessForm> page = workflowService.findInstances(keyword, pageNumber);
		mav.addObject("page", page);

		return mav;
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<StreamingResponseBody> image(@PathVariable("id") String id)
			throws UnsupportedEncodingException {
		ProcessImage image = this.workflowService.getTraceImage(id);
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.valueOf("image/png"));
		builder.contentLength(image.getContent().length);
		String name = URLEncoder.encode(image.getName(), "UTF-8");
		builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + name);

		StreamingResponseBody body = (out) -> {
			out.write(image.getContent());
		};

		return builder.body(body);
	}
}
