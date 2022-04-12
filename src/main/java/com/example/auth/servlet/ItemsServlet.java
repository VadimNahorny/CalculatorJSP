package com.example.auth.servlet;

import com.example.auth.repository.OrderRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.example.auth.repository.ItemRepository.getGoods;
import static com.example.auth.repository.OrderRepository.makeOrder;

@WebServlet("/itemsServ")
public class ItemsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("goods", getGoods());
        getServletContext().getRequestDispatcher("/jsp/items.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        System.out.println(name);
        String phone = request.getParameter("phone");
        System.out.println(phone);
        String goods = request.getParameter("goodsServ");
        System.out.println(goods);
        int orderIsMade = OrderRepository.makeOrder(name,phone,goods);
        if (orderIsMade == 0) {
            request.setAttribute("goods", getGoods());
            request.setAttribute("errorMessage", "Вы не заполнили все обязательные поля для создания заказа либо не выбрали товар. Заполните такие поля, выберите товар и попробуйте создать заказ снова.");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/items.jsp");
            requestDispatcher.forward(request, response);
        } else if (orderIsMade == 1) {
            RequestDispatcher requestDispatcher1 = getServletContext().getRequestDispatcher("/jsp/end.jsp");
            requestDispatcher1.forward(request, response);
        } else if (orderIsMade == 2) {
            request.setAttribute("goods", getGoods());
            request.setAttribute("errorMessage", "Проблемы при создании заказа!");
            RequestDispatcher requestDispatcher2 = getServletContext().getRequestDispatcher("/jsp/items.jsp");
            requestDispatcher2.forward(request, response);
        }
    }
}