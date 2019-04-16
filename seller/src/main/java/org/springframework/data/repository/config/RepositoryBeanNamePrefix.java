package org.springframework.data.repository.config;

import java.lang.annotation.*;

/**
 * repository bean 名称的前缀
 * @author : ChenQian
 * @date : 2019/4/16 16:19
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RepositoryBeanNamePrefix {
    String value();
}
