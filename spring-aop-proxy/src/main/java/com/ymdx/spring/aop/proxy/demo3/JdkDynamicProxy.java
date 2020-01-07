package com.ymdx.spring.aop.proxy.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JdkDynamicProxy
 * @Description: JDK 动态代理示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:26
 * @Version: 1.0
 **/
public class JdkDynamicProxy implements InvocationHandler {

    private Object targetObject;

    public JdkDynamicProxy(Object targetObject){
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Jdk动态代理-开启事务");
        Object object = method.invoke(targetObject, args);
        System.out.println("Jdk动态代理-提交事务");
        return object;
    }

}
