package com.example.auth.Repository.UserRepository;

import com.example.auth.Repository.ConnectionFabric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationRepository {
    private static RegistrationRepository registrationRepository;

    public static RegistrationRepository getRegistrationRepository() {
        if (registrationRepository == null) registrationRepository = new RegistrationRepository();
        return registrationRepository;
    }

    private static final String REGISTER = "insert into users(login, password, new_user, distribution) values (?, ?, ?, ?)";

    public boolean save(String login, String password, String new_user, String distribution) {
        boolean result = false;
        try {
            PreparedStatement statement = new ConnectionFabric().connectionCreate().prepareStatement(REGISTER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setBoolean(3, Boolean.parseBoolean(new_user));
            if (distribution == null) distribution = "false";
            statement.setBoolean(4, Boolean.parseBoolean(distribution));
            if (statement.executeUpdate() == 1) result = true;
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при регистрации нового пользователя!");
            e.printStackTrace();
        }
        return result;
    }
}
