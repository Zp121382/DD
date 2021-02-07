package com.xzy.util;

import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/** 对mysql操作的封装
 * @author Invisible
 * @date 2021/2/6 18:14
 * @version 0.1
 */
public class DBUtils {
    private static  Properties prop = new Properties();
    private static  String URL="jdbc:mysql://localhost:3306/dq?serverTimezone=Asia/Shanghai";
    private static  String USER="root";
    private static  String PASSWORD="Llwxnl114";

    // 构造方法私有化-->外部不能通过新建对象来获取
    private DBUtils() {
        System.out.println("私有化方法");
    }

    //加载驱动
    static {
        try {
            //初始化配置信息
            //如果是ClassLoader().getResourceAsStream();里面的路径不需要加根
            //DBUtils.class.getClassLoader()代表的是out.production.DD这个目录
            //DBUtils.class代表的是out.production.DD.com.xzy.util目录
            //           / 代表out.production.DD的绝对路径
//            InputStream inputStream = DBUtils.class.getResourceAsStream("/mysql.properties");
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("mysql.properties");

            prop.load(inputStream);

            URL = prop.getProperty("url");
            USER = prop.getProperty("user");
            PASSWORD = prop.getProperty("password");
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("加载驱动异常");
        } catch (FileNotFoundException e) {
            System.err.println("初始化资源异常");
        } catch (IOException e) {
            System.err.println("IO异常");
        }
    }

    /**
     * 获取连接对象
     *
     * @return 连接对象
     */

    public static Connection  getConnection(){
        // 获取连接对象
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            System.err.println("获取连接对象异常" + e.getMessage());
        }
        return connection;
    }

    /**
     * 释放资源
     * @param resultSet 结果集对象
     * @param statement statement对象
     * @param connection 连接对象
     */
    public static void closeAll(ResultSet resultSet,Statement statement,Connection connection) {
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection!= null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
