package com.wzx.springbootblog.web.controller.back;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.service.CategoryService;
import com.wzx.springbootblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 栏目管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("back/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 无条件的分页查询，查询出所有的栏目
	 * @param model
	 * @param page
	 * @return
	 */
	@PreAuthorize("hasAuthority('栏目管理')")
	@RequestMapping("list")
	public String list(Model model,Integer page) {
		if(page==null){
			page=1;
		}
		PageInfo<CategoryInfo> allCategory = this.categoryService.findPageCategoryList(page,Const.PAGE_SIZE);
		model.addAttribute("pageBean",allCategory);
		return "category/category";
	}

	/**
	 * 加载栏目修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("loadupdate/{id}")
	public String loadupdate(@PathVariable Integer id,Model model) {
		CategoryInfo cateInfo = this.categoryService.findCateInfoById(id);
		model.addAttribute("cate",cateInfo);
		return "category/category_update";
	}

	/**
	 * 接收提交过来要修改的栏目信息，并执行修改操作
	 * @param categoryInfo
	 * @param model
	 * @return
	 */

	@PostMapping("update")
	public String update(CategoryInfo categoryInfo,Model model) {
		boolean b = this.categoryService.updateCateInfo(categoryInfo);
		if (b){
			CategoryInfo cateInfo = this.categoryService.findCateInfoById(categoryInfo.getCategoryId());
			model.addAttribute("message","修改成功");
			model.addAttribute("cate",categoryInfo);
			return "category/category_update";
		}else {
			model.addAttribute("message","修改失败");
			return "category/category_update";
		}


	}


	/**
	 * 添加栏目
	 * @param categoryInfo
	 * @return
	 */
	@RequestMapping("add")
	public String add(CategoryInfo categoryInfo,Model model) {
		boolean b = this.categoryService.addCategory(categoryInfo);
		if (b){
			model.addAttribute("message","添加成功");
			return "redirect:/back/category/list";
			//return "category/category";
		}else {
			model.addAttribute("message","添加失败");
			return "redirect:/back/category/list";
			//return "category/category";
		}
	}

	/**
	 * 根据栏目id删除栏目 ，永久性的删除
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String deleteCate(@PathVariable Integer id) {
		boolean b = this.categoryService.deleteCate(id);
		return "redirect:/back/category/list";
	}


}
