package com.lzq.controller.pay;

import com.lzq.bean.Order;
import com.lzq.service.OrderService;
import com.lzq.service.PayMentsService;
import com.lzq.service.TransportsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Transport.do")
public class ChangeTransportsController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Order order = (Order) req.getSession().getAttribute("order");
        order.setTransportId(Integer.parseInt(id));
        OrderService.updateOrder(order);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
