package com.lzq.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.User;
import com.lzq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updatePass.do")
public class UpdatePassController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("pass");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.setPassword(pass);
        UserService.update(user);
        JSONObject json = new JSONObject();
        json.put("msg","密码修改成功");
        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
