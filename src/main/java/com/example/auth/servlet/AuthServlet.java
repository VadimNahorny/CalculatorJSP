package com.example.auth.servlet;

import com.example.auth.repository.AuthRepository;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.auth.service.ProjectPath.setPropertyPath;


@WebServlet(name = "authServ", value = "/authServ")
public class AuthServlet extends HttpServlet {
    private static final String FOR_LOG = "input user with login: ";
    static Logger logger = Logger.getLogger(AuthServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isAuth = AuthRepository.checkAuth(login, password);
        if (isAuth) {
            setPropertyPath(req,resp);
            logger.info(FOR_LOG + login + ";");
            final HttpSession session = req.getSession();
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getServletContext().getRequestDispatcher("/jsp/calculator.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Неправильный логин или пароль!");
            req.getServletContext().getRequestDispatcher("/jsp/auth.jsp").forward(req, resp);
        }
    }
}


