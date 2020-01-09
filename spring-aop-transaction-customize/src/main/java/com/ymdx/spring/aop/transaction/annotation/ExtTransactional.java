package com.ymdx.spring.aop.transaction.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: ExtTransactional
 * @Description: 自定义事务注解
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 13:37
 * @Version: 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExtTransactional {



}
