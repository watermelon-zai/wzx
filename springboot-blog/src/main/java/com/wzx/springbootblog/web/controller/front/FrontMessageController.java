package com.wzx.springbootblog.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.MessageInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.service.MessageService;
import com.wzx.springbootblog.service.UserService;
import com.wzx.springbootblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
@Controller
@RequestMapping("/")
public class FrontMessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


    @ResponseBody
    @GetMapping("authenticate")
    public String authenticate(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //判断是否已经登录，如果没有登录，提示还未登录，不能收藏。
        if (name.equals("anonymousUser")) {
            return "你还没登录，先登录后才能评论哦~";
        }else
            return "ok";
    }

    @GetMapping("front/message")
    private String loadMessage(Model model,Integer page){
        if (page==null){
            page=1;
        }
        PageInfo<MessageInfo> allMessagePageList = this.messageService.findAllMessagePageListByShow(page, 3);
        model.addAttribute("pageBean",allMessagePageList);

        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        model.addAttribute("articleRecom",articleRecomList);
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);
        return "front/message";
    }

    @PostMapping("front/message")
    @ResponseBody
    private String addMessage(MessageInfo messageInfo){
        System.out.println("ss"+messageInfo.getMessageName());
        if (messageInfo.getMessageName()==null||messageInfo.getMessageName().equals("")){
            return "留下你的名字吧";
        }else if (messageInfo.getMessageContent()==null||messageInfo.getMessageContent().equals("")){
            return "想说点什么快写下来吧~";
        }else {
            boolean b = this.messageService.addMessage(messageInfo);
            if (b){
                return "收到你的留言啦,我会及时处理的哈[]~(￣▽￣)~*";
            }
            return "留言失败";
        }

    }

    /**
     * 用户在文章下留言
     * @param model
     * @param messageInfo
     * @return
     */
    @PostMapping("articlemessage")
    @ResponseBody
    private String addArticleMessage(Model model, MessageInfo messageInfo){
        //获取认证信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //判断是否已经登录，如果没有登录，提示还未登录，不能收藏。
        if (name.equals("anonymousUser")) {
            return "你还没登录，先登录后才能评论哦~";
        }else if(messageInfo.getMessageContent()==null|messageInfo.getMessageContent()==""){
            return "请先输入点东西";
        }else{
            boolean b = this.messageService.addMessage(messageInfo);
            if (b){
                return "收到你的留言啦,我会及时处理的哈[]~(￣▽￣)~*";
            }
            return "留言失败";
        }

    }


    @GetMapping("myArticleMessage")
    private String findMessage(Model model,Integer page){
        if (page==null){
            page=1;
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        //查询我的文章下的评论
        PageInfo<MessageInfo> myArtMessagelist = this.messageService.findMessage(page, 3,user.getUserId());

        model.addAttribute("pageBean",myArtMessagelist);
        return "front/myArticleMessage";
    }


    @GetMapping("myMessageToxxx")
    private String myMessageToxxx(Model model,Integer page){
        if (page==null){
            page=1;
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);


        //查询我发表的评论
        PageInfo<MessageInfo> myMessagelist = this.messageService.findMymessagelist(page, 3,user.getUserId());
        model.addAttribute("pageBean2",myMessagelist);

        return "front/myMessageToXX";
    }
}
