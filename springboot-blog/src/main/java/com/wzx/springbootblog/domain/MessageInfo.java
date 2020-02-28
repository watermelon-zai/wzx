package com.wzx.springbootblog.domain;

import java.util.Date;

public class MessageInfo {
    private Integer messageId;

    private String messageContent;

    private Date messageTime;

    private String messageName;

    private String messageMark;

    private Integer userId;

    private Integer articleId;

    private String userName;

    private String userImg;

    private String articleTitle;

    private Integer replyId;

    private String replyContent;

    private String replyTime;

    private Integer replyUserId;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName == null ? null : messageName.trim();
    }

    public String getMessageMark() {
        return messageMark;
    }

    public void setMessageMark(String messageMark) {
        this.messageMark = messageMark == null ? null : messageMark.trim();
    }

    private String ck;
    private Integer[]ids;
    public String getCk() {
        ck="<input type='checkbox' name='ids' id='id' value=' "+this.getMessageId()+"'/>";
        return ck;
    }
    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}