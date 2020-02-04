package com.wzx.springbootblog;

import com.wzx.springbootblog.domain.UserInfo;
import com.wzx.springbootblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void addUser() {
        UserInfo user=new UserInfo();
        user.setUserName("张三");
        user.setUserAccount("321321");
        user.setUserPassword("00123");
        user.setUserPhone("11741130495");
        user.setUserMark("1");
        System.out.println(user);
        this.userService.addUser(user);

    }

}
