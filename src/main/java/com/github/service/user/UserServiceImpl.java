package com.github.service.user;

import com.github.dao.BaseDao;
import com.github.dao.user.UserDao;
import com.github.dao.user.UserDaoImpl;
import com.github.pojo.User;
import org.junit.Test;

import java.sql.Connection;

public class UserServiceImpl implements UserService{
    // 业务层都会调用dao层，所以需要引入Dao层
    private UserDao userDao;

    // 构造函数：没有返回值
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    // 登录成功返回User对象，否则返回null
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        // 1. 获取对象
        try {
            // 返回一个新的连接
            connection = BaseDao.getConnection();
            // 通过业务层调用对应的具体的数据库操作，根据userCode获取User对象
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResult(connection,null,null);
        }

        // 2. 匹配密码
        if(null != user){
            if(!user.getUserPassword().equals(userPassword)) {
                user = null;
            }
        }
        return user;
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234567");
        if (admin != null){
            System.out.println("login successful!");
        }else {
            System.out.println("login failed!");
        }
//        System.out.println(admin.getUserPassword());
    }
}