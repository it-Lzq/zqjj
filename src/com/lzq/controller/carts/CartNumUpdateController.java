package com.lzq.controller.carts;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.Carts;
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

@WebServlet("/cartNumUpdate.do")
public class CartNumUpdateController extends HttpServlet {
    private CartsService service;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品id，修改数量，session域对象
        String goodsId = req.getParameter("goodsId");
        String cartNum = req.getParameter("cartNum");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        if(session.getAttribute("user")==null){
            //用户未登录
            service = new SessionCartsServiceImpl();
        }else{
            //用户已登录
            service = new SqlCartServiceImpl();
        }
        //修改数量
        int status = service.updateCartNum(session,Integer.parseInt(goodsId),Integer.parseInt(cartNum));
        //设置响应json
        json.put("status",status);
        //返回json
        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
