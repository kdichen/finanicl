package com.qian.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author :  ChenQian
 * @date : 2019/4/12 11:47
 */
@Data
@Entity(name = "order_t")
public class Order {
    @Id
    private String orderId;

    /**
     * 渠道id
     */
    private String chanId;

    private String chanUserId;

    /**
     * @see com.qian.entity.enums.OrderType
     */
    private String orderType;

    private String productId;

    private BigDecimal amount;

    private String outerOrderId;

    /**
     * @see com.qian.entity.enums.OrderStatus
     */
    private String orderStatus;

    private String memo;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updateAt;
}
