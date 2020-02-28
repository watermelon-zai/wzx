package com.wzx.springbootblog.service.impl;

import com.wzx.springbootblog.mapper.ReplyMapper;
import com.wzx.springbootblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public boolean insertReplyMessage(String replyContent, Integer userId, Integer messageId) {
        try{
            boolean b = this.replyMapper.insertReplyMessage(replyContent, userId, messageId);
            return b;
        }catch(Exception e){
         e.printStackTrace();
            return false;
        }

    }
}
