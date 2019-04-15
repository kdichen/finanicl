package com.qian.seller.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qian.seller.sign.SignText;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 下单请求参数
 *
 * @author : ChenQian
 * @date : 2019/4/15 21:04
 */
@Data
public class OrderParam implements SignText {

    /**
     * 渠道ID
     */
    private String chanId;

    private String chanUserId;

    private String productId;

    private BigDecimal amount;

    private String outerOrderId;

    private String memo;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt;
}
