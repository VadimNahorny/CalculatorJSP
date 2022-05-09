package com.example.auth.Repository.UserRepository;


import com.example.auth.Repository.ConnectionFabric;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AuthRepository {


    private static final String CHECK_AUTH = "select * from users where login = ? and password = ?";

    public static boolean checkAuth(String login, String password) {
        boolean result = false;
        try {
            PreparedStatement statement = new ConnectionFabric().connectionCreate().prepareStatement(CHECK_AUTH);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) result = true;
            statement.close();
        } catch (SQLException e) {
            System.out.println("Сломались на аутентификации Пользователя!");
        }
        return result;
    }
}
