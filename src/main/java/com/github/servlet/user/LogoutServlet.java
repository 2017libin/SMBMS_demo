package com.github.servlet.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author libin
 * @Date 2023/10/31
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 移除session中的属性
//        request.getSession().removeAttribute(Constants.USER_SESSION);
        // 删除session，下次使用该JSESSIONID访问时会新建一个session而不是报错
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
