package com.ymdx.spring.aop.xml.demo01;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogAspect
 * @Description: 日志切面类
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 16:22
 * @Version: 1.0
 **/
@Component
@Aspect
public class LogAspect {

    /**
     * 前置通知
     */
    public void before(){
        System.out.println("before -> 前置通知");
    }

    /**
     * 后置通知
     */
    public void after(){
        System.out.println("after -> 后置通知");
    }

    /**
     * 运行通知
     */
    public void afterReturning(){
        System.out.println("afterReturning -> 运行通知");
    }

    /**
     * 异常通知
     */
    public void afterThrowing(){
        System.out.println("afterThrowing -> 异常通知");
    }

    /**
     * 环绕通知
     */
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around -> 环绕通知（业务逻辑处理前）");
        // 如果处理业务的过程中发生异常，则会立即返回，后续的代码将不会被执行
        joinPoint.proceed();
        System.out.println("around -> 环绕通知（业务逻辑处理后）");
    }


}
