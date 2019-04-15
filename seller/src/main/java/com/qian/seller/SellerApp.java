package com.qian.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 销售端启动类
 * @author : ChenQian
 * @date : 2019/4/14 11:39
 */
@SpringBootApplication
@EnableCaching
public class SellerApp {
    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }
}
