package com.wzx.springbootblog.web.controller.back;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back/")
public class BackIndexController {

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@PreAuthorize("hasAuthority('报告')")
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@PreAuthorize("hasAuthority('报告')")
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
}
