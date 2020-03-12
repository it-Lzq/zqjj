package com.lzq.controller.address;

import com.lzq.bean.Address;
import com.lzq.bean.User;
import com.lzq.service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addressUpdate.do")
public class AddressUpdateController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String username = req.getParameter("username");
         String userphone = req.getParameter("userphone");
         int provinceCode = Integer.parseInt(req.getParameter("provinceCode"));
         int cityCode = Integer.parseInt(req.getParameter("cityCode"));
         int areaCode = Integer.parseInt(req.getParameter("areaCode"));
         int streeCode = Integer.parseInt(req.getParameter("streeCode"));
         String userAddress = req.getParameter("addMessage");
         int id =Integer.parseInt(req.getParameter("aid"));
         Address address= new Address(id,user.getId(),username,userphone,provinceCode,cityCode,areaCode,streeCode,userAddress,0,new Date(System.currentTimeMillis()));
        if(id == -1){
            //添加
            AddressService.insertAddress(address);

        }else{
            //修改
            AddressService.updateAddress(id,address);
        }
        resp.sendRedirect("/order.jsp");

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
