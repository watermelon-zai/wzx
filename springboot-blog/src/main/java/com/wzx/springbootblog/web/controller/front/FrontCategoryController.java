package com.wzx.springbootblog.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class FrontCategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * 查询当前是否有用户登录
     * @return
     */
    public UserInfo getUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        return user;
    }

    /**
     * 根据栏目id查询该栏目下的所有文章
     * @param model
     * @return
     */
    @GetMapping("cate/{id}")
    public String findArticle(Model model, @PathVariable Integer id,Integer pageNum){
        if (pageNum==null){
            pageNum=1;
        }
        PageInfo<ArticleInfo> articleByCateId = this.articleService.findArticleByCateId(id,pageNum,3);

        //查询站长推荐文章
        List<ArticleInfo> articleRecomList = this.articleService.findArticleRecomList();
        model.addAttribute("articleRecom",articleRecomList);
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);
        //查询该栏目下站长推荐的文章 即本栏推荐
        List<ArticleInfo> articleRecomListByCateId = this.articleService.findArticleRecomListByCateId(id);
        model.addAttribute("articleRecomListByCateId",articleRecomListByCateId);
        if (CollectionUtils.isEmpty(articleByCateId.getList())){
            return "front/list_null";
        }
        UserInfo user = this.getUser();
        model.addAttribute("user",user);
        model.addAttribute("pageBean",articleByCateId);
        return "front/list";
    }
}
