package com.lzq.service.impl;

import com.lzq.bean.Carts;
import com.lzq.bean.User;
import com.lzq.dao.CartsDao;
import com.lzq.dao.impl.CartsDaoImpl;
import com.lzq.service.CartsService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/14 1:35
 * @email 邮箱:905866484@qq.com
 * @description 描述：用户登录时数据存储到数据库
 */
public class SqlCartServiceImpl implements CartsService {
    private static CartsDao dao = new CartsDaoImpl();
    @Override
    public void addCart(HttpSession session, int goodsId, int num) {
        User user = (User) session.getAttribute("user");
        Carts carts= dao.findUserCartsExist(user.getId(), goodsId);
        if(carts != null){
            //购物车中有此商品，增加数量即可
            dao.updateCartNum(user.getId(),goodsId,num);
        }else{
            //新增商品到购物车中
            dao.addCarts(user.getId(),goodsId,num);
        }
    }

    @Override
    public int updateCartNum(HttpSession session, int goodsId, int num) {
        User user = (User) session.getAttribute("user");
        int index =  dao.updateCartNum(user.getId(),goodsId,num);
        if(index == 0){
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",count+num);
            return 200;
        }else{
            return -1;
        }
    }

    @Override
    public int cartChecked(HttpSession session, int goodsId, int checked) {
        User user = (User) session.getAttribute("user");
        return dao.updateCartChecked(user.getId(),goodsId,checked);
    }

    @Override
    public int delCarts(HttpSession session, int goodsId) {
        User user = (User) session.getAttribute("user");
        Carts carts = dao.findUserCartsExist(user.getId(), goodsId);
        int index = dao.delCarts(user.getId(),goodsId);
        if(index == 0){
            Integer count = (Integer) session.getAttribute("cartsCount");
            session.setAttribute("cartsCount",getCount(user.getId()));
            return 200;
        }else{
            return -1;
        }
    }

    public static List<Carts> getCartsList(int userId){
        return dao.findCartsByUserId(userId);
    }

    public static int getCount(int userId){
        List<Carts> cartsList = getCartsList(userId);
        int count = 0;
        for (Carts carts:cartsList) {
            count += carts.getCartNum();
        }
        return count;
    }
}
