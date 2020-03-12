package com.lzq.controller.address;

import com.alibaba.fastjson.JSONObject;
import com.lzq.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setDefault.do")
public class SetDefaultController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean flag = AddressService.setDefault(Integer.parseInt(id));
        JSONObject json = new JSONObject();
        if(flag){
            json.put("status",200);
            resp.getWriter().write(json.toJSONString());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
