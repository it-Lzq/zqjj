package com.lzq.controller.carts;

import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/delCarts.do")
public class DelCartsController extends HttpServlet {
    private CartsService service = null;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsId = req.getParameter("goodsId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        JSONObject json = new JSONObject();
        int status = -1;
        if(user == null){
            //未登录
            service = new SessionCartsServiceImpl();
            //删除商品
            status = service.delCarts(session, Integer.parseInt(goodsId));
            json.put("cartsCount",session.getAttribute("cartsCount"));
        }else{
            //已登录
            service = new SqlCartServiceImpl();
            status = service.delCarts(session, Integer.parseInt(goodsId));
            json.put("cartsCount",SqlCartServiceImpl.getCount(user.getId()));
        }
        //设置响应json
        json.put("status",status);
        //返回json
        resp.getWriter().write(json.toJSONString());

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
