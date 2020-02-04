package com.wzx.springbootblog.service;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.ArticleInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    /**
     * 图片上传
     * @param file
     * @return
     */
    public String doPutFile(MultipartFile file);

    /**
     *添加文章
     * @param articleInfo
     * @return
     */
    boolean addAriticle(ArticleInfo articleInfo);

    /**
     * 无条件的分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ArticleInfo> findAllArticlePageList(Integer pageNum, Integer pageSize);


    /**
     * 根据文章标题查询文章分页信息
     * @param page
     * @param pageSize
     * @param articleTitle
     * @return
     */
    PageInfo<ArticleInfo> findAllArticlePageListByCondition(Integer page,Integer pageSize,ArticleInfo articleInfo);

    /**
     * 根据文章id删除文章
     * @param id
     * @return
     */
    boolean deleteArticleById(Integer id);

    /**
     * 根据文章id查询文章信息
     * @param id
     * @return
     */
    ArticleInfo findOneArticleById(Integer id);

    /**
     * 后台修改文章信息
     * @param articleInfo
     * @return
     */
    boolean updateArticleInfo(ArticleInfo articleInfo);

    /**
     * 查询最新文章 前6
     * @return
     */
    List<ArticleInfo> findNewArticle();

    /**
     * 查询站长推荐的文章 前5
     * @return
     */
    List<ArticleInfo> findArticleRecomList();

    /**
     * 根据栏目id查询该栏目下的所有文章
     * @param id
     * @return
     */
    PageInfo<ArticleInfo> findArticleByCateId(Integer id,Integer pageNum, Integer pageSize);

    /**
     * 根据栏目id查询查询该栏目下站长推荐的文章
     * @return
     */
    List<ArticleInfo> findArticleRecomListByCateId(Integer id);

    /**
     * 检索文章
     * @param name
     * @return
     */
    PageInfo<ArticleInfo> searchArticle(String name,Integer pageNum,Integer pageSize);
}
