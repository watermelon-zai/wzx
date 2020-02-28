package com.wzx.springbootblog.domain;

public class Reply {

    private Integer replyId;

    private String replyContent;

    private String replyTime;

    private Integer replyUserId;

    private Integer replyMessageId;

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

    public Integer getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Integer replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime='" + replyTime + '\'' +
                ", replyUserId=" + replyUserId +
                ", replyMessageId=" + replyMessageId +
                '}';
    }
}
