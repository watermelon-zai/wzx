package com.wzx.springbootblog.mapper;

import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.ArticleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleInfoMapper {
    int countByExample(ArticleInfoExample example);

    int deleteByExample(ArticleInfoExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    ArticleInfo selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    int updateByExample(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);

    List<ArticleInfo> findAllArticlePageList(Integer pageNum, Integer pageSize);

    List<ArticleInfo> findAllArticlePageListByCondition(Integer pageNum, Integer pageSize, String articleTitle);

    List<ArticleInfo> findNewArticle();

    List<ArticleInfo> findArticleRecomList();

    ArticleInfo findOneArticleById(Integer id);

    List<ArticleInfo> findArticleByCateId(Integer id);

    List<ArticleInfo> findArticleRecomListByCateId(Integer id);

    List<ArticleInfo> searchArticle(String name);
}