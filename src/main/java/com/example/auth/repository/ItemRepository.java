package com.example.auth.repository;

import com.example.auth.good.Good;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/intservice";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String GETTER = "SELECT * FROM goods;";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static List <Good> getGoods() {
        List<Good> goods = null;
        try {
            goods = new ArrayList();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GETTER);
            while (rs.next()) {
                goods.add(new Good(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("Сломались при получении товаров!");
            e.printStackTrace();
        }
        return goods;
    }


}