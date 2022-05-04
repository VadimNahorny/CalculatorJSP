package com.example.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathOperation {

    private static final Pattern BR = Pattern.compile("\\([^()]+\\)");
    private static final Pattern FIGURE = Pattern.compile("-?\\d+\\.?\\d*");
    private static final Pattern PLSUB = Pattern.compile("(\\+-)|(--)|(-\\+)");
    private static final Pattern DIVMULT = Pattern.compile("-?\\d+\\.?\\d*[*/][\\+-]?\\d+\\.?\\d*");
    private static final Pattern PL = Pattern.compile("(\\+\\+)");



    public static double calc(String mathExpression) {
        StringBuffer sb = new StringBuffer(mathExpression);
        double result = 0;
        while (mathExpression.contains("(") || mathExpression.contains(")")) {
            sb = withoutBrackets(mathExpression);
            mathExpression = String.valueOf(sb);
        }
        result = mathOperation(String.valueOf(sb));
        return result;
    }

    private static StringBuffer withoutBrackets(String mathExpression) {
        mathExpression = clearString(mathExpression);
        if (mathExpression.matches("\\(-?\\d+\\.?\\d*\\)"))
            return new StringBuffer(mathExpression.substring(1, mathExpression.length() - 1));
        Matcher matcher = BR.matcher(mathExpression);
        StringBuffer buffer = new StringBuffer();
        String simpleExpression = null;
        while (matcher.find()) {
            simpleExpression = matcher.group();
            simpleExpression = simpleExpression.substring(1, simpleExpression.length() - 1);
            matcher.appendReplacement(buffer, String.valueOf(mathOperation(simpleExpression)));
        }
        matcher.appendTail(buffer);
        return buffer;
    }

    private static double mathOperation(String mathExpression) {
        if (mathExpression.matches("[-+]?\\d+\\.?\\d*")) return Double.parseDouble(mathExpression);

        mathExpression = clearString(mathExpression);

        while (mathExpression.contains("*") || mathExpression.contains("/"))
            mathExpression = divMulOperation(mathExpression);

        mathExpression = clearString(mathExpression);

        if (mathExpression.contains("+") || mathExpression.contains("-")) return plSubOperation(mathExpression);
        return Double.parseDouble(mathExpression);
    }

    private static String clearString(String mathExpression) {
        if (mathExpression.charAt(0) == '+') mathExpression = mathExpression.substring(1);
        if (mathExpression.contains("+-") || mathExpression.contains("--") ||
                mathExpression.contains("-+")) mathExpression = withoutplsub(mathExpression);
        if (mathExpression.contains("++"))
            mathExpression = withoutpl(mathExpression);
        return mathExpression;
    }

    private static String withoutplsub(String mathExpression) {

        Matcher matcher = PLSUB.matcher(mathExpression);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "-");
        }
        matcher.appendTail(buffer);
        return String.valueOf(buffer);
    }

    private static String withoutpl(String mathExpression) {
        Matcher matcher = PL.matcher(mathExpression);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "+");
        }
        matcher.appendTail(buffer);
        return String.valueOf(buffer);
    }

    private static String divMulOperation(String mathExpression) {
        mathExpression = clearString(mathExpression);
        Matcher matcher = DIVMULT.matcher(mathExpression);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, simpleDivMulOperation(matcher.group()));
        }
        matcher.appendTail(buffer);
        mathExpression = String.valueOf(buffer);
        if (mathExpression.contains("Infinity")) mathExpression= "Infinity";
        return mathExpression;
    }

    private static String simpleDivMulOperation(String mathExpression) {
        List<Double> twoFigure = new ArrayList<>();
        Matcher matcher = FIGURE.matcher(mathExpression);
        double result = 0;
        while (matcher.find()) {
            twoFigure.add(Double.parseDouble(matcher.group()));
        }
        if (mathExpression.contains("/")) {
            result = twoFigure.get(0) / twoFigure.get(1);
        } else if (mathExpression.contains("*")) {
            result = twoFigure.get(0) * twoFigure.get(1);
        }
        String resultString = String.valueOf(result);
        if (result > 0) {
            resultString = "+" + resultString;
        }
        return resultString;
    }

    private static double plSubOperation(String mathExpression) {
        mathExpression = clearString(mathExpression);
        StringBuffer figure = new StringBuffer("");
        double result = 0;
        char operation = 0;
        if (mathExpression.charAt(0) == '-') {
            figure.append(mathExpression.charAt(0));
            mathExpression = mathExpression.substring(1);
        }
        if (mathExpression.charAt(0) == '+') {
            mathExpression = mathExpression.substring(1);
        }

        for (int i = 0; i < mathExpression.length(); i++) {
            if (mathExpression.charAt(i) == '+' || mathExpression.charAt(i) == '-') {
                if (operation == 0) {
                    result = Double.parseDouble(String.valueOf(figure));
                } else {
                    switch (operation) {
                        case '-':
                            result = result - Double.parseDouble(String.valueOf(figure));
                            break;
                        case '+':
                            result = result + Double.parseDouble(String.valueOf(figure));
                            break;
                    }
                }
                operation = mathExpression.charAt(i);
                figure.delete(0, figure.length());

            } else if (i == (mathExpression.length() - 1)) {
                figure.append(mathExpression.charAt(i));
                switch (operation) {
                    case '-':
                        result = result - Double.parseDouble(String.valueOf(figure));
                        break;
                    case '+':
                        result = result + Double.parseDouble(String.valueOf(figure));
                        break;
                }
            } else figure.append(mathExpression.charAt(i));
        }
        return result;
    }
}