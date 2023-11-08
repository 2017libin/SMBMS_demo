package com.github.dao.user;

import com.github.dao.BaseDao;
import com.github.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author libin
 * @Date 2023/10/30
 */
public class UserDaoImpl implements UserDao {
    public User getLoginUser(Connection connection, String userCode) throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        if (connection != null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            resultSet = BaseDao.execute(connection, preparedStatement, resultSet, sql, params);
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResult(null, preparedStatement, resultSet);
        }
        return user;
    }

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String pwd) throws SQLException {
        System.out.println("UserServlet1:"+pwd);

        PreparedStatement pstm = null;
        int flag = 0;
        if(connection != null){
            String sql = "update smbms_user set userPassword= ? where id = ?";
            Object[] params = {pwd,id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResult(null, pstm, null);
        }
        return flag;
    }
}
