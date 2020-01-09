package com.ymdx.spring.aop.transaction.aop;

import com.ymdx.spring.aop.transaction.annotation.ExtTransactional;
import com.ymdx.spring.aop.transaction.util.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * @ClassName: TransactionAspect
 * @Description: 事务切面
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 13:52
 * @Version: 1.0
 **/
@Component
@Aspect
public class TransactionAspect {
    
    @Autowired
    private TransactionUtil transactionUtil;

    @AfterThrowing("execution(* com.ymdx.spring.aop.transaction.*.*.*.*(..))")
    public void afterThrowing(){
        // 回滚事务
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        transactionUtil.rollback();
    }
    
    @Around("execution(* com.ymdx.spring.aop.transaction.*.*.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取自定义的事务注解
        ExtTransactional extTransactional = getExtTransactional(pjp);

        // 开启事务
        TransactionStatus ts = begin(extTransactional);

        // 正常业务处理
        pjp.proceed();

        // 提交事务
        commit(ts);
    }

    /**
     * 提交事务
     * @param ts
     */
    private void commit(TransactionStatus ts){
        if(null != ts){
            transactionUtil.commit();
        }
    }

    /**
     * 开启事务，并返回事务状态
     * @param extTransactional
     * @return
     */
    private TransactionStatus begin(ExtTransactional extTransactional){
        if(null == extTransactional){
            return null;
        }
        return transactionUtil.begin();
    }

    /**
     * 获取事务注解
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    private ExtTransactional getExtTransactional(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        // 获取方法名称
        String methodName = pjp.getSignature().getName();
        // 获取目标对象
        Class<?> targetClass = pjp.getTarget().getClass();
        // 获取目标对象方法参数类型
        Class<?>[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method targetObjMethod = targetClass.getMethod(methodName, parameterTypes);
        // 判断是否有自定义事务注解
        return targetObjMethod.getDeclaredAnnotation(ExtTransactional.class);
    }


}
