package com.github.dao.user;

import com.github.dao.BaseDao;
import com.github.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // 根据用户名或角色查询用户总数
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count=0;

        if(connection!=null){
            StringBuffer sql = new StringBuffer();
            // count(1) 统计行数
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            // 存放参数
            List<Object> list = new ArrayList<Object>();

            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                // index:0
                list.add("%" + userName + "%");
            }

            if(userRole>0){
                sql.append(" and u.userRole = ?");
                // index:1
                list.add(userRole);
            }

            // 将List转换为数组
            Object[] prams = list.toArray();
            // 输出最后拼接后的sql语句
            System.out.println("sql----->" + sql.toString());
            rs = BaseDao.execute(connection,pstm,rs,sql.toString(),prams);

            // 从结果集中获取最后的数量
            if(rs.next()){
                count = rs.getInt("count");
            }

            BaseDao.closeResult(null,pstm,rs);
        }

        return count;
    }
}
