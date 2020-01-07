package com.ymdx.spring.aop.annotation.demo01;

/**
 * @ClassName: StaticProxyMain
 * @Description: 静态代理
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:20
 * @Version: 1.0
 **/
public class StaticProxyMain {

    public static void main(String[] args) {
        BizService bizService = new BizServiceImpl();
        BizServiceProxy bizServiceProxy = new BizServiceProxy(bizService);
        bizServiceProxy.process();
    }

}
