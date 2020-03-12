package com.lzq.controller.goods;



import com.lzq.bean.Goods;
import com.lzq.bean.GoodsClass;
import com.lzq.service.GoodsClassService;
import com.lzq.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findGoods.do")
public class FindGoodsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.    接收请求的参数 : 商品id
        String goodsId = request.getParameter("goodsId");
        int id = Integer.parseInt(goodsId);
        //2.    调用Service , 查询商品信息
        Goods goods = GoodsService.findGoodsById(id);
        if( goods != null){
            //2.    查询商品一级分类
            GoodsClass class1 = GoodsClassService.findById(goods.getClassid1());
            //3.    查询商品二级分类
            GoodsClass class2 = GoodsClassService.findById(goods.getClassid2());
            //4.    将查询到的三个数据存储在请求对象中
            request.setAttribute("goods",goods);
            request.setAttribute("class1",class1);
            request.setAttribute("class2",class2);
        }
        //5.    请求转发到商品详情页
        request.getRequestDispatcher("proDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }
}
