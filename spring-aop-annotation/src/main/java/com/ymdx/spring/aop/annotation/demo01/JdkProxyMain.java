package com.ymdx.spring.aop.annotation.demo01;

import java.lang.reflect.Proxy;

/**
 * @ClassName: JdkProxyMain
 * @Description: JDK动态代理
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:52
 * @Version: 1.0
 **/
public class JdkProxyMain {
    public static void main(String[] args) {
        BizService bizService = new BizServiceImpl();
        JdkProxy jdkProxy = new JdkProxy(bizService);

        BizService proxyInstance = (BizService) Proxy.newProxyInstance(bizService.getClass().getClassLoader(), bizService.getClass().getInterfaces(), jdkProxy);
        proxyInstance.process();

    }
}
