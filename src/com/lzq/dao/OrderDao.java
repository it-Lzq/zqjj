package com.lzq.dao;

import com.lzq.bean.Order;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:01
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public interface OrderDao {
    /**
     * 添加订单
     * @param order
     */
    void insertOrder(Order order);

    /**
     * 更改订单
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 查询用户订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> getByUserId(int userId);


    Order getById(int id);


}
