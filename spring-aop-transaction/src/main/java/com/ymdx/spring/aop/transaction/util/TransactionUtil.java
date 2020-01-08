package com.ymdx.spring.aop.transaction.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName: TransactionUtil
 * @Description: 事务工具类
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-08 11:28
 * @Version: 1.0
 **/
@Component
@Scope("prototype") // 设置成原型，解决线程安全问题
public class TransactionUtil {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     */
    public TransactionStatus begin(){
        TransactionStatus ts = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return ts;
    }

    /**
     * 提交事务
     */
    public void commit(TransactionStatus ts){
        dataSourceTransactionManager.commit(ts);
    }

    /**
     * 回滚事务
     */
    public void rollback(TransactionStatus ts){
        dataSourceTransactionManager.rollback(ts);
    }

}
