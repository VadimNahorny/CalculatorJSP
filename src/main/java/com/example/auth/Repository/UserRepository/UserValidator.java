package com.example.auth.Repository.UserRepository;
import com.example.auth.Repository.ConnectionFabric;

import java.sql.*;

public class UserValidator {

    private static final String CHECK_AUTH = "select * from users where login = ?";

    public static boolean checkUniqueUser(String login){
        boolean result = false;
        try {
            PreparedStatement statement = new ConnectionFabric().connectionCreate().prepareStatement(CHECK_AUTH);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) result = true;
            statement.close();
            } catch (SQLException e) {
            System.out.println("Ошибка проверке уникальности пользователя!");
            e.printStackTrace();
        }
        return result;
    }
}

