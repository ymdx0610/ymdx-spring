package com.ymdx.spring.ioc.annotation.service;

import com.ymdx.spring.ioc.annotation.ext.ExtService;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 19:58
 * @Version: 1.0
 **/
@ExtService
public interface OrderService {

    public void addOrder();

}
