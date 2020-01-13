package com.ymdx.spring.mvc.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ExtController
 * @Description: 自定义控制器注解
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-13 13:40
 * @Version: 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtController {
    String value() default "";
}
