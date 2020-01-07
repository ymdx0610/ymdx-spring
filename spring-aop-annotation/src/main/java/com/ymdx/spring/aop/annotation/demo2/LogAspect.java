package com.ymdx.spring.aop.annotation.demo2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogAspect
 * @Description: TODO
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
    @Before("execution(* com.ymdx.spring.aop.annotation.demo01.BizService2.process(..))")
    public void before(){
        System.out.println("before -> 前置通知");
    }

    /**
     * 后置通知
     */
    @After("execution(* com.ymdx.spring.aop.annotation.demo01.BizService2.process(..))")
    public void after(){
        // 如果处理业务的过程中发生异常，后续的通知代码也会被执行
        System.out.println("after -> 后置通知");
    }

    /**
     * 运行通知
     */
    @AfterReturning("execution(* com.ymdx.spring.aop.annotation.demo01.BizService2.process(..))")
    public void afterReturning(){
        System.out.println("afterReturning -> 运行通知");
    }

    /**
     * 异常通知
     */
    @AfterThrowing("execution(* com.ymdx.spring.aop.annotation.demo01.BizService2.process(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing -> 异常通知");
    }

    /**
     * 环绕通知
     */
    @Around("execution(* com.ymdx.spring.aop.annotation.demo01.BizService2.process(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around -> 环绕通知（业务逻辑处理前）");
        // 如果处理业务的过程中发生异常，则会立即返回，后续的通知代码将不会被执行
        joinPoint.proceed();
        System.out.println("around -> 环绕通知（业务逻辑处理后）");
    }


}
