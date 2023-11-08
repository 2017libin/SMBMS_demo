package com.github.service.user;

import com.github.pojo.User;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public interface UserService {

    // 用户登录验证
    public User login(String userCode, String userPassword);

    // 根据user修改密码
    public boolean updatePwd(int id,String pwd);
}
