package com.wzx.springbootblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = "com.wzx.springbootblog.mapper")
@EnableCaching
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableTransactionManagement
public class SpringbootBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApplication.class, args);
    }

}
