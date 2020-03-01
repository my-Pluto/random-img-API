package com.himoyi.img_api.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    //加载hikaricp的配置信息
    private static HikariConfig hikariConfig = new HikariConfig("D:\\SoftwareData\\IDEA\\img_api\\src\\main\\resources\\hikari.properties");
    //获取数据库连接池
    private static HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
    //多线程支持
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection con = connectionThreadLocal.get();
        if (con != null)
            return con;
        return hikariDataSource.getConnection();
    }

    /**
     * 获取数据库连接池
     * @return
     * @throws SQLException
     */
    public static DataSource get_DataSource() throws SQLException {
        return hikariDataSource;
    }

    public static void startTransaction() throws SQLException {
        Connection con = connectionThreadLocal.get();
        if (con != null) {
            throw new RuntimeException("已经开启事务，请勿重复开启！");
        }
        con = hikariDataSource.getConnection();
        //将连接的自动提交改为false
        con.setAutoCommit(false);
        connectionThreadLocal.set(con);
    }

    /**
     * 手动提交
     * 在开启事务时使用
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        Connection con = connectionThreadLocal.get();
        if (con == null) {
            throw new RuntimeException("未开启事务，无需提交！");
        }

        con.commit();
        con.close();
        connectionThreadLocal.set(con);
    }

    /**
     * 事务回滚
     * 在事务执行失败时执行
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection con = connectionThreadLocal.get();
        if (con == null) {
            throw new RuntimeException("未开启事务，无需回滚！");
        }

        con.rollback();
        con.close();
        connectionThreadLocal.remove();
    }

    /**
     * 释放连接
     * 只释放单个连接
     * @param con
     * @throws SQLException
     */
    public static void release(Connection con) throws SQLException {
        if (connectionThreadLocal.get() == null)
            con.close();
        if (con != connectionThreadLocal.get())
            con.close();
    }
}
