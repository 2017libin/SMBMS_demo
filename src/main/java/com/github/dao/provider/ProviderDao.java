package com.github.dao.provider;

import com.github.pojo.Provider;

import java.sql.Connection;
import java.util.List;

// 供应商管理接口
public interface ProviderDao {

    // 增加供应商
    public int add(Connection connection, Provider provider)throws Exception;

    // 通过供应商名称、编码获取供应商列表-模糊查询-providerList
    public List<Provider> getProviderList(Connection connection, String proName, String proCode)throws Exception;

    // 通过proId删除Provider
    public int deleteProviderById(Connection connection, String delId)throws Exception;

    // 通过proId获取Provider
    public Provider getProviderById(Connection connection, String id)throws Exception;

    // 修改用户信息
    public int modify(Connection connection, Provider provider)throws Exception;

}