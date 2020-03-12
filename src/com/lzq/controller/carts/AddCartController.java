package com.lzq.controller.carts;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.Carts;
import com.lzq.bean.User;
import com.lzq.service.CartsService;
import com.lzq.service.impl.SessionCartsServiceImpl;
import com.lzq.service.impl.SqlCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addCart.do")
public class AddCartController extends HttpServlet {
    CartsService service = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsId = req.getParameter("goodsId");
        String num = req.getParameter("num");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        if(session.getAttribute("user") == null){
            //用户未登录
            service = new SessionCartsServiceImpl();
        }else{
            //用户已登录
            service = new SqlCartServiceImpl();
        }
        //添加商品
        service.addCart(session,Integer.parseInt(goodsId),Integer.parseInt(num));
        //更改商品数量
        Integer count = (Integer) session.getAttribute("cartsCount");
        if(count == null){
            count = 0;
        }
        count += Integer.parseInt(num);
        session.setAttribute("cartsCount",count);
        //返回json
        json.put("statua",200);
        json.put("msg","添加成功");
        resp.getWriter().write(json.toJSONString());

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }



}
