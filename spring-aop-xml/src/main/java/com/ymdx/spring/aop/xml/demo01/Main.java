package com.ymdx.spring.aop.xml.demo01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 17:09
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        BizService bizService = (BizService) classPathXmlApplicationContext.getBean("bizService");
        bizService.process();
    }

}
