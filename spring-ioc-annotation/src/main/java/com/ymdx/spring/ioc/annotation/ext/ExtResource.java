package com.ymdx.spring.ioc.annotation.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: ExtResource
 * @Description: 自定义依赖注入注解
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 19:56
 * @Version: 1.0
 **/
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface ExtResource {
}
