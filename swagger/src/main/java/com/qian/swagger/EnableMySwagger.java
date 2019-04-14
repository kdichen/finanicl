package com.qian.swagger;

/**
 * 开启swagger文档自动生成功能
 *
 * @author : ChenQian
 * @date : 2019/4/13 22:34
 */

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Documented
@Import(SwaggerConfiguration.class)
@EnableSwagger2
public @interface EnableMySwagger {
}
