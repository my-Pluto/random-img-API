package com.himoyi.img_api.utils;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库操作加强类
 * 将connection改为手动获取
 */
class TxQueryRunner extends QueryRunner {
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        int[] ints = super.batch(con, sql, params);
        JDBCUtils.release(con);
        return ints;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        T t = super.query(con, sql, rsh, params);
        JDBCUtils.release(con);
        return t;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        T t = super.query(con, sql, rsh);
        JDBCUtils.release(con);
        return t;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        int i = super.update(con, sql);
        JDBCUtils.release(con);
        return i;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        int i = super.update(con, sql, param);
        JDBCUtils.release(con);
        return i;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        int i = super.update(con, sql, params);
        JDBCUtils.release(con);
        return i;
    }

    @Override
    public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        T t = super.insert(con, sql, rsh);
        JDBCUtils.release(con);
        return t;
    }

    @Override
    public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        T t = super.insert(con, sql, rsh, params);
        JDBCUtils.release(con);
        return t;
    }

    @Override
    public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        T t = super.insertBatch(con, sql, rsh, params);
        JDBCUtils.release(con);
        return t;
    }

    @Override
    public int execute(String sql, Object... params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        int i = super.execute(con, sql, params);
        JDBCUtils.release(con);
        return i;
    }

    @Override
    public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection con = JDBCUtils.getConnection();
        List<T> list = super.execute(con, sql, rsh, params);
        JDBCUtils.release(con);
        return list;
    }
}

