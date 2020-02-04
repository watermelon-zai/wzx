package com.wzx.springbootblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.CategoryInfoExample;
import com.wzx.springbootblog.mapper.CategoryInfoMapper;
import com.wzx.springbootblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    /**
     * 查询栏目的分页信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CategoryInfo> findPageCategoryList(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CategoryInfo> categoryInfoList = this.categoryInfoMapper.findAllCategoryList();
        PageInfo<CategoryInfo> categoryInfoPageInfo = new PageInfo<>(categoryInfoList);
        return categoryInfoPageInfo;
    }

    /**
     * 添加栏目
     * @param categoryInfo
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "wzx",allEntries = true)
    public boolean addCategory(CategoryInfo categoryInfo) {
        int i = this.categoryInfoMapper.insertSelective(categoryInfo);
        if (i>0){
            return true;
        }else
        return false;
    }

    /**
     * 查询该栏目下的文章数
     * @param categoryId
     * @return
     */
    @Override
    public Integer countAriticals(Integer categoryId) {
        return null;
    }

    /**
     * 根据栏目id删除栏目
     * @param cateId
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "wzx",allEntries = true)
    public boolean deleteCate(Integer cateId) {
        int i = this.categoryInfoMapper.deleteByPrimaryKey(cateId);
        if (i>0){
            return true;
        }else
            return false;
    }

    /**
     * 根据栏目id查询栏目信息
     * @param id
     * @return
     */
    @Override
    public CategoryInfo findCateInfoById(Integer id) {
        CategoryInfo categoryInfo = this.categoryInfoMapper.selectByPrimaryKey(id);
        return categoryInfo;
    }

    /**
     * 修改栏目信息
     * @param categoryInfo
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "wzx",allEntries = true)
    public boolean updateCateInfo(CategoryInfo categoryInfo) {
        int i = this.categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        if (i>0){
            return true;
        }else
            return false;

}

    @Override
    @Cacheable(value = "wzx",key = "#p0")
    public List<CategoryInfo> findAllCategoryList(CategoryInfo categoryInfo) {
        List<CategoryInfo> allCategoryList = this.categoryInfoMapper.findAllCategoryList();
        return allCategoryList;
    }
}
