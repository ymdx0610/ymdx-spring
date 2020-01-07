package com.ymdx.spring.aop.annotation.demo01;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibProxy
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:58
 * @Version: 1.0
 **/
public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    public Object getProxy(Object targetObject){
        this.targetObject = targetObject;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("业务处理前～");
        Object obj = method.invoke(targetObject, params);
        System.out.println("业务处理后。");
        return obj;
    }
}
