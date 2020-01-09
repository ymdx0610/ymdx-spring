package com.ymdx.spring.aop.transaction.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Log
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 19:32
 * @Version: 1.0
 **/
@Data
public class Log {

    private Long id;
    private String content;
    private Date createTime;

}
