package com.lzq.controller.order;

import com.lzq.bean.*;
import com.lzq.dao.impl.CartsDaoImpl;
import com.lzq.service.*;
import com.lzq.service.impl.SqlCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("all")
@WebServlet("/putOrders.do")
public class PutOrdersController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idMsg = req.getParameter("idMsg");
        String priceA = req.getParameter("priceA");
        String[] ids = idMsg.split("-");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        List<Carts> cartsList = SqlCartServiceImpl.getCartsList(user.getId());

        Order order = new Order();
        order.setUserId(user.getId());
        order.setAddressId(AddressService.getDefault());
        order.setMoney(Double.parseDouble(priceA));
        order.setPaymentId(1);
        order.setTransportId(1);
        order.setStatus(0);
        order.setCreateTime(new Date(System.currentTimeMillis()));
        order.setExpressNo(UUID.randomUUID().toString());
        OrderService.insertOrder(order);

        for (int i = 0; i < ids.length; i++) {
            Carts carts = new Carts(Integer.parseInt(ids[i]));
            int index = cartsList.indexOf(carts);
            carts = cartsList.get(index);
            Goods goods = GoodsService.findGoodsById(Integer.parseInt(ids[i]));
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(goods.getId());
            orderGoods.setGoodsNum(carts.getCartNum());
            orderGoods.setGoodsPrice(goods.getPrice());
            orderGoods.setGoodsImg(goods.getImgs());
            orderGoods.setGoodsName(goods.getName());
            OrderGoodsService.insert(orderGoods);
            //移出购物车
            new CartsDaoImpl().delCarts(user.getId(),goods.getId());

        }
        session.setAttribute("order",order);
        resp.sendRedirect("order.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
