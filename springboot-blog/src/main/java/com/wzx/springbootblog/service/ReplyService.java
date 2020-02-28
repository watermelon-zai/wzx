package com.wzx.springbootblog.service;

public interface ReplyService {
    boolean insertReplyMessage(String replyContent, Integer userId, Integer messageId);
}
