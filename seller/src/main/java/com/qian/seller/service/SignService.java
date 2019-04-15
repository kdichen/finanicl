package com.qian.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ChenQian
 * @date : 2019/4/15 22:44
 */
@Service
public class SignService {
    private static Map<String, String> PUBLIC_KEYS = new HashMap<>();

    static {
        PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTZ4ULJ14o6CgMrcwy9h4zkg4g\n" +
                "t7D1D/dhvaJHXmx8Fuf7kd0iqBzeJOcHktH5ALJN9x2JnqMWIwxmY20NX8H04irp\n" +
                "J+yUW4j2O2CJr6ZUb60OjKGQaTiBAL3RUCr/KYqDS7gmJHSKF+7T0sstdQO+ilwS\n" +
                "AZzsgeKqDpNFJqw30wIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     *
     * @param authId
     * @return
     */
    public String getPublicKey(String authId) {
        return PUBLIC_KEYS.get(authId);
    }
}
