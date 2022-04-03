package com.example.auth.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/intservice";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String REGISTER = "insert into users(login, password, new_user, distribution) values (?, ?, ?, ?)";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static int register(String login, String password, String new_user, String distribution) {
        int result = 2;
        try {
            PreparedStatement statement = connection.prepareStatement(REGISTER);

            if (password==null|| login==null||new_user==null) {
                result = 0;
                return result;
            }
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setBoolean(3, Boolean.parseBoolean(new_user));
            if (distribution==null) distribution="false";
            statement.setBoolean(4, Boolean.parseBoolean (distribution));
            if (statement.executeUpdate() == 1) result = 1;
            statement.close();
        } catch (SQLException e) {
            System.out.println("Сломались при регистрации нового Пользователя!");
            e.printStackTrace();
        }
        return result;
    }
}
