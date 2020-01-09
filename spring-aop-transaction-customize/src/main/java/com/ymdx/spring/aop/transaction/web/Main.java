package com.ymdx.spring.aop.transaction.web;

import com.ymdx.spring.aop.transaction.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 15:34
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userService");
        userService.add();
    }

}
