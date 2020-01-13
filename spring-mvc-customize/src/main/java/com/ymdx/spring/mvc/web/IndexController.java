package com.ymdx.spring.mvc.web;

import com.ymdx.spring.mvc.annotation.ExtController;
import com.ymdx.spring.mvc.annotation.ExtRequestMapping;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-13 13:49
 * @Version: 1.0
 **/
@ExtController
@ExtRequestMapping("/ext")
public class IndexController {

    @ExtRequestMapping("/test")
    public String index(){
        return "ok";
    }

}
