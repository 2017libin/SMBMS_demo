package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author libin
 * @Date 2023/10/23
 */
@Data
public class Provider {
    private Integer id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String proPhone;
    private String proAddress;
    private String proFax;
    private Integer createdBy;
    private Date creationDate;
    private Date modifyDate;
    private Integer modifyBy;
}
