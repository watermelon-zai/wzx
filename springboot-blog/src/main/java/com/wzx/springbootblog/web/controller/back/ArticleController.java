package com.wzx.springbootblog.web.controller.back;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 文章管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/article/")
public class ArticleController {
	CategoryInfo categoryInfo = new CategoryInfo();
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	/**
	 *无条件的分页查询
	 * @return
	 */
	@PreAuthorize("hasAuthority('文章管理')")
	@GetMapping("list")
	public String list(Model model,Integer page) {
		if (page==null)
			page=1;

		PageInfo<ArticleInfo> allArticlePageList = this.articleService.findAllArticlePageList(page, 8);
		model.addAttribute("pageBean",allArticlePageList);
		return "article/article_list";
	}

	/**
	 * 根据文章标题查询文章的分页信息
	 * @param model
	 * @param page
	 * @param articleTitle
	 * @return
	 */
	@PreAuthorize("hasAuthority('有条件的文章分页查询')")
	@PostMapping("list")
	public String find(Model model,Integer page,ArticleInfo articleInfo) {
		if (page==null)
			page=1;
		PageInfo<ArticleInfo> allArticlePageList = this.articleService.findAllArticlePageListByCondition(page, 8,articleInfo);
		model.addAttribute("pageBean",allArticlePageList);
		return "article/article_list";
	}

	/**
	 * 加载添加文章页面
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAuthority('加载添加文章页面')")
	@GetMapping("loadAdd")
	public String loadAdd(Model model) {
		List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
		model.addAttribute("cateList",allCategoryList);
		return "article/article_add";
	}

	/**
	 * 添加栏目信息
	 * @param model
	 * @param articleInfo
	 * @return
	 */
	@PreAuthorize("hasAuthority('添加文章')")
	@PostMapping("add")
	public String add(Model model, ArticleInfo articleInfo) {
		boolean b = this.articleService.addAriticle(articleInfo);
		if (b){
			List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
			model.addAttribute("cateList",allCategoryList);
			model.addAttribute("mess","添加成功！！");
			return "article/article_add";
		}else {
			List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
			model.addAttribute("cateList",allCategoryList);
			model.addAttribute("mess","添加失败");
			return "article/article_add";
		}


	}

	/**
	 * 文章图片上传
	 * @param upload
	 * @return
	 */
	@PostMapping("upload")
    @ResponseBody
	@PreAuthorize("hasAuthority('上传文章图片')")
	public String upLoad(@RequestParam MultipartFile upload){
		String url = articleService.doPutFile(upload);
		return url;

	}

	/**
	 * 根据栏目id删除栏目
	 * @param id
	 * @return
	 */
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Integer id) {
        this.articleService.deleteArticleById(id);
		return "redirect:/back/article/list";
	}

	/**
	 * 加载栏目更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAuthority('修改文章')")
	@GetMapping("loadupdate/{id}")
	public String loadUpdate(@PathVariable Integer id,Model model) {
		ArticleInfo articleInfo = this.articleService.findOneArticleById(id);
		List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
		model.addAttribute("cateList",allCategoryList);
		model.addAttribute("article",articleInfo);
		return "article/article_update";
	}

	/**
	 * 修改栏目信息
	 * @param articleInfo
	 * @param model
	 * @return
	 */
	@PostMapping("update")
	public String update(ArticleInfo articleInfo,Model model) {
		boolean b = this.articleService.updateArticleInfo(articleInfo);
		if (b){
			ArticleInfo article = this.articleService.findOneArticleById(articleInfo.getArticleId());
			List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
			model.addAttribute("cateList",allCategoryList);
			model.addAttribute("article",articleInfo);
			model.addAttribute("mess","修改成功");
			return "article/article_update";
		}
		else {
			ArticleInfo article = this.articleService.findOneArticleById(articleInfo.getArticleId());
			List<CategoryInfo> allCategoryList = this.categoryService.findAllCategoryList(categoryInfo);
			model.addAttribute("cateList",allCategoryList);
			model.addAttribute("article",articleInfo);
			model.addAttribute("mess","修改失败");
			return "article/article_update";
		}
	}

	/**
	 * 批量删除栏目
	 * @param articleInfo
	 * @return
	 */
	@PreAuthorize("hasAuthority('批量删除文章')")
	@PostMapping("deleteList")
	public String deleteList(ArticleInfo articleInfo) {
		System.out.println(articleInfo.getIds()[0]);
		List<String> ids = Arrays.asList(articleInfo.getIds());
		for (String id:ids) {
			int i = Integer.parseInt(id);
			System.out.println(id);
			this.articleService.deleteArticleById(i);
		}

		return "redirect:/back/article/list";
	}

}
