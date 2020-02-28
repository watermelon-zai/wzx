package com.wzx.springbootblog.web.controller.back;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.UserService;
import com.wzx.springbootblog.utils.Const;
import com.wzx.springbootblog.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.util.List;


/**
 * 用户管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/user/")
public class UserInfoController {
	@Autowired
	private UserService userService;


	/**
	 * 无条件分页查询用户信息
	 * @param user
	 * @param model
	 * @param page 
	 * @return
	 */
	@PreAuthorize("hasAuthority('用户管理')")
	@GetMapping("/list")
	public String list(Model model, Integer page) {
		if(page==null){
			page=1;
		}
		PageInfo<UserInfo> pageBean=userService.findPageUserList(page, Const.PAGE_SIZE);
		model.addAttribute("pageBean", pageBean);
		List<UserInfo> userlist = pageBean.getList();
		model.addAttribute("userinfo", userlist);
		return "userinfo/userinfo_list";
	}

	/**
	 * 有条件的分页查询
	 * @param user
	 * @param model
	 * @param page
	 * @return
	 */
	@PreAuthorize("hasAuthority('用户管理')")
	@PostMapping("/list")
	public String findUserByName(UserInfo user, Model model, Integer page) {
		if(page==null){
			page=1;
		}
		PageInfo<UserInfo> pageBean=userService.findPageUserListCandition(user,page,Const.PAGE_SIZE);
		model.addAttribute("pageBean", pageBean);
		List<UserInfo> userlist = pageBean.getList();
		model.addAttribute("userinfo", userlist);

		return "userinfo/userinfo_list";
	}


	/**
	 * 跳转到添加用户页面
	 * @return
	 */
	@GetMapping("/add")
	public String add() {
		return "userinfo/userinfo_add";
	}

	/**
	 * 提交添加的用户的信息
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/add")
	public String add(UserInfo user, Model model) {
           int i = this.userService.addUser(user);
           if (i>0)
           model.addAttribute("mess","添加成功");
           else
           	model.addAttribute("mess","添加失败");
		return "userinfo/userinfo_add";
	}

	/**
	 * 根据用户id跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/update/{id}")
	public String loadUpdate(@PathVariable Integer id,Model model){
		UserInfo userInfo = this.userService.selectOneUser(id);
		System.out.println(userInfo.getUserName());
		model.addAttribute("user",userInfo);
		return "userinfo/userinfo_update";
	}

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@PostMapping("update")
	public String update(UserInfo userInfo,Model model){
		int i = this.userService.updateUserInfo(userInfo);
		if (i==1){
			model.addAttribute("mess","修改成功！！");
			//将修改成功后的用户信息显示到页面上
			this.userService.selectOneUser(userInfo.getUserId());
			model.addAttribute("user",userInfo);
			return "userinfo/userinfo_update";

		}else {
			model.addAttribute("mess", "修改失败！！");
			this.userService.selectOneUser(userInfo.getUserId());
			model.addAttribute("user", userInfo);
			return "userinfo/userinfo_update";
		}
	}

	/**
	 * 根据用户id删除用户 逻辑删除 即将用户的状态修改成 0
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
	@ResponseBody
	public String deleteUser( Integer id){
		this.userService.deleteUser(id);
		return "删除成功~";
	}


}
