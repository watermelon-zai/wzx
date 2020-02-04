package com.wzx.springbootblog.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class FrontArticleController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

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
        model.addAttribute("articleRecom",articleRecomList);
        model.addAttribute("newArticle",newArticle);
        model.addAttribute("cateList",allCategoryList);
        return "front/index";
    }

    @GetMapping("listView/{id}")
    public  String listView(Model model, @PathVariable Integer id){
        ArticleInfo article = this.articleService.findOneArticleById(id);
        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);
        model.addAttribute("articleRecom",articleRecomList);
        model.addAttribute("art",article);
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
}
