package com.ymdx.spring.ioc.annotation.web;

import com.ymdx.spring.ioc.annotation.container.MyContainer;
import com.ymdx.spring.ioc.annotation.service.UserService;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 20:04
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        MyContainer myContainer = new MyContainer("com.ymdx.spring.ioc.annotation.service.impl");
        UserService userService = (UserService) myContainer.getBean("userServiceImpl");
        userService.addUser();
    }

}
