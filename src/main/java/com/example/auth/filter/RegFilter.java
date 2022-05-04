package com.example.auth.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "regFilter", value = "/regFilter")
public class RegFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        final String login = servletRequest.getParameter("login");
        final String password = servletRequest.getParameter("password");
        System.out.println(login);
        final HttpSession session = httpServletRequest.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            sessionNotNull(httpServletRequest, httpServletResponse);
        } else {
            sessionIsNull(httpServletRequest, httpServletResponse);
        }
    }

    private void sessionNotNull(final HttpServletRequest req, final HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("/calculator").forward(req, res);
    }

    private void sessionIsNull(final HttpServletRequest req, final HttpServletResponse res)
            throws IOException, ServletException {
        req.getRequestDispatcher("/registration").forward(req, res);
    }
}



