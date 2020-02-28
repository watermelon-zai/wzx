package com.wzx.springbootblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.wzx.springbootblog.domain.ArticleInfo;
import com.wzx.springbootblog.domain.ArticleInfoExample;
import com.wzx.springbootblog.mapper.ArticleInfoMapper;
import com.wzx.springbootblog.mapper.MessageInfoMapper;
import com.wzx.springbootblog.service.ArticleService;
import com.wzx.springbootblog.utils.Const;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @Override
    public String doPutFile(MultipartFile file) {
        try {
            //图片名称
            String fileName = file.getOriginalFilename();
            //当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String format = sdf.format(new Date());

            //拼接url
            String url = Const.FILE_URL+format+fileName;
            System.out.println(url);

            //jersey客户端
            Client client = new Client();
            WebResource resource = client.resource(url);

            //将文件转为byte
            byte[] buf = file.getBytes();
            resource.put(String.class,buf);
            return url;

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 添加文章
     * @param articleInfo
     * @return
     */
    @Override
    public boolean addAriticle(ArticleInfo articleInfo) {
        try {
            int i = this.articleInfoMapper.insertSelective(articleInfo);
            if (i>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return false;
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ArticleInfo> findAllArticlePageList(Integer pageNum, Integer pageSize) {
       try {
           PageHelper.startPage(pageNum,pageSize);
           ArticleInfoExample articleInfoExample = new ArticleInfoExample();
           List<ArticleInfo> articleInfoList = this.articleInfoMapper.findAllArticlePageList(pageNum,pageSize);
           PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleInfoList);
           return pageInfo;

       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    /**
     * 根据文章标题查询文章的分页信息
     * @param pageNum
     * @param pageSize
     * @param articleTitle
     * @return
     */
    @Override
    public PageInfo<ArticleInfo> findAllArticlePageListByCondition(Integer pageNum, Integer pageSize,ArticleInfo articleInfo) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<ArticleInfo> articleInfoList = this.articleInfoMapper.findAllArticlePageListByCondition(pageNum,pageSize,articleInfo.getArticleTitle());
            PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleInfoList);
            return pageInfo;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文章id删除文章信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteArticleById(Integer id) {

        try {
            int i = this.articleInfoMapper.deleteByPrimaryKey(id);
            this.messageInfoMapper.deleteMessageByArtId(id);
            if (i>0){

                return true;
            }else
                return false;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据文章id来插叙文章信息
     * @param id
     * @return
     */
    @Override
    public ArticleInfo findOneArticleById(Integer id) {
        try {
            ArticleInfo articleInfo = this.articleInfoMapper.findOneArticleById(id);
            return articleInfo;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 修改文章信息
     * @param articleInfo
     * @return
     */
    @Override
    public boolean updateArticleInfo(ArticleInfo articleInfo) {
        try{
            int i = this.articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
            if (i>0)
                return true;
            else
                return false;
        }catch(Exception e){
         e.printStackTrace();
        }
        
        return false;
    }

    /**
     * 查询最新的6篇文章信息
     * @return
     */
    @Override
    public List<ArticleInfo> findNewArticle() {
        List<ArticleInfo> newArticle = this.articleInfoMapper.findNewArticle();
        return newArticle;
    }

    /**
     * 查询站长推荐的文章
     * @return
     */
    @Override
    public List<ArticleInfo> findArticleRecomList() {
        List<ArticleInfo> articleRecomList = this.articleInfoMapper.findArticleRecomList();
        return articleRecomList;
    }

    /**
     * 根据栏目id查询该栏目下的所有文章
     * @param id
     * @return
     */
    @Override
    public PageInfo<ArticleInfo> findArticleByCateId(Integer id,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> allArticlePageList = this.articleInfoMapper.findArticleByCateId(id);
        PageInfo pageInfo= new PageInfo(allArticlePageList);
        return pageInfo;
    }

    /**
     * 根据栏目id查询查询该栏目下站长推荐的文章
     * @return
     */
    @Override
    public List<ArticleInfo> findArticleRecomListByCateId(Integer id) {
        try{
            List<ArticleInfo> articleRecomListByCateId = this.articleInfoMapper.findArticleRecomListByCateId(id);
            if (CollectionUtils.isNotEmpty(articleRecomListByCateId)){
                return articleRecomListByCateId;
            }
        }catch(Exception e){
         e.printStackTrace();
        }
        return null;
    }

    /**
     * 检索文章
     * @param name
     * @return
     */
    @Override
    public PageInfo<ArticleInfo> searchArticle(String name,Integer pageNum,Integer pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<ArticleInfo> articleInfos = this.articleInfoMapper.searchArticle(name);
            PageInfo pageInfo = new PageInfo(articleInfos);
            if (CollectionUtils.isNotEmpty(articleInfos)){
                return pageInfo;
            }
        }catch(Exception e){
         e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户查询该用户的所有文章
     * @param userId
     * @return
     */
    @Override
    public PageInfo<ArticleInfo> findPersonalArticleByCondition(Integer pageNum, Integer pageSize,ArticleInfo articleInfo) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<ArticleInfo> personalArticle = this.articleInfoMapper.findPersonalArticleByCondition(articleInfo);
            PageInfo pageInfo = new PageInfo(personalArticle);
            if (CollectionUtils.isNotEmpty(personalArticle)){
                return pageInfo;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 收藏文章 将用户id和文章id插入收藏表
     * @param userId
     * @param articleId
     * @return
     */
    @Override
    public boolean collectAritcles(Integer userId, Integer articleId) {
       try{
           this.articleInfoMapper.collectArticles(userId,articleId);
           return true;
       }catch(Exception e){
        e.printStackTrace();
       }
        return false;
    }

    /**
     * 根据用户编号和文章编号查询 收藏记录，
     * @param userId
     * @param id
     * @return
     */
    @Override
    public Integer findUserCollections(Integer userId, Integer id) {
        Integer i = this.articleInfoMapper.findUserCollections(userId,id);
        return i;
    }

    @Override
    public Boolean cancelCollection(Integer userId, Integer id) {
       try{
           this.articleInfoMapper.cancelCollection(userId,id);
           return true;
       }catch(Exception e){
        e.printStackTrace();
       }
        return false;
    }

    @Override
    public PageInfo<ArticleInfo> findAllMyCollectionsArticlePageListByUserId(Integer pageNum,Integer pageSize, Integer userId) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<ArticleInfo> personalArticle = this.articleInfoMapper.findAllMyCollectionsArticlePageListByUserId(userId);
            PageInfo pageInfo = new PageInfo(personalArticle);
            if (CollectionUtils.isNotEmpty(personalArticle)){
                return pageInfo;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }


}
