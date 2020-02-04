package com.wzx.springbootblog.service.impl;

import com.github.pagehelper.PageInfo;
import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService;
    @Test
    void addUser() {
        UserInfo user=new UserInfo();
        user.setUserName("张三");
        user.setUserAccount("321321");
        user.setUserPassword("00123");
        user.setUserPhone("11741130495");
        user.setUserMark("1");
        this.userService.addUser(user);

    }

/*    @Test
    void find(){
        PageInfo<UserInfo> pageUserList=  this.userService.findPageUserList(2,5);
           List<UserInfo> list= pageUserList.getList();
           for(UserInfo l: list){
               System.out.println(l.getUserName());
           }
    }*/
}