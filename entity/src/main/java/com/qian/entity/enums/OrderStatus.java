package com.qian.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author :  ChenQian
 * @date : 2019/4/12 11:47
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    /**
     *
     */
    INIT("初始化"), PROCESS("处理中"), SUCCESS("成功"), FAIL("失败");

    private String desc;

}
