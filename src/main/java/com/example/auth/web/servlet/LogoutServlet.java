package com.example.auth.web.servlet;

import com.example.auth.Entity.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.auth.Repository.OperationRepository.InMemoryOperationStorage.getInMemoryOperationStorage;

@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final HttpSession session = req.getSession();
        getInMemoryOperationStorage().clear(((User) req.getSession().getAttribute("user")).getLogin());
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/authServ");
//        resp.sendRedirect( "/jsp/auth.jsp");
    }
}


