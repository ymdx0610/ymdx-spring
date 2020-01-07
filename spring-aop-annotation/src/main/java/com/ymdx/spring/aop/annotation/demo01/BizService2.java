package com.ymdx.spring.aop.annotation.demo01;

import org.springframework.stereotype.Service;

/**
 * @ClassName: BizService2
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-07 16:15
 * @Version: 1.0
 **/
@Service("bizService2")
public class BizService2 {
    public void process(){
//        int a = 1/0;
        System.out.println("正在执行业务2处理...");
    }
}
