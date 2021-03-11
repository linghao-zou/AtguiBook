package com.linghao.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zoulinghao
 * @create 2021-01-30-14:19
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();
    static{
        Properties properties=new Properties();
        InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource =(DruidDataSource)  DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库池中的连接
     * @return 如果返回 null 就说明获取连接失败 有值就是获取连接成功。
     */
    public static Connection getConnection() {

        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                //保存到threadLocal中供后面的jdbc操作使用
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
//    public static void close(Connection conn){
//        if (conn!=null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
    public static void commitAndClose(){
        Connection connection=conns.get();
        if (connection!=null){
            //如果不等于null则说明之前使用过该连接 所以提交且关闭
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        conns.remove();

        //一定要执行remove操作 否则就会出错
        //因为Tomcat底层使用了线程池操作
    }
    public static void rollbackAndClose(){
        Connection connection=conns.get();
        if (connection!=null){
            //如果不等于null则说明之前使用过该连接 所以提交且关闭
            try {
                connection.rollback();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        conns.remove();

        //一定要执行remove操作 否则就会出错
        //因为Tomcat底层使用了线程池操作
    }

}
