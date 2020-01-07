package com.ymdx.spring.aop.proxy.demo2;

/**
 * @ClassName: Main
 * @Description: 静态代理测试主方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:21
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        StaticProxy staticProxy = new StaticProxy(userDao);
        staticProxy.addUser();
    }
}
