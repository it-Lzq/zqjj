package com.lzq.dao;

import com.lzq.bean.Carts;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/15 14:55
 * @email 邮箱:905866484@qq.com
 * @description 描述：购物车dao
 */
public interface CartsDao {

    /**
     * 查询命苦个用户的购物车中是否拥有此商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 购物
     */
    Carts findUserCartsExist(int userId, int goodsId);

    /**
     * 查询某个用户的购物车
     * @param userId 用户id
     * @return 购物车商品集合
     */
    List<Carts> findCartsByUserId(int userId);

    /**
     * 将用户购物车商品保存到数据库添加一行数据
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num 商品数量
     */
    void addCarts(int userId,int goodsId,int num);

    /**
     * 改变用户购物车的数量
     * @param userId 用户id
     * @param goodsId 商品id
     * @param num +1 或 -1
     * @return 是否成功
     */
    int updateCartNum(int userId,int goodsId,int num);
    /**
     * 改变用户购物车的选中效果
     * @param userId 用户id
     * @param goodsId 商品id
     * @param checked 0 或 -1
     * @return 是否成功
     */
    int updateCartChecked(int userId,int goodsId,int checked);


    /**
     * 删除商品
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 删除结果
     */
    int delCarts(int userId,int goodsId);
}
