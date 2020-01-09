package com.ymdx.spring.aop.transaction.service.impl;

import com.ymdx.spring.aop.transaction.annotation.ExtTransactional;
import com.ymdx.spring.aop.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 13:48
 * @Version: 1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @ExtTransactional
    public void add() {
        String sql = "insert into t_user(name,age) values(?,?);";
        int test01 = jdbcTemplate.update(sql, "test01", 66);
        if(test01 > 0)
            System.out.println("test01插入成功！");

//        int a = 1/0;

        int test02 = jdbcTemplate.update(sql, "test02", 88);
        if(test02 > 0)
            System.out.println("test02插入成功！");

    }

}
