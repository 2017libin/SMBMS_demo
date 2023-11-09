package com.github.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.github.pojo.Role;
import com.github.pojo.User;
import com.github.service.role.RoleService;
import com.github.service.role.RoleServiceImpl;
import com.github.service.user.UserService;
import com.github.service.user.UserServiceImpl;
import com.github.util.Constants;
import com.github.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        System.out.println("GET: method----> " + method);

        if(method != null && method.equals("savepwd")){
            this.updatePwd(request, response);
        } else if(method.equals("pwdmodify") && method!=null){
            this.pwdModify(request,response);
        } else if(method != null && method.equals("query")) {
            this.query(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        System.out.println("POST: method----> " + method);

        if (method!=null){
            if(method.equals("savepwd")){  // 修改密码
                this.updatePwd(request, response);
            } else if(method.equals("pwdmodify")){  // 验证旧密码是否正确
                this.pwdModify(request,response);
            } else if(method != null && method.equals("query")) {
                this.query(request,response);
            }
        }
    }

    // 修改密码
    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从Session中取ID
        Object o = request.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = request.getParameter("newpassword");

        System.out.println("UserServlet3:"+newpassword);

        boolean flag = false;

        if(o != null && (newpassword != null && !newpassword.isEmpty())){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(),newpassword);
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
        request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
    }

    // 验证旧密码,session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 从Session中取ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String, String> resultMap = new HashMap<String, String>();

        // session过期
        if(null == o ){
            resultMap.put("result", "sessionerror");
        }else if(oldpassword == null || oldpassword.isEmpty()){
            // 旧密码输入为空
            resultMap.put("result", "error");
        }else{
            String sessionPwd = ((User)o).getUserPassword();
            if(oldpassword.equals(sessionPwd)){
                resultMap.put("result", "true");
            }else{
                // 旧密码输入不正确
                resultMap.put("result", "false");
            }
        }

        resp.setContentType("application/json");
        PrintWriter outWriter = resp.getWriter();
        // JSONArray 阿里巴巴的JSON工具类
        outWriter.write(JSONArray.toJSONString(resultMap));
        outWriter.flush();
        outWriter.close();

    }

    // 用户管理
    public void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 查询用户列表
        String queryUserName = request.getParameter("queryname");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int queryUserRole = 0;
        UserService userService = new UserServiceImpl();
        List<User> userList = null;

        // 设置页面容量
        int pageSize = Constants.pageSize;

        // 当前页码
        int currentPageNo = 1;
        /**
         * http://localhost:8090/SMBMS/userlist.do
         * ----queryUserName --NULL
         * http://localhost:8090/SMBMS/userlist.do?queryname=
         * --queryUserName ---""
         */
        System.out.println("queryUserName servlet-------->"+queryUserName);
        System.out.println("queryUserRole servlet-------->"+queryUserRole);
        System.out.println("query pageIndex----------> " + pageIndex);

        if(queryUserName == null){
            queryUserName = "";
        }
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.parseInt(pageIndex);
            }catch(NumberFormatException e){
                response.sendRedirect("error.jsp");
            }
        }
        // 满足条件的数据数量
        int totalCount	= userService.getUserCount(queryUserName, queryUserRole);

        // 总页数
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);  // 数据的总数

        int totalPageCount = pages.getTotalPageCount();

        // 控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }


        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo, pageSize);
        request.setAttribute("userList", userList);
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);
        request.setAttribute("queryUserName", queryUserName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }
}