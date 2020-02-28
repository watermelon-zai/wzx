package com.wzx.springbootblog.web.controller.back;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.MessageInfo;
import com.wzx.springbootblog.service.MessageService;
import com.wzx.springbootblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 留言管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/message/")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * 无条件的查询留言的分页信息
	 * @return
	 */
	@PreAuthorize("hasAuthority('留言管理')")
	@RequestMapping("list")
	public String list(Model model,Integer page) {
		if (page==null){
			page=1;
		}
		PageInfo<MessageInfo> allMessagePageList = this.messageService.findAllMessagePageList(page, Const.PAGE_SIZE);
		model.addAttribute("pageBean",allMessagePageList);
		return "message/message_list";
	}

	/**
	 * 根据留言姓名查询留言信息
	 * @param model
	 * @param page
	 * @return
	 */
	@PostMapping("list")
	public String find(Model model,Integer page,MessageInfo messageInfo) {
		if (page==null){
			page=1;
		}
		PageInfo<MessageInfo> allMessagePageList = this.messageService.findAllMessagePageListByName(page, Const.PAGE_SIZE,messageInfo.getMessageName());
		model.addAttribute("pageBean",allMessagePageList);
		return "message/message_list";
	}

	/**
	 * 删除留言信息
	 * @return
	 */
	@GetMapping("delete")
	@ResponseBody
	public String delete(Integer id) {
		boolean b = this.messageService.deleteMessge(id);
		if (b)
			return "删除成功！";
		else
			return "删除失败！！";
	}

	/**
	 *根据留言id批量删除留言信息
	 * @return
	 */
	@PostMapping("deleteList")
	@ResponseBody
	public String deleteList(MessageInfo messageInfo) {
		List<Integer> ids = Arrays.asList(messageInfo.getIds());
		boolean b = false;
		for (Integer id:ids) {

			 b = this.messageService.deleteMessge(id);
		}
		if (b)
			return "删除成功~";
		else
			return "删除失败~";
	}

	/**
	 * 显示留言信息 及修改信息的messageMark=1
	 * @return
	 */
	@RequestMapping("show")
	@ResponseBody
	public String show(Integer id) {
        this.messageService.updateMassMarkShow(id);
		return "修改成功，可以在文章下显示啦~~";
	}

	/**
	 * 显示留言信息 及修改信息的messageMark=0
	 * @return
	 */
	@RequestMapping("noshow")
	@ResponseBody
	public String noshow(Integer id) {
		this.messageService.updateMassMarkNoshow(id);
		return "修改成功，不会再文章下显示了~~";
	}


}
