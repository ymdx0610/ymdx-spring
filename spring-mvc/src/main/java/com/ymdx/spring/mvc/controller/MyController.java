package com.ymdx.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: MyController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-11 17:53
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/")
public class MyController {

    @RequestMapping("/show")
    public String show(){
        return "ok";
    }

}
