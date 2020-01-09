package com.ymdx.spring.aop.transaction.service.impl;

import com.ymdx.spring.aop.transaction.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName: LogServiceImpl
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 19:34
 * @Version: 1.0
 **/
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * - PROPAGATION_REQUIRED：如果当前有事务，就用当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。（默认）
     * - PROPAGATION_SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。// 如果外层方法没有事务，就会以非事务方式执行。
     * - PROPAGATION_MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。 
     * - PROPAGATION_REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。 
     * - PROPAGATION_NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。// 如果当前有事务，就会以非事务进行执行。
     * - PROPAGATION_NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。
     * - PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
     *
     * @param content
     */
    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void addLog(String content) {
        String sql = "insert into t_log(content,create_time) values(?,?)";
        jdbcTemplate.update(sql, content, new Date());
    }

}
