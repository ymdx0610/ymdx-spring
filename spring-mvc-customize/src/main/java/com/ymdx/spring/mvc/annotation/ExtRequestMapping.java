package com.ymdx.spring.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ExtRequestMapping
 * @Description: 自定义映射注解
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-13 13:42
 * @Version: 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtRequestMapping {
    String value() default "";
}
