package com.wzx.springbootblog.config;

import com.wzx.springbootblog.handler.ComstomAuthenticationFailureHandler;
import com.wzx.springbootblog.handler.ComstomAuthenticationSuccessHandler;
import com.wzx.springbootblog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //调用业务逻辑
      auth.userDetailsService(this.userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //放行静态资源
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/admin/**","/ckeditor/**","/front/**","/layer/**");
    }

    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        在spring boot项目中出现不能加载iframe
        页面报一个"Refused to display 'http://......' in a frame because it set 'X-Frame-Options' to 'DENY'. "错误
         */
        http.headers().frameOptions().disable();

    http.authorizeRequests()
            .antMatchers("/back/main","/index","/front/message","/listView/**","/java/**","/web/**","/spring/**","/php/**","/html/**","/javascript/**","/python/**","/search").permitAll()
            .antMatchers("/**").hasAnyRole("ADMIN","USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/back/login")
            .loginProcessingUrl("/login")
            .successHandler(new ComstomAuthenticationSuccessHandler())
            .failureHandler(new ComstomAuthenticationFailureHandler())
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/back/login")
            .invalidateHttpSession(true)
            .permitAll()
            .and()
            .csrf()
            .disable();
    }
}
