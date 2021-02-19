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


    public static void main(String[] args) throws Exception {
        // 创建引导程序
        BootStrap bootStrapApp = new BootStrap();



        // 启动程序
        bootStrapApp.startUp();

        // 用户验证
        if (!bootStrapApp.login()){
            System.out.println("登陆失败");
        }
        System.out.println("登陆成功");

        // 列出车的类型列表

        bootStrapApp.listCategory();

        // 选择车的类型,返回类型ID
        Integer categoryId =  bootStrapApp.selectCarCategory();
        System.out.println(categoryId);



    }



}












