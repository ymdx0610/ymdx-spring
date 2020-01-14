package com.ymdx.spring.dbcp.ext;

import com.ymdx.spring.dbcp.bean.DbBean;

import java.sql.Connection;

/**
 * @ClassName: DataBaseConnectionPoolManager
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-14 11:52
 * @Version: 1.0
 **/
public class DataBaseConnectionPoolManager {

    private static DbBean dbBean = new DbBean();
    private static IDataBaseConnectionPool dataBaseConnectionPool = new DataBaseConnectionPoolImpl(dbBean);

    public static Connection getConnection() {
        return dataBaseConnectionPool.getConnection();
    }

    public static void releaseConnection(Connection conn) {
        dataBaseConnectionPool.releaseConnection(conn);
    }

}
