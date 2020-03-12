package com.lzq.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.Carts;
import com.lzq.bean.User;
import com.lzq.service.CartsService;
import com.lzq.service.UserService;
import com.lzq.service.impl.SqlCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userphone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        User user = new User();
        user.setUserphone(userphone);
        user.setPassword(password);
        boolean login = UserService.login(user);
        JSONObject json = new JSONObject();
        if(login){
            json.put("status",200);
            HttpSession session = req.getSession();
            user = UserService.getUser(userphone);
            session.setAttribute("user",user);
            //面向切面 合并购物车操作
           if(session.getAttribute("carts") != null){
               this.updateCartStatus(session);
           }
        }else{
            json.put("status",-1);
        }
        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    private void updateCartStatus(HttpSession session){
        List<Carts> cartsList = (List<Carts>) session.getAttribute("carts");
        if(cartsList != null){
            //将session中购物车商品添加到数据库中
            new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < cartsList.size(); i++) {
                        Carts carts = cartsList.get(i);
                        CartsService service = new SqlCartServiceImpl();
                        service.addCart(session,carts.getGoodsId(),carts.getCartNum());
                    }

                    session.removeAttribute("carts");
                    session.removeAttribute("cartsCount");
                }
            }.run();
        }
    }
}
