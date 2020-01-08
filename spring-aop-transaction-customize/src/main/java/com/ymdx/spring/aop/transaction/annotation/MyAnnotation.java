package com.ymdx.spring.aop.transaction.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: MyAnnotation
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-08 22:09
 * @Version: 1.0
 **/

@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String name() default "ymdx2020";
    int age() default 30;

}
