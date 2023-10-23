package com.github.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author chase
 * @Date 2023/10/23
 */
@Data
public class Bill {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDesc;  // 商品描述
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private int isPayment;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private String providerName;
    private Integer providerId;
}
