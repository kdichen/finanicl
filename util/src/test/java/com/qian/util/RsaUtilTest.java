package com.qian.util;

import org.junit.Test;

/**
 * 测试加签验签
 *
 * @author : ChenQian
 * @date : 2019/4/15 20:39
 */

public class RsaUtilTest {

    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTZ4ULJ14o6CgMrcwy9h4zkg4g\n" +
            "t7D1D/dhvaJHXmx8Fuf7kd0iqBzeJOcHktH5ALJN9x2JnqMWIwxmY20NX8H04irp\n" +
            "J+yUW4j2O2CJr6ZUb60OjKGQaTiBAL3RUCr/KYqDS7gmJHSKF+7T0sstdQO+ilwS\n" +
            "AZzsgeKqDpNFJqw30wIDAQAB";

    public static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANNnhQsnXijoKAyt\n" +
            "zDL2HjOSDiC3sPUP92G9okdebHwW5/uR3SKoHN4k5weS0fkAsk33HYmeoxYjDGZj\n" +
            "bQ1fwfTiKukn7JRbiPY7YImvplRvrQ6MoZBpOIEAvdFQKv8pioNLuCYkdIoX7tPS\n" +
            "yy11A76KXBIBnOyB4qoOk0UmrDfTAgMBAAECgYA5O+qSJXthmdDV9eu11FpC3gUD\n" +
            "KJg5ELSJ+rxHlDHMuqrduhTjgB4oqlb9/urn/h9H6mJN0uwWXzDJO5vXphgtRV6H\n" +
            "vdUdLQof8TS00KuXb6esJqow1I0ufe8ZwU/bDrp/lHzk3eIm289v9j6nRul49+Dm\n" +
            "Q+vIvuT4phFThli0+QJBAOxsoPAcyCMw0nahJUEjYF3GFFNJGgC4z9F6IynBmVn/\n" +
            "n16Gm2OUAaprSgmgFu+AqtnYmnDD6Bpg7cmG73in3l0CQQDk6I4hyCpC1Et8E+Xr\n" +
            "aSsq8rXbs5HTVvI5s604fvK5BsfzTeNrB2RQgkHlAYNCR7TctYfqY3mqyihDsTnw\n" +
            "YivvAkEAyr6pEOANvSfcBEFS2lZFCIWZ3oY810ulY8UyrFdKc8RjXcNfM/izqPpS\n" +
            "xohTwtV7Lk+yuWs2+pAOupiNedGzPQJAaoqO9Mw8V0SGKab9QXd+nwu+dxZ6DfqO\n" +
            "3SpyujPj9xhf4i9jZQJYv1wRHWx50lSbqopXS1GnZEWZPgBz7h1TQwJBAJtehWh+\n" +
            "SKDNeZmtLnrHjC0uu3F+8MrBkaJ+gSzezW5Duuv+yEKOIvE1GF4aiESEVqMgWD4O\n" +
            "JfjoOE7C586gXOQ=";

    @Test
    public void signTest() {
        String text = "{\"amount\":10,\"chanId\":\"111\",\"chanUserId\":\"112\",\"createAt\":\"2019-12-364 " +
                "02:08:42\",\"memo\":\"1001\",\"outerOrderId\":\"T001\",\"productId\":\"T001\"}";
        String sign = RsaUtil.sign(text, privateKey);
        System.out.println(sign);
        // 验签结果
        System.out.println(RsaUtil.verify(text, sign, publicKey));

    }

}