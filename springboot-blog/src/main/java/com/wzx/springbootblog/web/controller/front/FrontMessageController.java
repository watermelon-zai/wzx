package com.wzx.springbootblog.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.MessageInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.service.MessageService;
import com.wzx.springbootblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MessageService messageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


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
    private String addMessage(Model model, MessageInfo messageInfo){
        boolean b = this.messageService.addMessage(messageInfo);
        if (b){
            return "收到你的留言啦,我会及时处理的哈[]~(￣▽￣)~*";
        }
        return "留言失败";
    }
}
