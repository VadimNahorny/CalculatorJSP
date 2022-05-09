package com.example.auth.web.servlet;

import com.example.auth.Entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.auth.Repository.UserRepository.AuthRepository.checkAuth;
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
       User user = new User(req.getParameter("login"), req.getParameter("password"));
       if (checkAuth(user.getLogin(), user.getPassword())) {
            setPropertyPath(req, resp);
            logger.info(FOR_LOG + user.getLogin() + ";");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/calculator");
        } else {
            req.setAttribute("errorMessage", "Неправильный логин или пароль!");
            req.getServletContext().getRequestDispatcher("/jsp/auth.jsp").forward(req, resp);
        }
    }
}


