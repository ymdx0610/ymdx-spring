package com.ymdx.spring.aop.proxy;

/**
 * @ClassName: ProxySubject
 * @Description: 代理主题
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-09-30 10:19
 * @Version: 1.0.1
 **/
public class ProxySubject implements Subject {

    private Subject subject;

    public ProxySubject(Subject subject){
        this.subject = subject;
    }

    @Override
    public void request() {
        this.breforeRequest();
        subject.request();
        this.afterRequest();
    }

    // 请求后的操作
    private void afterRequest() {
        // 善后处理
    }

    // 请求前的处理
    private void breforeRequest() {
        // 预处理
    }

}
