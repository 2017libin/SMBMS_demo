package com.github.dao.user;

import com.github.pojo.User;

import java.sql.Connection;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public interface UserDao {
    public User getLoginUser(Connection connection, String userCode) throws Exception;
}
