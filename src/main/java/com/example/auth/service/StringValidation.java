package com.example.auth.service;

import java.util.Stack;

public class StringValidation {


    public static int stringValidation(String mathExpression) {
        int a = 0;
        if (mathExpression.equals("")) a = 4;
        else if (mathExpression.charAt(mathExpression.length() - 1) == '+' || mathExpression.charAt(mathExpression.length() - 1) == '-' ||
                mathExpression.charAt(mathExpression.length() - 1) == '*' || mathExpression.charAt(mathExpression.length() - 1) == '/')
            a = 1;
        else if (!bracketsValidation(mathExpression)) a = 2;
        else if (mathExpression.charAt(mathExpression.length() - 1) == '.') a = 3;
        return a;
    }

    public static boolean bracketsValidation(String mathExpression) {
        boolean a = true;
        if (mathExpression.contains(")") || mathExpression.contains("(")) {
            Stack<Character> c = new Stack<>();
            StringBuffer bracketExpression = new StringBuffer("");
            for (int i = 0; i < mathExpression.length(); i++)
                if (mathExpression.charAt(i) == '(' || mathExpression.charAt(i) == ')')
                    bracketExpression.append(mathExpression.charAt(i));

            if (bracketExpression.charAt(0) == ')' ||
                    bracketExpression.charAt(bracketExpression.length() - 1) == '(' || bracketExpression.length() % 2 != 0)
                return false;

            for (int i = 0; i < bracketExpression.length(); i++) {
                if (bracketExpression.charAt(i) == ')') {
                    if (c.size() == 0) return false;
                    else c.pop();
                } else {
                    c.push(bracketExpression.charAt(i));
                }
            }
            a = c.isEmpty();
        }
        return a;
    }
}

