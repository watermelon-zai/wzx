package com.wzx.springbootblog.web.controller.back;

import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/back")
public class BackIndexController {

	@Autowired
	private UserService userService;


	@GetMapping("login")
	public String login() {

		return "login2";
	}

	@GetMapping("regist")
	public String loadregist(UserInfo userInfo){
		return "regist";
	}

	@PostMapping("regist")
	@ResponseBody
	public String regist(UserInfo userInfo){
		//加密密码
		BCryptPasswordEncoder c = new BCryptPasswordEncoder();
		String encode = c.encode(userInfo.getUserPassword());
		userInfo.setUserPassword(encode);
		int i = this.userService.addUser(userInfo);
		if (i==1){
			return "注册成功";
		}else
		    return "注册失败";
	}

	@PreAuthorize("hasAuthority('后台首页')")
	@RequestMapping("index")
	public String index(Authentication authentication, Model model) {
		String name = authentication.getName();
		UserInfo user = this.userService.findUserByName(name);
		model.addAttribute("username",name);
		model.addAttribute("userinfo",user);
		return "index";
	}

	@PreAuthorize("hasAuthority('报告')")
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
}
