package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author chase
 * @Date 2023/10/22
 */
@Data  // 通过Data注解来自动生成属性的getter/setter、toString方法
public class User {
    private Integer id;
    private String userCode;
    private String userName;
    private String userPassword;
    private Integer gender;
    private Date birthday;
    private String phone;
    private String address;
    private Integer userRole;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer age;
    private String userRoleName;
    public Integer getAge(){
        Date date = new Date();
        Integer age = date.getYear() - birthday.getYear();
        return age;
    }
}
