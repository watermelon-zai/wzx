package com.wzx.springbootblog.handler;

import com.sun.org.apache.regexp.internal.RE;
import com.wzx.springbootblog.domain.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

public class ComstomAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        String auth = authorities.toString();
        if (auth.contains("ROLE_ADMIN")){
            response.sendRedirect(request.getContextPath()+"/back/index");
            System.out.println("管理员 ");
        }else if (auth.contains("ROLE_USER")){
            response.sendRedirect(request.getContextPath()+"/personal");
            System.out.println("用户");
        }
     /*   response.setContentType("applocation/json;charset=utf-8");
        RequestCache cache = new HttpSessionRequestCache();
        SavedRequest request1 = cache.getRequest(request, response);
        if (request1==null){
            response.sendRedirect(request.getContextPath()+"/personal");
        }else {
            response.sendRedirect(request1.getRedirectUrl());
        }*/
    }
}
