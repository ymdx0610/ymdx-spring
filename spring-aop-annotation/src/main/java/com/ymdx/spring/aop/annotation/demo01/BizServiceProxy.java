package com.ymdx.spring.aop.annotation.demo01;

/**
 * @ClassName: BizServiceProxy
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:18
 * @Version: 1.0
 **/
public class BizServiceProxy implements BizService{

    private BizService bizService;

    public BizServiceProxy(BizService bizService){
        this.bizService = bizService;
    }

    @Override
    public void process() {
        System.out.println("业务处理前~");
        bizService.process();
        System.out.println("业务处理后~");
    }

}
