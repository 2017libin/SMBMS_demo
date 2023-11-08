package com.github.servlet.user;

import com.github.pojo.User;
import com.github.service.user.UserService;
import com.github.service.user.UserServiceImpl;
import com.github.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 实现servlet复用
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        System.out.println("method----> " + method);

        if(method != null && method.equals("savepwd")){
            this.updatePwd(request, response);
        }
    }

    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从Session中取ID
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");

        System.out.println("UserServlet3:"+newpassword);

        boolean flag = false;

        if(user != null && newpassword != null && !newpassword.isEmpty()){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(user.getId(),newpassword);
            if(flag){
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                // 密码修改成功，session注销
                request.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        // 请求转发
        // 将当前请求（request）转发给另一个Web资源（通常是另一个JSP页面或Servlet），并将控制权移交给该资源来处理请求
        request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
    }

}