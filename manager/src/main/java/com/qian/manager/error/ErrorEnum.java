package com.qian.manager.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ChenQian
 * @date : 2019/4/13 14:09
 */

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /**
     * 错误种类
     */
    ID_NOT_NULL("F001", "编号不可为空", false),
    //...
    UNKNOWN("999", "未知异常", false);
    private String code;
    private String message;
    private boolean canRetry;

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (errorEnum.code.equals(code)) {
                return errorEnum;
            }
        }
        return UNKNOWN;
    }
}
