package com.qian.manager.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 错误处理相关配置
 *
 * @author : ChenQian
 * @date : 2019/4/13 14:26
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public ErrorController basicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                                                ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        return new ErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolversProvider.getIfAvailable());
    }
}
