package com.github.dao.user;

import com.github.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public interface UserDao {
    // 得到要登录的用户
    public User getLoginUser(Connection connection,String userCode) throws SQLException, Exception;

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String pwd)throws SQLException, Exception;

    // 根据UserName和UserRole返回满足条件的用户数量
    public int getUserCount(Connection connection, String userName, int userRole)throws Exception;

    // 根据UserName和UserRole筛选用户列表，并计算和返回第currentPageNo页的用户列表
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;

    // 增加用户信息，其中user.id是一个不存在的id
    public int add(Connection connection, User user)throws Exception;

    // 通过userId删除user
    public int deleteUserById(Connection connection, Integer delId)throws Exception;

    // 修改用户信息
    public int modify(Connection connection, User user)throws Exception;

    // 通过userId查询user
    public User getUserById(Connection connection, String id)throws Exception;

}
