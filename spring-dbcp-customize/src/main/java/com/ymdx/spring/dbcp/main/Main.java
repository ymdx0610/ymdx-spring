package com.ymdx.spring.dbcp.main;

import com.ymdx.spring.dbcp.ext.DataBaseConnectionPoolManager;

import java.sql.Connection;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-14 11:51
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        DBCPThread dbcpThread = new DBCPThread();
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(dbcpThread, "用户线程" + i);
            thread.start();
        }
    }

}

class DBCPThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Connection connection = DataBaseConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName() + "，connection：" + connection);
            DataBaseConnectionPoolManager.releaseConnection(connection);
        }
    }
}