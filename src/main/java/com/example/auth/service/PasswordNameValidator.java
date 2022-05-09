package com.example.auth.service;

public class PasswordNameValidator {

    public static boolean passwordNameValidate (String login, String password) {
        return login.length() > 2 && password.length() > 5 && !login.contains(" ") && password.matches("[\\w&&[^_]]+");
    }

    public static boolean nullValidate (String login, String password, String new_user) {
        if (password.equals("")|| login.equals("")||new_user==null) {
            return false;
        }
        return true;
    }

}
