package com.wzx.springbootblog.service;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.CategoryInfo;

import java.util.List;

public interface CategoryService {
    /**
     * 查询栏目的分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CategoryInfo> findPageCategoryList(Integer pageNum, Integer pageSize);

    /**
     * 添加栏目
     * @param categoryInfo
     * @return
     */
    boolean addCategory(CategoryInfo categoryInfo);

    /**
     * 计算该栏目下的文章数
     * @param categoryId
     * @return
     */
    Integer countAriticals(Integer categoryId);

    /**
     * 删除栏目
     * @param cateId
     * @return
     */
    boolean deleteCate(Integer cateId);

    /**
     * 根据栏目id查询栏目
     * @param id
     * @return
     */
    CategoryInfo findCateInfoById(Integer id);

    /**
     * 修改栏目信息
     * @param categoryInfo
     * @return
     */
    boolean updateCateInfo(CategoryInfo categoryInfo);

    /**
     * 查询所有栏目信息
     * @return
     */
    List<CategoryInfo> findAllCategoryList(CategoryInfo categoryInfo);
}
