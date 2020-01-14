package com.ymdx.spring.dbcp.ext;

import com.ymdx.spring.dbcp.bean.DbBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: DataBaseConnectionPoolImpl
 * @Description: 自定义数据库连接池
 *
 * 连接池参数：
 * - 初始化连接数：initConnections
 * - 最大空闲连接数：maxConnections
 * - 最大允许的连接数：maxActiveConnections
 *
 * 定义变量：
 * 1. 空闲连接集合freeConnections
 * 2. 活动连接集合activeConnections
 * 3. 当前连接数connCount
 *
 * 实现步骤：
 * 1. 初始化initConnections个连接，暂存到空闲连接集合freeConnections中，connCount++
 *
 * 2. 获取连接
 * 2.1 判断连接总数connCount是否大于等于最大连接数maxActiveConnections：
 * 2.1.1 如果连接总数connCount大于等于最大连接数maxActiveConnections，则进行等待
 * 2.1.2 如果连接总数connCount小于最大连接数maxActiveConnections，则继续判断空闲连接数freeConnections.size()是否大于0：
 * 2.1.2.1 如果空闲连接数freeConnections.size()小于等于0，则创建新的连接
 * 2.1.2.2 如果空闲连接数freeConnections.size()大于0，则从空闲连接集合freeConnections中取出空闲线程，暂存到活动连接集合activeConnections中
 *
 * 3. 释放连接
 * 3.1 判断空闲连接池是否已满（即空闲连接数freeConnections.size()是否小于最大空闲连接数maxConnections）
 * 3.1.1 若空闲连接池已满，则直接关闭连接
 * 3.1.2 若空闲连接池未满，将连接暂存到空闲连接集合freeConnections中（连接回收重用）
 * 3.1.3 从活动连接集合activeConnections中移除需要释放的连接，connCount--
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-01-14 10:12
 * @Version: 1.0
 **/
public class DataBaseConnectionPoolImpl implements IDataBaseConnectionPool {
    /**空闲连接集合freeConnections*/
    private List<Connection> freeConnections = new Vector<>();
    /**活动连接集合activeConnections*/
    private List<Connection> activeConnections = new Vector<>();
    /**连接总数connCount*/
    private int connCount = 0;

    private DbBean dbBean;

    public DataBaseConnectionPoolImpl(DbBean dbBean){
        this.dbBean = dbBean;
        initConnections();
    }

    private void initConnections() {
        if(dbBean == null){
            throw new RuntimeException("Please check the db config!");
        }
        int initConnections = dbBean.getInitConnections();
        for (int i = 0; i < initConnections; i++) {
            Connection conn = initConnection();
            if(conn != null){
                freeConnections.add(conn);
            }
        }
    }

    private Connection initConnection() {
        try {
            Class.forName(dbBean.getDriverName());
            Connection conn = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
            connCount++;
            return conn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized Connection getConnection() {
        Connection conn = null;
        try {
            // 判断当前连接数connCount是否大于等于最大连接数maxActiveConnections
            if(connCount < dbBean.getMaxActiveConnections()){
                // 如果前连接数connCount小于最大连接数maxActiveConnections，则继续判断空闲连接数freeConnections.size()是否大于0
                if(freeConnections.size() > 0){
                    // 如果空闲连接数freeConnections.size()大于0，则从空闲连接集合freeConnections中取出空闲线程，暂存到活动连接集合activeConnections中
                    conn = freeConnections.remove(0);
                }else{
                    // 如果空闲连接数freeConnections.size()小于等于0，则创建新的连接
                    conn = initConnection();
                }
                if(isAvailable(conn)){
                    activeConnections.add(conn);
                }else{
                    connCount--;
                    conn = getConnection();
                }
            }else{
                // 如果前连接数connCount大于等于最大连接数maxActiveConnections，则进行等待
                wait(dbBean.getConnTimeOut());
                // 递归重试获取连接
                conn = getConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    private boolean isAvailable(Connection conn) {
        try {
            if(conn == null || conn.isClosed()){
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public synchronized void releaseConnection(Connection conn) {
        try {
            if(isAvailable(conn)){
                // 判断空闲连接池是否已满（即空闲连接数freeConnections.size()是否小于最大空闲连接数maxConnections）
                if(freeConnections.size() < dbBean.getMaxConnections()){
                    // 若空闲连接池未满，将连接暂存到空闲连接集合freeConnections中（连接回收重用）
                    freeConnections.add(conn);
                }else{
                    // 若空闲连接池已满，则直接关闭连接
                    conn.close();
                }
                // 从活动连接集合activeConnections中移除需要释放的连接
                activeConnections.remove(conn);
                connCount--;
                // 唤醒所有等待的连接
                notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
