package com.qian.seller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.qian.util.JsonUtil;

/**
 * 签名明文
 *
 * @author : ChenQian
 * @date : 2019/4/15 21:05
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {
    /**
     * 签名
     *
     * @return 签名后json格式
     */
    default String toText() {
        return JsonUtil.toJson(this);
    }
}
