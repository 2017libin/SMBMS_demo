package com.github.service.role;

import com.github.pojo.Role;

import java.util.List;

public interface RoleService {
    // 获取用户角色
    public List<Role> getRoleList();

}