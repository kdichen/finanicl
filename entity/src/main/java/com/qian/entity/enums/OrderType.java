package com.qian.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author :  ChenQian
 * @date : 2019/4/12 11:47
 */
@Getter
@AllArgsConstructor
public enum OrderType {
    /**
     */
    APPLY("申购"), REDEEM("赎回");

    private String desc;

}
