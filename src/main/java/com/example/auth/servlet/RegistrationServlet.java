package com.example.auth.servlet;

import com.example.auth.repository.CheckUniqueUser;
import com.example.auth.repository.RegistrationRepository;
import org.omg.CORBA.INVALID_ACTIVITY;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final String INVALID_DATA = "Введенные данные не соответствуют правилам. Логин должен содержать" +
            " не менее 3 символов, пароль - не менее 6; пароль не должен содержать специальных символов";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String new_user = req.getParameter("new_user");
        String distribution = req.getParameter("distribution");

        boolean isCheckUniqueUser = CheckUniqueUser.checkerUniqueUser(login);
        if (isCheckUniqueUser) {
            int isRegistered = RegistrationRepository.register(login, password, new_user, distribution);
            if (isRegistered == 0) {
                req.setAttribute("errorMessage", "Вы не заполнили все обязательные поля для регистрации. Заполните такие поля и попробуйте зарегистрироваться снова.");
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
                requestDispatcher.forward(req, resp);
            } else if (isRegistered == 1) {
                RequestDispatcher requestDispatcher1 = getServletContext().getRequestDispatcher("/index.jsp");
                requestDispatcher1.forward(req, resp);
            } else if (isRegistered == 2) {
                req.setAttribute("errorMessage", "Проблемы при регистрации!");
                RequestDispatcher requestDispatcher2 = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
                requestDispatcher2.forward(req, resp);

        } else if (isRegistered == 3) {
                req.setAttribute("errorMessage", INVALID_DATA);
                RequestDispatcher requestDispatcher2 = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
                requestDispatcher2.forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Пользователь с таким именем уже существует. Введите другое имя.");
            RequestDispatcher requestDispatcher3 = getServletContext().getRequestDispatcher("/jsp/registration.jsp");
            requestDispatcher3.forward(req, resp);
        }
    }
}

