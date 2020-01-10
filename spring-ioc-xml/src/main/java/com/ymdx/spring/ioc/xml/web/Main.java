package com.ymdx.spring.ioc.xml.web;

import com.ymdx.spring.ioc.xml.container.ExtClassPathXmlApplicationContext;
import com.ymdx.spring.ioc.xml.service.UserService;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-10 14:59
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext classPathXmlApplicationContext = new ExtClassPathXmlApplicationContext("spring-ioc.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.add();
    }

}
