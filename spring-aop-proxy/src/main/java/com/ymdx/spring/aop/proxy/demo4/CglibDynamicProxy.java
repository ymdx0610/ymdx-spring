package com.ymdx.spring.aop.proxy.demo4;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibDynamicProxy
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:59
 * @Version: 1.0
 **/
public class CglibDynamicProxy implements MethodInterceptor {
    private Object targetObject;

    public Object getProxy(Object targetObject) {
        this.targetObject = targetObject;

        // 操作字节码，创建虚拟子类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理-开启事务");
        Object object = method.invoke(targetObject, args);
        System.out.println("Cglib动态代理-提交事务");
        return object;
    }

}
