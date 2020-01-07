package com.ymdx.spring.aop.proxy.demo3;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-27 14:18
 * @Version: 1.0
 **/
public class UserDaoImpl implements IUserDao {
    @Override
    public void addUser() {
        System.out.println("调用添加方法...");
    }
}
