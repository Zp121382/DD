package com.xzy;

import com.xzy.util.DBUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 引导程序(启动程序)
 * @author Invisible
 * @version 0.1
 * @date 2021/2/19 18:12
 */
public class BootStrap {
    private static Connection connection;
    /**
     * 程序启动
     * 1)加载数据库驱动
     * 2)获取连接对象
     */
    public void startUp() {
        System.out.println("程序正在启动...");
        connection = DBUtils.getConnection();
    }


    /**
     * 登录
     * @return
     * @throws Exception
     */
    public boolean login() throws Exception {
        // 提示用户(登录)
        System.out.println("欢迎使用哒哒租车系统");
        System.out.println("请输入用户名");
        String nickname = new Scanner(System.in).next();

        System.out.println("请输入密码");
        String password = new Scanner(System.in).next();

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
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set(nickname);
            System.out.println(threadLocal.get());

            return true;
        }else{
//            System.out.println("登陆失败,没有此用户");
            return false;
        }
    }


    /**
     *  列出车类型的列表
     */
    public void listCategory() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from category");
            System.out.println("类型ID\t类型");
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id+"\t"+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 选择车的类型
     * @return 车类型的ID
     */
    public Integer selectCarCategory() {
        System.out.println("请输入租用的车辆类型的ID");
        return new Scanner(System.in).nextInt();
    }
}
