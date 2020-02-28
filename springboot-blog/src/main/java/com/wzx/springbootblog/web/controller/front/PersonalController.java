package com.wzx.springbootblog.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.service.ReplyService;
import com.wzx.springbootblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class PersonalController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReplyService replyService;



    @GetMapping("login")
    public String userLogin(){
        return "/front/user_login";
    }


    @GetMapping("personal")
    public String personal(Authentication authentication,Model model){
        //查询栏目信息
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
        model.addAttribute("cateList",allCategoryList);

        String name = authentication.getName();
        UserInfo user = this.userService.findUserByName(name);

        model.addAttribute("username",name);
        model.addAttribute("userinfo",user);
        return "/front/personalCentral";
    }

    /**
     * 查询当前登录用户的个人信息
     * @param authentication
     * @param model
     * @return
     */
    @GetMapping("personalInfo")
    public String getPersonalInfo(Authentication authentication,Model model){
        String name = authentication.getName();
        UserInfo user = this.userService.findUserByName(name);
        model.addAttribute("username",name);
        model.addAttribute("userinfo",user);

        return "/front/personalinfo";
    }

    @GetMapping("personalArticle")
    public String getPersonalArticle(Authentication authentication,Model model,Integer page){
        String name = authentication.getName();
        UserInfo user = this.userService.findUserByName(name);
        if (page==null)
            page=1;
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setUserId(user.getUserId());
        PageInfo<ArticleInfo> personalArticles = this.articleService.findPersonalArticleByCondition(page, 8,articleInfo);
        if (personalArticles==null){
            return "front/null";
        }else {
            model.addAttribute("pageBean",personalArticles);
            return "/front/personal_article_list";
        }

    }

    @PostMapping("personalArticle")
    public String getPersonalArticleByTitle(Authentication authentication,Model model,Integer page,ArticleInfo articleInfo){
        String name = authentication.getName();
        UserInfo user = this.userService.findUserByName(name);
        if (page==null)
            page=1;
        articleInfo.setUserId(user.getUserId());
        PageInfo<ArticleInfo> personalArticles = this.articleService.findPersonalArticleByCondition(page, 8,articleInfo);
        if (personalArticles==null){
            return "front/null";
        }else {
            model.addAttribute("pageBean",personalArticles);
            return "/front/personal_article_list";
        }


    }


  /*  @GetMapping("/collection/{id}")
    public String collectionArticle(Authentication authentication,@PathVariable Integer id){
        System.out.println("555");
        String name = authentication.getName();
        UserInfo user = this.userService.findUserByName(name);
        Integer userId = user.getUserId();
        boolean flag = this.articleService.collectAritcles(userId,id);
        System.out.println("555");
        return "redirect:front/listview";

    }*/

    @ResponseBody
    @GetMapping("/collection")
    public String collection(Integer id){
        //获取认证信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
       //判断是否已经登录，如果没有登录，提示还未登录，不能收藏。
       if (name.equals("anonymousUser")) {
           return "你还没登录，先登录后才能收藏哦~";
        }else{
           //如果已经登录了，先拿到用户编号
           UserInfo user = this.userService.findUserByName(name);
           Integer userId = user.getUserId();
           //将用户编号与文章编号保存到数据库
           boolean flag = this.articleService.collectAritcles(userId, id);
           if (flag)
               return "收藏成功~";
           else
               return "收藏失败~发生了一点小错误~";
       }
    }

    /**
     * 该方法用于首次访问该页面的文章时，是否显示文章已被收藏或否
     *1.如果的未登录状态，则一律显示为 未收藏， 即修改样式为空心状态 glyphicon glyphicon-heart-empty，即返回json数据，用于前端修改样式
     * 2.如果是已经登录但未收藏， 也显示为 未收藏，即修改样式为空心状态 glyphicon glyphicon-heart-empty，即返回json数据，用于前端修改样式
     * 3如果是已经登录但已收藏，则显示为实心状态，
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/iscollectioned")
    public String iscollectioned(Integer id){

        //获取认证信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //如果当前是未登录状态，则一律显示为 未登录状态，
        if (name.equals("anonymousUser")) {
            return "glyphicon glyphicon-heart-empty";
        }else{
            UserInfo user = this.userService.findUserByName(name);
            Integer userId = user.getUserId();
            //如果当前是登录状态，拿到用户的编号 和 文章编号，查询该用户是否已经收藏了该文章，
            Integer i = this.articleService.findUserCollections(userId,id);
            if (i==0) {
                //如果没有收藏记录，显示为空心
                return "glyphicon glyphicon-heart-empty";
            }else
                //如果已收藏，显示为实心
                return "glyphicon glyphicon-heart";

        }
    }

    @ResponseBody
    @GetMapping("/cancelcollectioned")
    public String cancelcollectioned(Integer id){
        //获取认证信息
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfo user = this.userService.findUserByName(name);
            Integer userId = user.getUserId();
             boolean flag = this.articleService.cancelCollection(userId,id);
            if (flag) {
                return "已取消收藏";
            }else
                return "发生了一点小错误~";
    }

    /**
     * 我的收藏 查询出所有我收藏的文章
     * @param model
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/myCollections")
    public String myCollections(Model model,Integer page, HttpServletRequest request){
        //获取认证信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        Integer userId = user.getUserId();
        if (page==null)
            page=1;
        PageInfo<ArticleInfo> allArticlePageList = this.articleService.findAllMyCollectionsArticlePageListByUserId(page, 8,userId);
        if (allArticlePageList==null){
            return "front/null";
        }else {
            model.addAttribute("pageBean",allArticlePageList);
            return "front/myCollection_article_list";
        }

    }

    /**
     * 查看我收藏的文章的详细内容
     * @param model
     * @param id
     * @return
     */
    @GetMapping("collectionArticleInfo/{id}")
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
        return "front/collection_articleInfo";
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @param model
     * @return
     */
    @PostMapping("updatepersonalinfo")
    @ResponseBody
    public String update(UserInfo userInfo,Model model){
        String userPassword = userInfo.getUserPassword();
        if (userPassword.length()<20) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encode = bCryptPasswordEncoder.encode(userPassword);
            userInfo.setUserPassword(encode);
        }
        int i = this.userService.updateUserInfo(userInfo);
        return "修改成功~";

    }

    /**
     * 用户头像上传
     * @param upload
     * @return
     */
    @PostMapping("upload")
    @ResponseBody
    @PreAuthorize("hasAuthority('上传用户头像')")
    public String upLoad(@RequestParam MultipartFile upload){
        String url = userService.doPutFile(upload);
        return url;

    }

    @PostMapping("replyMessage")
    public String replyMessage(String replyContent,Integer messageId){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = this.userService.findUserByName(name);
        Integer userId = user.getUserId();
        boolean b = this.replyService.insertReplyMessage(replyContent, userId, messageId);
        if (b){
            return "redirect:/myArticleMessage";
        }
        return "/myArticleMessage";
    }

}
