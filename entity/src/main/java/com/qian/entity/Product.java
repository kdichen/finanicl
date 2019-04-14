package com.qian.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
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
@NoArgsConstructor
public class Product implements Serializable {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;
    private String createUser;
    private String updateUser;

    public Product(String id, String name, String status, BigDecimal thresholdAmount, BigDecimal stepAmount,
                   BigDecimal rewardRate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.thresholdAmount = thresholdAmount;
        this.stepAmount = stepAmount;
        this.rewardRate = rewardRate;
    }
}
