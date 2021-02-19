package com.xzy;

import com.xzy.entity.Car;
import com.xzy.entity.Member;
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

    // thread用于保存当前线程的共享变量
    public static ThreadLocal threadLocal = new ThreadLocal();
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
     *
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
        preparedStatement.setString(1, nickname);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        long count = resultSet.getLong("total");
        System.out.println(count);
        if (count > 0) {
            System.out.println("登录成功!");
            threadLocal.set(nickname);

            return true;
        } else {
//            System.out.println("登陆失败,没有此用户");
            return false;
        }
    }


    /**
     * 列出车类型的列表
     */
    public void listCategory() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from category");
            System.out.println("类型ID\t类型");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + "\t" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * 选择车的类型
     *
     * @return 车类型的ID
     */
    public Integer selectCarCategory() {
        System.out.println("请输入租用的车辆类型的ID");
        return new Scanner(System.in).nextInt();
    }

    /**
     * 列出类型对应的 具体车的列表
     *
     * @param categoryId
     */
    public void listCar(Integer categoryId) {
        String sql = "select car.id,car.name,car.price,car.zkl,car.zhl,category.name categoryName from car inner join category on car.category_id = category.id where car.category_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("车辆ID\t车辆名称\t车辆单价\t载客量\t载货量\t所属类型");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer price = resultSet.getInt("price");
                Integer zkl = resultSet.getInt("zkl");
                Integer zhl = resultSet.getInt("zhl");
                String categoryName = resultSet.getString("categoryName");

                System.out.println(id + "\t\t" + name + "\t" + price + "\t\t" + zkl + "\t\t" + zhl + "\t\t" + categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /**
     * 选择租用的具体的车辆
     *
     * @return
     */
    public Integer selectCar() {
        System.out.println("请输入租用的车辆具体车型的ID");
        return new Scanner(System.in).nextInt();
    }

    /**
     * 读取租用的天数
     *
     * @return
     */
    public Integer readUseDays() {
        System.out.println("请输入租用天数");
        return new Scanner(System.in).nextInt();
    }

    /**
     * 查找用户
     *
     * @param nickname 会员别名
     * @return 用户ID
     */
    public Member findMemberByNickname(String nickname) {
        String sql = "select * from member where nickname = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                return new Member(id, nickname, null);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 生成订单
     *
     * @param days
     * @param carId
     * @param totalPrice
     * @param memberId
     * @return
     */
    public Integer genOrdedrs(Integer days, Integer carId, Integer totalPrice, Integer memberId) {

        String sql = "insert into orders(days,total_price,member_id,car_id) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,days);
            preparedStatement.setInt(2,totalPrice);
            preparedStatement.setInt(3,memberId);
            preparedStatement.setInt(4,carId);
            preparedStatement.execute();

            System.out.println("生成订单成功!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    /**
     * 根据车辆ID查询车辆具体信息
     *
     * @param carId
     * @return
     */
    public Car findCarById(Integer carId) {
        String sql = "select * from car where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Car car = new Car();
            if (resultSet.next()){
                car.setId(carId);
                car.setName(resultSet.getString("name"));
                car.setPrice(resultSet.getInt("price"));
                car.setZhl(resultSet.getInt("zhl"));
                car.setZkl(resultSet.getInt("zkl"));
                return car;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}














