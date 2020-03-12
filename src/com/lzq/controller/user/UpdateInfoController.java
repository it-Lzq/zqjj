package com.lzq.controller.user;

import com.lzq.bean.User;
import com.lzq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateInfo.do")
public class UpdateInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String userphone = req.getParameter("userphone");
        User user = new User();
        user.setUserphone(userphone);
        user.setEmail(email);
        user.setNickName(name);
        UserService.updateUserInfo(user);
        user = UserService.getUser(user.getUserphone());
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("mygrxx.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
