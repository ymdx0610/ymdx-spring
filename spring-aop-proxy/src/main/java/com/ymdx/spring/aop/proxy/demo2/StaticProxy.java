package com.ymdx.spring.aop.proxy.demo2;

/**
 * @ClassName: StaticProxy
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:19
 * @Version: 1.0
 **/
public class StaticProxy implements IUserDao {

    private IUserDao userDao;

    public StaticProxy(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void addUser() {
        System.out.println("静态代理-开启事务");
        userDao.addUser();
        System.out.println("静态代理-提交事务");
    }

}
