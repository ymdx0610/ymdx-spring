package com.ymdx.spring.aop.transaction.service.impl;

import com.ymdx.spring.aop.transaction.service.UserService;
import com.ymdx.spring.aop.transaction.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-08 10:48
 * @Version: 1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionUtil transactionUtil;

//    @Override
//    public void add() {
//        TransactionStatus ts = null;
//        try {
//            // 开启事务
//            ts = transactionUtil.begin();
//
//            String sql = "insert into t_user(name, age) values(?, ?);";
//            int ymdx001 = jdbcTemplate.update(sql, "ymdx001", 18);
//            if (ymdx001 > 0)
//                System.out.println("ymdx001插入成功！");
////            int a = 1/0;
//            System.out.println("--------------------------");
//            int ymdx002 = jdbcTemplate.update(sql, "ymdx002", 19);
//            if (ymdx002 > 0)
//                System.out.println("ymdx002插入成功！");
//
//            // 提交事务
//            if (null != ts)
//                transactionUtil.commit(ts);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            // 回滚事务
//            if (null != ts)
//                transactionUtil.rollback(ts);
//
//        }
//    }

    @Override
    public void add() {
        String sql = "insert into t_user(name, age) values(?, ?);";
        int ymdx001 = jdbcTemplate.update(sql, "ymdx001", 18);
        if (ymdx001 > 0)
            System.out.println("ymdx001插入成功！");
        int a = 1 / 0;
        System.out.println("--------------------------");
        int ymdx002 = jdbcTemplate.update(sql, "ymdx002", 19);
        if (ymdx002 > 0)
            System.out.println("ymdx002插入成功！");
    }

}
