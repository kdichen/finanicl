package com.qian.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品
 *
 * @author :  ChenQian
 * @date : 2019/4/12 11:47
 */
@Data
@Entity
public class Product {

    @Id
    private String id;
    private String name;
    /**
     * @see com.qian.entity.enums.ProductStatus
     */
    @ApiModelProperty(value = "状态", dataType = "com.qian.entity.enums.ProductStatus")
    private String status;
    /**
     * 起投金额
     */
    private BigDecimal thresholdAmount;
    /**
     * 投资步长
     */
    private BigDecimal stepAmount;
    /**
     * 锁定期
     */
    private Integer lockTerm;
    /**
     * 收益率，因为要与其他数相乘，所以使用BigDecimal
     */
    private BigDecimal rewardRate;
    private String memo;
    private Date createAt;
    private Date updateAt;
    private String createUser;
    private String updateUser;
}
