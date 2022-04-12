package com.example.auth.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/intservice";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String CHECK_AUTH = "select * from users where login = ? and password = ?";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkAuth(String login, String password) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_AUTH);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) result = true;
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Сломались на аутентификации Пользователя!");
        }
        return result;
    }
}
