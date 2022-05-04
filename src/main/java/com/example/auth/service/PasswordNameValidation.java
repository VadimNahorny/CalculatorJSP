package com.example.auth.service;

public class PasswordNameValidation {

    public static boolean passwordNameValidation (String login, String password) {
        return login.length() > 2 && password.length() > 5 && !login.contains(" ") && password.matches("[\\w&&[^_]]+");
    }
}
