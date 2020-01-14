package com.ymdx.spring.dbcp.ext;

import java.sql.Connection;

/**
 * @ClassName: IDataBaseConnectionPool
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-14 10:09
 * @Version: 1.0
 **/
public interface IDataBaseConnectionPool {

    public Connection getConnection();

    public void releaseConnection(Connection conn);

}
