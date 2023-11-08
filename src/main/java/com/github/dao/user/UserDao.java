package com.github.dao.user;

import com.github.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public interface UserDao {
    // 根据userCode获取User对象
    public User getLoginUser(Connection connection, String userCode) throws Exception;

    // 修改当前用户密码
    public int updatePwd(Connection connection,int id,String pwd) throws SQLException;

}
