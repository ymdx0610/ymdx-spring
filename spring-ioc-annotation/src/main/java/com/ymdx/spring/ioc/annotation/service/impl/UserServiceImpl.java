package com.ymdx.spring.ioc.annotation.service.impl;

import com.ymdx.spring.ioc.annotation.ext.ExtResource;
import com.ymdx.spring.ioc.annotation.ext.ExtService;
import com.ymdx.spring.ioc.annotation.service.OrderService;
import com.ymdx.spring.ioc.annotation.service.UserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 19:59
 * @Version: 1.0
 **/
@ExtService
public class UserServiceImpl implements UserService {

    @ExtResource
    private OrderService orderServiceImpl;

    @Override
    public void addUser() {
        System.out.println("addUser（使用注解 + 反射技术创建bean）");
        orderServiceImpl.addOrder();
    }

}
