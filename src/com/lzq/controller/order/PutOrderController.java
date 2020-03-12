package com.lzq.controller.order;

import com.lzq.bean.*;
import com.lzq.service.AddressService;
import com.lzq.service.GoodsService;
import com.lzq.service.OrderGoodsService;
import com.lzq.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;
@SuppressWarnings("all")
@WebServlet("/putOrder.do")
public class PutOrderController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsId = req.getParameter("goodsId");
        String num = req.getParameter("num");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Goods goods = GoodsService.findGoodsById(Integer.parseInt(goodsId));
        //订单存储
        Order order = new Order();
        order.setUserId(user.getId());
        order.setAddressId(AddressService.getDefault());
        order.setMoney(Double.parseDouble(num)*goods.getPrice());
        order.setPaymentId(1);
        order.setTransportId(1);
        order.setStatus(0);
        order.setExpressNo(UUID.randomUUID().toString());
        order.setCreateTime(new Date(System.currentTimeMillis()));
        OrderService.insertOrder(order);
        //商品订单
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(order.getId());
        orderGoods.setGoodsId(goods.getId());
        orderGoods.setGoodsNum(Integer.parseInt(num));
        orderGoods.setGoodsPrice(goods.getPrice());
        orderGoods.setGoodsImg(goods.getImgs());
        orderGoods.setGoodsName(goods.getName());
        OrderGoodsService.insert(orderGoods);
        //存储到session
        session.setAttribute("order",order);
//        Carts carts = new Carts(Integer.parseInt(goodsId));
//        carts.setCartNum(Integer.parseInt(num));
//        ArrayList<Carts> orderCarts = new ArrayList<>();
//        orderCarts.add(carts);
//        session.setAttribute("orderCarts",orderCarts);
        resp.sendRedirect("order.jsp");

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
