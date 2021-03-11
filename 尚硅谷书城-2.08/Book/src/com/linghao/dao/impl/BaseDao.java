package com.linghao.dao.impl;

import com.linghao.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zoulinghao
 * @create 2021-01-30-14:39
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    /**
     * update方法用来执行 INSERT DELETE UPDATE语句
     * @return影响的行数 如果返回-1则说明执行失败了
     * 返回其他表示影响的行数
     */
    public int update(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @param type  返回的对象类型
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回的类型的泛型
     * @return    返回查询到的一个对象 如果没查到返回null
     */
    public <T> T  queryForOne(Class<T> type,String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }
    public <T> List<T> queryForList(Class<T> type ,String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    /**
     * 执行返回一行一列的参数
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler<>(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
