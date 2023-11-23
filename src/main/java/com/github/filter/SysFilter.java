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

    // 对于未登陆的
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("-------SysFilter--------");
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;

        // rq.getSession()在sessionId不存在/或者为空时将会创建新的session，并且响应set-cookie设置新的JSESSIONID
        User userSession = (User) rq.getSession().getAttribute("userSession");  // 如果session不存在，当然userSession属性也不存在

        // 请求包中JSESSIONID不存在时为true
        if(userSession == null){
            // 重定向到error.jsp，并响应set-cookie设置新的JSESSIONID
            System.out.println("userSession is null");
            rp.sendRedirect("/smbms/error.jsp");
        }else {
            chain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}