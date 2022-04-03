package com.example.auth.repository;

import java.sql.*;

public class CheckUniqueUser {


    private static final String URL = "jdbc:mysql://localhost:3306/intservice";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String CHECK_AUTH = "select * from users where login = ?";

    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkerUniqueUser(String login){
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_AUTH);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            if (!resultSet.next()) result = true;
            resultSet.close();
            statement.close();
            } catch (SQLException e) {
            System.out.println("Сломались на проверке уникальности пользователя!");
            e.printStackTrace();
        }
        return result;
    }
}

