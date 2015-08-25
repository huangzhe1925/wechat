package com.hz.wecharproject.interceptor;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hz.wecharproject.utils.CommonUtil;

public class AllFilter extends OncePerRequestFilter {
   
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (CommonUtil.WECHAT_URL.equals(uri)) {
        	filterChain.doFilter(request, response);
        }else if(CommonUtil.SITE_MANAGE_URL.equals(uri)){
        	filterChain.doFilter(request, response);
        }
    }
}