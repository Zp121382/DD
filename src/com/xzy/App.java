package com.xzy;

import com.xzy.entity.Car;
import com.xzy.entity.Member;
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

//        System.out.println(categoryId);
        // 列出类型对应的车的列表
        bootStrapApp.listCar(categoryId);


        // 选择具体的车型
        Integer carId =  bootStrapApp.selectCar();

        // 请输入租用的天数
        Integer days =  bootStrapApp.readUseDays();

    // 生成订单 返回订单ID
        String nickname = (String) BootStrap.threadLocal.get();// 从全局的共享变量获取当前登录(下单)的用户
        //查询会员
        Member member = bootStrapApp.findMemberByNickname(nickname);
        //查询具体车辆
        Car car = bootStrapApp.findCarById(carId);
        Integer ordedrsId = bootStrapApp.genOrdedrs(days,carId, days*car.getPrice(),member.getId());

    }
}












