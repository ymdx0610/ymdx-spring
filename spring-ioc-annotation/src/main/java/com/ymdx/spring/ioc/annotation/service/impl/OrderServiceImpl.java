package com.ymdx.spring.ioc.annotation.service.impl;

import com.ymdx.spring.ioc.annotation.ext.ExtService;
import com.ymdx.spring.ioc.annotation.service.OrderService;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 19:59
 * @Version: 1.0
 **/
@ExtService
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder() {
        System.out.println("addOrder（使用注解 + 反射技术实现依赖注入）");
    }
}
