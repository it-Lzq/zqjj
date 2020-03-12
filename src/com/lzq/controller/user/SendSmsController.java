package com.lzq.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lzq.util.SendSms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sms.do")
public class SendSmsController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String status = req.getParameter("status");
        String userPhone = req.getParameter("userPhone");
        int code = SendSms.random();
        System.out.println(status+"-"+userPhone+"  "+code);
        JSONObject json = new JSONObject();
        if(status.equals("1")){
            boolean flag =SendSms.send(userPhone, code);
            if(flag){
                json.put("status",200);
                json.put("msg","验证码发送成功");
                json.put("realCode",code);
                req.getSession().setAttribute("userPhone",userPhone);
                req.getSession().setAttribute("code",code);

            }else{
                json.put("status",-1);
                json.put("msg","验证码发送失败，请检查您的手机号");
            }
        }

        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
