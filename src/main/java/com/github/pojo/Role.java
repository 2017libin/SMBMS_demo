package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author chase
 * @Date 2023/10/23
 */
@Data
public class Role {
    private Integer id;
    private String roleCode;
    private String roleName;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
