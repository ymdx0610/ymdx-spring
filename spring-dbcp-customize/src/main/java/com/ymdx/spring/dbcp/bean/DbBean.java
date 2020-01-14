package com.ymdx.spring.dbcp.bean;

import lombok.Data;

/**
 * @ClassName: DbBean
 * @Description: 外部配置文件信息
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-14 10:05
 * @Version: 1.0
 **/
@Data
public class DbBean {
    /**链接属性*/
    private String driverName = "com.mysql.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/test";

    private String userName = "root";

    private String password = "123456";

    /**连接池名字*/
    private String poolName = "thread01";

    /**空闲池，最小连接数*/
    private int minConnections = 1;

    /**空闲池，最大连接数*/
    private int maxConnections = 10;

    /**初始化连接数*/
    private int initConnections = 5;

    /**重复获得连接的频率*/
    private long connTimeOut = 1000;

    /**最大允许的连接数，和数据库对应*/
    private int maxActiveConnections = 100;

    /**连接超时时间，默认20分钟*/
    private long connectionTimeOut = 1000 * 60 * 20;

}
