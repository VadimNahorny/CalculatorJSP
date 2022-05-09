package com.example.auth.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "authFilter", value = "/*")
public class AuthFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user") == null) {
            sessionIsNull(httpServletRequest, httpServletResponse);
        } else sessionNotNull(httpServletRequest, httpServletResponse);
    }


    public static void sessionNotNull(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (req.getServletPath().equals("/registration") || req.getServletPath().equals("/authServ")) {
            req.getServletContext().getRequestDispatcher("/calculator").forward(req, res);
        } else {
            req.getServletContext().getRequestDispatcher(req.getServletPath()).forward(req, res);
        }
    }

//    private void sessionIsNull(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        System.out.println("не авторизован");
//        System.out.println(req.getRequestURL());
//        System.out.println(req.getServletPath());
//        res.sendRedirect(req.getContextPath() + "/jsp/auth.jsp");
//    }

    public static void sessionIsNull(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        if (req.getServletPath().equals("/registration"))
            req.getServletContext().getRequestDispatcher("/registration").forward(req, res);
        req.getServletContext().getRequestDispatcher("/authServ").forward(req, res);
    }
}



