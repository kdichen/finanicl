package com.qian.manager;

import com.qian.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Import(SwaggerConfiguration.class) 导入swagger配置类
 * @author : ChenQian
 * @date : 2019/4/12 16:42
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.qian.entity"})
@EnableMySwagger
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class);
    }
}
