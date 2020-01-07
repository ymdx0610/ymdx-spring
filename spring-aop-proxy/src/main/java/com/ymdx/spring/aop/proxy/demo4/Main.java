package com.ymdx.spring.aop.proxy.demo4;

/**
 * @ClassName: Main
 * @Description: Cglib动态代理测试主方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 15:09
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        // 采用cglib动态代理，被代理的对象可以不基于接口；采用jdk动态代理，被代理的对象必须基于接口
        UserDao userDao = (UserDao) new CglibDynamicProxy().getProxy(new UserDao());
        userDao.addUser();
    }

}
