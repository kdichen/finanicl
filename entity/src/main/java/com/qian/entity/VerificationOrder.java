package com.qian.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对账
 *
 * @author : ChenQian
 * @date : 2019/4/16 9:41
 */
@Data
@Entity
public class VerificationOrder {
    @Id
    private String orderId;

    //渠道id
    private String chanId;

    private String chanUserId;

    /**
     * @see com.qian.entity.enums.OrderType
     */
    private String orderType;

    private String productId;

    private BigDecimal amount;

    private String outerOrderId;


    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;
}
