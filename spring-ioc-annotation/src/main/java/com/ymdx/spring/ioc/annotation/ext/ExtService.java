package com.ymdx.spring.ioc.annotation.ext;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName: ExtService
 * @Description: 自定义Service注解
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 19:55
 * @Version: 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExtService {
}
