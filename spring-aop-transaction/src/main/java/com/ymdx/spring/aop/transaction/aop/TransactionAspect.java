package com.ymdx.spring.aop.transaction.aop;

import com.ymdx.spring.aop.transaction.util.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @ClassName: TransactionAspect
 * @Description: 事务切面
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-08 11:41
 * @Version: 1.0
 **/
@Component
@Aspect
public class TransactionAspect {

    @Autowired
    private TransactionUtil transactionUtil;

    @Around("execution(* com.ymdx.spring.aop.transaction.service.impl.UserServiceImpl.add(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("开启事务...");
        TransactionStatus ts = transactionUtil.begin();
        joinPoint.proceed();
        System.out.println("提交事务...");
        transactionUtil.commit(ts);
    }

    @AfterThrowing("execution(* com.ymdx.spring.aop.transaction.service.impl.UserServiceImpl.add(..))")
    public void afterThrowing(){
        System.out.println("回滚事务...");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}
