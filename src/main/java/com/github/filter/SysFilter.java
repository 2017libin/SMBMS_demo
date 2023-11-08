package com.github.filter;

import com.github.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 过滤未登陆的请求，重定向到error页面
public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("-------SysFilter--------");
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;

        // session不存在时将会创建新的session，并且响应set-cookie设置新的JSESSIONID
        User userSession = (User) rq.getSession().getAttribute("userSession");

        if(userSession == null){
            System.out.println("userSession is null");
            rp.sendRedirect("/smbms/error.jsp");
        }else {
            chain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}