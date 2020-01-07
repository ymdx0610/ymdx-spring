package com.ymdx.spring.aop.annotation.demo01;

/**
 * @ClassName: BizServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 15:16
 * @Version: 1.0
 **/
public class BizServiceImpl implements BizService {
    @Override
    public void process() {
        System.out.println("正在处理业务逻辑...");
    }
}
