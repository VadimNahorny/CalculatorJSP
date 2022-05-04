package com.example.auth.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class OperationStorage {


    static Map<String, List<String>> storage = new HashMap<>();

    public static List<String> workWithStorage(HttpServletRequest req,
                                               HttpServletResponse resp, String mathExpression) {
        final HttpSession session = req.getSession();
        String login = (String) req.getSession().getAttribute("password");
        String password = (String) req.getSession().getAttribute("login");
        String key = login + password;
        if (storage.containsKey(key)) {
            List<String> temporaryList = storage.get(key);
            temporaryList.add(mathExpression);
            storage.put(key, temporaryList);
        } else {
            storage.put(key, new ArrayList<>(Collections.singletonList(mathExpression)));
        }
        return storage.get(key);
    }
}

