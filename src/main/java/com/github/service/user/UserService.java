package com.github.service.user;

import com.github.pojo.User;

import java.util.List;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public interface UserService {

    // 用户登录验证
    public User login(String userCode, String userPassword);

    // 根据user修改密码
    public boolean updatePwd(int id,String pwd);

    // 根据条件查询出用户表记录数
    public int getUserCount(String queryUserName, int queryUserRole);

    // 根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    // 增加用户信息
    public boolean add(User user);

    // 通过userId删除user
    public boolean deleteUserById(Integer delId);

    // 修改用户信息
    public boolean modify(User user);

    // 通过userId查询user
    public User getUserById(String id);

    // 根据userCode查询出User
    public User selectUserCodeExist(String userCode);

}
