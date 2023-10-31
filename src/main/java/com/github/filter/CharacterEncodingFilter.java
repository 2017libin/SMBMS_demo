package com.github.filter;

import javax.servlet.*;
import java.io.IOException;

// 设置请求和响应的字符编码
public class CharacterEncodingFilter implements Filter {

    // init方法在web应用启动时调用，并且只会调用一次
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 传递给下一个过滤器
        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}