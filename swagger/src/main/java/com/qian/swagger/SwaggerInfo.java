package com.qian.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : ChenQian
 * @date : 2019/4/13 22:31
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {
    private String groupName = "controller";

    private String basePackage;

    private String antPath;

    private String title = "HTTP API";

    private String description = "金融练手项目 接口文档";

    private String license = "Apache License Version 2.0";
}
