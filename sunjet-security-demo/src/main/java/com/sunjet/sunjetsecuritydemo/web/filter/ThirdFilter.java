package com.sunjet.sunjetsecuritydemo.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component
public class ThirdFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ThirdFilter filter init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ThirdFilter filter start.");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("ThirdFilter filter 耗时:" + (new Date().getTime() - start));
        System.out.println("ThirdFilter filter finish.");
    }

    @Override
    public void destroy() {
        System.out.println("ThirdFilter filter destroy.");
    }
}
