package com.example.auth.web.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.auth.Repository.UserRepository.RegistrationRepository.getRegistrationRepository;
import static com.example.auth.Repository.UserRepository.UserValidator.checkUniqueUser;
import static com.example.auth.service.PasswordNameValidator.nullValidate;
import static com.example.auth.service.PasswordNameValidator.passwordNameValidate;


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
        if (nullValidate(login, password, new_user)) {
            if (passwordNameValidate(login, password)) {
                if (checkUniqueUser(login)) {
                    if (getRegistrationRepository().save(login, password, new_user, distribution)) {
//                        resp.sendRedirect(req.getContextPath() + "/jsp/items.jsp");
                        getServletContext().getRequestDispatcher("/jsp/auth.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorMessage", "Ошибка при регистрации нового пользователя");
                        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("errorMessage", "Пользователь с таким именем или паролем уже существует. Введите другое имя.");
                    getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("errorMessage", INVALID_DATA);
                getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Вы не заполнили все обязательные поля для регистрации. Заполните такие поля и попробуйте зарегистрироваться снова.");
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        }
    }
}

