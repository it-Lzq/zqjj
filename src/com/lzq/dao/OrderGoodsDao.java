package com.lzq.dao;

import com.lzq.bean.OrderGoods;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:29
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public interface OrderGoodsDao {
    /**
            * 增加一条商品订单
     * @param orderGoods 订单
     */
    void insert(OrderGoods orderGoods);


    List<OrderGoods> getByOrderId(int orderId);
}
