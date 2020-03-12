package com.lzq.controller.carts;

import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/cartChecked.do")
public class CartCheckedController extends HttpServlet {
    private CartsService service = null;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodsId = req.getParameter("goodsId");
        String checked = req.getParameter("checked");
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        if(session.getAttribute("user") == null){
            //未登录
            service = new SessionCartsServiceImpl();
        }else{
            //已登录
            service = new SqlCartServiceImpl();
        }
        //执行修改选中
        int status = service.cartChecked(session, Integer.parseInt(goodsId), Integer.parseInt(checked));
        //设置响应json
        json.put("status",status);
        //返回json
        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
