package com.himoyi.img_api.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static HikariConfig hikariConfig = new HikariConfig("D:\\SoftwareData\\IDEA\\img_api\\src\\main\\resources\\hikari.properties");
    private static HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

    public static Connection get_Connection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public static void demo() throws SQLException {
        Connection connection = JDBCUtils.get_Connection();
        System.out.println(connection);
    }
}
