package com.qian.seller.task;

import com.qian.seller.enums.ChanEnum;
import com.qian.seller.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时对账任务
 *
 * @author : ChenQian
 * @date : 2019/4/16 15:12
 */
@Component
public class VerifyTask {
    @Autowired
    private VerifyService verifyService;

    /**
     * 每天1,3,5点生成对账文件
     */
    @Scheduled(cron = "0 0 1,3,5 * * ? ")
    public void makeVerificationFile() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verifyService.makeVerificationFile(chanEnum.getChanId(), yesterday);
        }
    }

    /**
     * 每天2,4,6点对账
     */
    @Scheduled(cron = "0 0 2,4,6 * * ? ")
    public void verify() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verifyService.verifyOrder(chanEnum.getChanId(), yesterday);
        }
    }
}
