package com.ymdx.spring.aop.annotation.demo01;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JdkProxy
 * @Description: JDK动态代理
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:48
 * @Version: 1.0
 **/
public class JdkProxy implements InvocationHandler {
    private Object targetObject;

    public JdkProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("业务处理前～");
        Object obj = method.invoke(targetObject, args);
        System.out.println("业务处理之后。");
        return obj;
    }

}
