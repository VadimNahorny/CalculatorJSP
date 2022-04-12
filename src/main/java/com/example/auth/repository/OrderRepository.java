package com.example.auth.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/intservice";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String MAKE_ORDER = "insert into `order_feedback` (name_customer, phone, goods) values (?, ?, ?);";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static int makeOrder(String name, String phone, String goods) {
        int result = 2;
        try {
            PreparedStatement statement = connection.prepareStatement(MAKE_ORDER);
         if (name.equals("")|| phone.equals("")||goods.equals("")) {
             result = 0;
             return result;
         }
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, goods);
            if (statement.executeUpdate() == 1) result = 1;
            statement.close();
        } catch (SQLException e) {
            System.out.println("Сломались при регистрации нового Пользователя!");
            e.printStackTrace();
        }
        return result;
    }
}