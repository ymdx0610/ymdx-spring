package com.ymdx.spring.ioc.xml.service.impl;

import com.ymdx.spring.ioc.xml.service.UserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 14:58
 * @Version: 1.0
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("使用反射技术创建Bean - UserServiceImpl");
    }
}
