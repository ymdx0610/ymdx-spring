package com.ymdx.spring.aop.proxy.demo3;

import java.lang.reflect.Proxy;

/**
 * @ClassName: Main
 * @Description: JDK动态代理测试主方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:29
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(userDao);
        IUserDao proxyInstance = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), jdkDynamicProxy);
        proxyInstance.addUser();
    }

}
