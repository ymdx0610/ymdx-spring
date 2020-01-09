package com.ymdx.spring.aop.transaction.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName: TransactionUtil
 * @Description: 自定义封装手动事务
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-09 13:39
 * @Version: 1.0
 **/
@Component
@Scope("prototype") // 每个事务都创建新的实例，避免多线程环境下线程安全问题
public class TransactionUtil {

    private TransactionStatus ts = null;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     */
    public TransactionStatus begin(){
        ts = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return ts;
    }

    /**
     * 提交事务
     */
    public void commit(){
        if(null != ts)
            dataSourceTransactionManager.commit(ts);
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        if(null != ts)
            dataSourceTransactionManager.rollback(ts);
    }

}
