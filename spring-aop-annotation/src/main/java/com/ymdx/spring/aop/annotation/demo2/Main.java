package com.ymdx.spring.aop.annotation.demo2;

import com.ymdx.spring.aop.annotation.demo01.BizService2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 16:36
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        BizService2 bizService2 = (BizService2) classPathXmlApplicationContext.getBean("bizService2");
        bizService2.process();
    }

}
