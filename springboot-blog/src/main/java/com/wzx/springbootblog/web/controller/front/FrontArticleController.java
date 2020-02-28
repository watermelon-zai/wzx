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
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class FrontArticleController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    /**
     * 查询当前是否有用户登录
     * @return
     */
    public UserInfo getUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        return user;
    }

    @GetMapping("/index")
    private String loadIndex(Model model,Integer page){
        if (page==null){
            page=1;
        }
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        //查询页面上最新文章 前6条
        List<ArticleInfo> newArticle = this.articleService.findNewArticle();
        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        //查询用户信息
        UserInfo user = this.getUser();
        model.addAttribute("user",user);
        model.addAttribute("articleRecom",articleRecomList);
        model.addAttribute("newArticle",newArticle);
        model.addAttribute("cateList",allCategoryList);
        return "front/index";
    }

    @GetMapping("listView/{id}")
    public  String listView(Model model, @PathVariable Integer id,Integer page){
        if (page==null){
            page=1;
        }
        //获取当前登录用户的信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        //根据文章编号查询该篇文章详细信息
        ArticleInfo article = this.articleService.findOneArticleById(id);
        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        //查询该文章下的所有留言
        PageInfo<MessageInfo>  pageBean = this.messageService.findArtMessage(page,6,id);

        model.addAttribute("pageBean",pageBean);
        model.addAttribute("cateList",allCategoryList);
        model.addAttribute("articleRecom",articleRecomList);
        model.addAttribute("art",article);
        model.addAttribute("user",user);
        return "front/listview";
    }

    @GetMapping("cateArticleList")
    public  String cateArticleList(Model model){

        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);
        model.addAttribute("articleRecom",articleRecomList);
        return "front/listview";
    }

    @PostMapping("search")
    public String findArticle(Model model,String name,Integer pageNum){
        if (pageNum==null){
            pageNum=1;
        }
        PageInfo<ArticleInfo> articles = this.articleService.searchArticle(name,pageNum,6);
        model.addAttribute("pageBean",articles);
        List<ArticleInfo> list = articles.getList();
        for (ArticleInfo s:list){
            System.out.println(s.getArticleTitle());
        }
        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        model.addAttribute("articleRecomListByCateId",articleRecomList);
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);


        return "front/list";
    }
}
