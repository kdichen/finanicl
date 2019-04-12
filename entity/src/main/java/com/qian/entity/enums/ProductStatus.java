package com.qian.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author :  ChenQian
 * @date : 2019/4/12 11:47
 */
@Getter
@AllArgsConstructor
public enum ProductStatus {
    /**
     *
     */
    AUDITING("审核中"),

    IN_SELL("销售中"),

    LOCKED("暂停销售"),

    FINISHED("已结束");

    private String desc;

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
