package com.qian.seller.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 对账测试
 *
 * @author : ChenQian
 * @date : 2019/4/16 10:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyServiceTest {
    @Autowired
    private VerifyService verifyService;

    /**
     * 生成对账文件
     */
    @Test
    public void makeVerificationTest() {
        Date day = new GregorianCalendar(2018, 11, 30).getTime();
        File file = verifyService.makeVerificationFile("111", day);
        System.out.println(file.getAbsolutePath());
    }

    /**
     * 解析对账文件并入库
     */
    @Test
    public void saveVerificationOrders() {
        Date day = new GregorianCalendar(2018, 11, 30).getTime();
        verifyService.saveChanOrders("111", day);
    }

}