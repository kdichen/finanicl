package com.qian.seller.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 渠道配置信息
 *
 * @author : ChenQian
 * @date : 2019/4/16 10:59
 */
@Getter
@AllArgsConstructor
public enum ChanEnum {

    /**
     * 模拟数据
     */
    ABC("111", "ABC", "D:\\javaPoj\\ABC");

    private String chanId;
    private String chanName;

    private String ftpPath, ftpUser, ftpPwd;

    private String rootDir;

    ChanEnum(String chanId, String chanName, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.rootDir = rootDir;
    }

    /**
     * 根据渠道编号获取渠道配置
     *
     * @param chanId 渠道ID
     * @return 渠道配置
     */
    public static ChanEnum getByChanId(String chanId) {
        for (ChanEnum chanEnum : ChanEnum.values()) {
            if (chanEnum.getChanId().equals(chanId)) {
                return chanEnum;
            }
        }
        return null;
    }

}
