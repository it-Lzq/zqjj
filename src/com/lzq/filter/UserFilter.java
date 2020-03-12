package com.lzq.filter;

import com.lzq.bean.User;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/mygxin.jsp","/mygrxx.jsp","/order.jsp","/putOrders.do","/putOrder.do","/myorderq.jsp","/address.jsp","/orderxq.jsp","/remima.jsp"})
public class UserFilter extends HttpFilter {
    public void destroy() {
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            chain.doFilter(req, resp);
        }else{
            resp.sendRedirect("login.html");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
