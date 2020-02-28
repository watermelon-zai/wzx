package com.wzx.springbootblog.mapper;

public interface ReplyMapper {
    boolean insertReplyMessage(String replyContent, Integer userId, Integer messageId);
}
