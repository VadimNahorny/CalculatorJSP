package com.example.auth.servlet;

import com.example.auth.service.MathOperation;
import com.example.auth.service.OperationStorage;
import com.example.auth.service.StringValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calculator", value= "/calculator")
    public class CalcServlet extends HttpServlet {
    private static final String LAST_OPERAND = "Введенное Вами выражение недопустимо: операнд не может быть последним.";
    private static final String INCORRECT_BRACKETS = "Введенное Вами выражение недопустимо: неправильно расставлены скобки.";
    private static final String LAST_POINT = "Введенное Вами выражение недопустимо: точка не может быть последней.";
    private static final String EMPTY_EXPRESSION = "Вы не ввели выражение. Введите выражение.";
    private static final String ARITHMETIC_EXCEPTION = "Результатом вычисления Вашего выражения является деление на ноль, что недопустимо.";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/calculator.jsp").forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String task = request.getParameter("hidden");
        System.out.println(task);
        int check = StringValidation.stringValidation(task);
        if (check > 0 && check < 5) {
            switch (check) {
                case 1:
                    request.setAttribute("errorMessage", LAST_OPERAND);
                    break;
                case 2:
                    request.setAttribute("errorMessage", INCORRECT_BRACKETS);
                    break;
                case 3:
                    request.setAttribute("errorMessage", LAST_POINT);
                    break;
                case 4:
                    request.setAttribute("errorMessage", EMPTY_EXPRESSION);
                    break;
            }
            request.setAttribute("mathExpression", task);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/calculator.jsp");
            requestDispatcher.forward(request, response);
        } else {
            double result = MathOperation.calc(task);


            if (String.valueOf(result).equals("Infinity")) {
                request.setAttribute("errorMessage", ARITHMETIC_EXCEPTION);
                request.setAttribute("mathExpression", task);
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/calculator.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("mathExpression", result);
                String fullExpression = task + " = " + result;
                request.setAttribute("previousOperations", OperationStorage.workWithStorage(request,response,fullExpression));
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/calculator.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}

