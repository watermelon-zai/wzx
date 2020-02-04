package com.wzx.springbootblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.MessageInfo;
import com.wzx.springbootblog.domain.MessageInfoExample;
import com.wzx.springbootblog.mapper.MessageInfoMapper;
import com.wzx.springbootblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    /**
     * 前台添加留言信息
     * @param messageInfo
     * @return
     */
    @Override
    public boolean addMessage(MessageInfo messageInfo) {
       try{
           int i = this.messageInfoMapper.insertSelective(messageInfo);
           if (i>0)
               return true;
       }catch(Exception e){
        e.printStackTrace();
       }

        return false;
    }

    /**
     * 前台提交的留言信息 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<MessageInfo> findAllMessagePageList(Integer pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        MessageInfoExample messageInfoExample = new MessageInfoExample();
        List<MessageInfo> messageInfoList = this.messageInfoMapper.selectByExample(messageInfoExample);
        PageInfo pageInfo = new PageInfo(messageInfoList);
        return pageInfo;
    }

    /**
     * 后台管理留言信息 删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteMessge(Integer id) {
       try{
           int i = this.messageInfoMapper.deleteByPrimaryKey(id);
           if (i>0)
               return true;
       }catch(Exception e){
        e.printStackTrace();
       }

        return false;
    }

    /**
     * 后台根据留言姓名查询留言分页信息
     * @param pageNum
     * @param pageSize
     * @param messName
     * @return
     */
    @Override
    public PageInfo<MessageInfo> findAllMessagePageListByName(Integer pageNum, int pageSize, String messName) {
        PageHelper.startPage(pageNum,pageSize);
        MessageInfoExample messageInfoExample = new MessageInfoExample();
        MessageInfoExample.Criteria criteria = messageInfoExample.createCriteria();
        criteria.andMessageNameLike("%"+messName+"%");
        List<MessageInfo> messageInfoList = this.messageInfoMapper.selectByExample(messageInfoExample);
        PageInfo pageInfo = new PageInfo(messageInfoList);
        return pageInfo;
    }

    /**
     * 后台审核，修改MessageMark为1 ，能在前台页面上显示
     * @param id
     * @return
     */
    @Override
    public boolean updateMassMarkShow(Integer id) {
        try{
        this.messageInfoMapper.updateMessMark(id);
        }catch(Exception e){
         e.printStackTrace();
        }
        return false;
    }

    /**
     * 后台审核，修改MessageMark为0 ，不能能在前台页面上显示
     * @param id
     */
    @Override
    public void updateMassMarkNoshow(Integer id) {
        try{
        this.messageInfoMapper.updateMessMarkNoshow(id);
        }catch(Exception e){
         e.printStackTrace();
        }
    }

    /**
     * 在前台页面能显示的留言信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<MessageInfo> findAllMessagePageListByShow(Integer pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        MessageInfoExample messageInfoExample = new MessageInfoExample();
        MessageInfoExample.Criteria criteria = messageInfoExample.createCriteria();
        criteria.andMessageMarkEqualTo("1");
        List<MessageInfo> messageInfoList = this.messageInfoMapper.selectByExample(messageInfoExample);
        PageInfo pageInfo = new PageInfo(messageInfoList);
        return pageInfo;
    }
}
