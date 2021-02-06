package com.xzy;

import com.xzy.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Invisible
 * @date 2021/2/6 18:01
 * @version 0.1
 */

public class App {
    public static void main(String[] args) throws SQLException {
        // 提示用户(登录)
        System.out.println("欢迎使用哒哒租车系统");
        System.out.println("请输入用户名");
        String nickname = new Scanner(System.in).next();

        System.out.println("请输入密码");
        String password = new Scanner(System.in).next();
        Connection connection = DBUtils.getConnection();
        // 要从数据库查询
        String sql = "select count(*) as total from member where nickname=? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,nickname);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        long count = resultSet.getLong("total");
        System.out.println(count);
        if(count>0){
            System.out.println("登录成功!");
        }else{
            System.out.println("登陆失败,没有此用户");
        }

        // 加载驱动
        // 连接数据库
        // 准备SQL语句
        // 执行SQL语句
        // 释放资源


    }
}












