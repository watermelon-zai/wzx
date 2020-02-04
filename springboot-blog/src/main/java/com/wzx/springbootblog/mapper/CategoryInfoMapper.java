package com.wzx.springbootblog.mapper;

import com.wzx.springbootblog.domain.CategoryInfo;
import com.wzx.springbootblog.domain.CategoryInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryInfoMapper {
    int countByExample(CategoryInfoExample example);

    int deleteByExample(CategoryInfoExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    CategoryInfo selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByExample(@Param("record") CategoryInfo record, @Param("example") CategoryInfoExample example);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);

    List<CategoryInfo> findAllCategoryList();
}