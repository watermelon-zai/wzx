package com.wzx.springbootblog.service;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.MessageInfo;

import java.util.List;

public interface MessageService {
    boolean addMessage(MessageInfo messageInfo);

    PageInfo<MessageInfo> findAllMessagePageList(Integer page, int pageSize);

    boolean deleteMessge(Integer id);

    PageInfo<MessageInfo> findAllMessagePageListByName(Integer pageNum, int pageSize, String messName);

    boolean updateMassMarkShow(Integer id);

    void updateMassMarkNoshow(Integer id);

    PageInfo<MessageInfo> findAllMessagePageListByShow(Integer pageNum, int pageSize);
}
