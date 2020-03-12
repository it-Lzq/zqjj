package com.lzq.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.User;
import com.lzq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/reg.do")
public class RegistController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userPhone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        String smsCode = req.getParameter("smsCode");
        int realCode = (int)req.getSession().getAttribute("code");
        String realUserPhone = (String)req.getSession().getAttribute("userPhone");
        JSONObject josn = new JSONObject();
        if(smsCode.equals(String.valueOf(realCode)) && userPhone.equals(realUserPhone)){
            //开始注册
            User user = new User();
            user.setUserphone(userPhone);
            user.setPassword(password);
            user.setCreateTime(new Date(System.currentTimeMillis()));
            boolean flag = UserService.regist(user);
            if(flag){
                josn.put("status",200);
                josn.put("msg","注册成功");

            }else{
                josn.put("status",-1);
                josn.put("msg","该手机号已注册");
            }
        }else{
            josn.put("status",-1);
            josn.put("msg","验证码错误");
        }
        resp.getWriter().write(josn.toString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
