package com.ymdx.spring.aop.proxy;

/**
 * @ClassName: RealSubject
 * @Description: 真实主题
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-09-30 10:19
 * @Version: 1.0.1
 **/
public class RealSubject implements Subject {
    @Override
    public void request() {
        // 业务逻辑处理
    }
}
