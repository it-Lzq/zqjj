package com.lzq.service;

import com.lzq.bean.Order;
import com.lzq.dao.OrderDao;
import com.lzq.dao.impl.OrderDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:22
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class OrderService {
    private static OrderDao dao = new OrderDaoImpl();

    public static void insertOrder(Order order){
       dao.insertOrder(order);
    }

    public static void updateOrder(Order order){dao.updateOrder(order);}

    /**
     * 查询用户订单
     * @param userId 用户id
     * @return 订单列表
     */
    public static List<Order> getByUserId(int userId){return dao.getByUserId(userId); }


    public static Map<Integer,List<Order>> getOrderMap(int userId){
        List<Order> orderList = OrderService.getByUserId(userId);
        Map<Integer,List<Order>> orderMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            List<Order> list = new ArrayList<>();
            for (Order order : orderList) {
                if(order.getStatus() == i){
                    list.add(order);
                }
            }
            orderMap.put(i,list);
        }
        ;
        return orderMap;
    }


    public static Order getById(int id){
        return dao.getById(id);
    }
}
