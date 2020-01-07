package com.ymdx.spring.aop.annotation.demo01;

/**
 * @ClassName: CglibProxyMain
 * @Description: CGLIB动态代理
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 16:03
 * @Version: 1.0
 **/
public class CglibProxyMain {

    public static void main(String[] args) {
        BizService2 bizService = (BizService2) new CglibProxy().getProxy(new BizService2());
        bizService.process();
    }

}
