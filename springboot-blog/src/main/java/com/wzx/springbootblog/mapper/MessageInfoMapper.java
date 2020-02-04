package com.wzx.springbootblog.mapper;

import com.wzx.springbootblog.domain.MessageInfo;
import com.wzx.springbootblog.domain.MessageInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageInfoMapper {
    int countByExample(MessageInfoExample example);

    int deleteByExample(MessageInfoExample example);

    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    List<MessageInfo> selectByExample(MessageInfoExample example);

    MessageInfo selectByPrimaryKey(Integer messageId);

    int updateByExampleSelective(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByExample(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);

    boolean updateMessMark(Integer id);

    void updateMessMarkNoshow(Integer id);
}