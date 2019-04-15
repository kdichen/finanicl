package com.qian.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : ChenQian
 * @date : 2019/4/14 11:27
 */
@JsonDeserialize(as = ProductRpcReq.class)
public interface ParamInf {
    /**
     * 获取ID列表
     *
     * @return
     */
    List<String> getIdList();

    /**
     * 获取最小投资率
     *
     * @return
     */
    BigDecimal getMinRewardRate();

    /**
     * 获取最大投资率
     *
     * @return
     */
    BigDecimal getMaxRewardRate();

    /**
     * 获取状态列表
     *
     * @return
     */
    List<String> getStatusList();
}
