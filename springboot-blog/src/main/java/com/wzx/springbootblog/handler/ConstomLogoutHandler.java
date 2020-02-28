package com.wzx.springbootblog.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ConstomLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        String auth = authorities.toString();
        if (auth.contains("ROLE_ADMIN")){
            response.sendRedirect(request.getContextPath()+"/back/login");

        }else if (auth.contains("ROLE_USER")){
            response.sendRedirect(request.getContextPath()+"/index");

        }
    }
}
