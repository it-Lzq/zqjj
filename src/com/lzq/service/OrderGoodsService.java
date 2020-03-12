package com.lzq.service;

import com.lzq.bean.OrderGoods;
import com.lzq.dao.OrderGoodsDao;
import com.lzq.dao.impl.OrderGoodsDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:37
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class OrderGoodsService {

    private static OrderGoodsDao dao = new OrderGoodsDaoImpl();


    public static void insert(OrderGoods orderGoods){
        dao.insert(orderGoods);
    }

    public static List<OrderGoods> getByOrderId(int orderId){
        return dao.getByOrderId(orderId);
    }


}
