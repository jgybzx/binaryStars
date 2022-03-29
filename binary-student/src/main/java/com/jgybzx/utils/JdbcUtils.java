package com.jgybzx.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jgybzx
 * @date 2020/11/23 17:21
 * @description 获取数据库连接 （遗留问题，没有读取到配置文件里边的数据）
 */
public class JdbcUtils {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/jgybzx?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai&useAffectedRows=true";
    private static final String username = "root";
    private static final String password = "root";
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";

    /*static {
        try {
            FileInputStream in = new FileInputStream("/db.properties");
            // 加载配置文件
            Properties prop = new Properties();
            prop.load(in);
            // 读取配置文件
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            driverClass = prop.getProperty("driverClass");
            // 注册驱动程序
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 获取 数据库连接对象
     */
    public static Connection getConnection() {
        try {
            // 获取数据库连接对象
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源：后创建的先释放
     *
     * @param conn Connection 对象
     * @param stmt PreparedStatement 对象
     * @param rs   ResultSet 对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
