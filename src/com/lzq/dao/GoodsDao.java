package com.lzq.dao;

import com.lzq.bean.Carts;
import com.lzq.bean.Goods;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/5 20:44
 * @email 邮箱:905866484@qq.com
 * @description 描述：商品
 */
public interface GoodsDao {

    /**
     * 查询所有商品
     * @return 查询结果
     */
    List<Goods> fingAll();

    /**
     * 查询所有商品排序
     * @param orderBy
     * @return List<Goods>
     */
    List<Goods> findAllOrder(int orderBy);

    /**
     * 查询一级分类商品(分页，排序)
     * @param class1Id 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass1(int class1Id,int page,int size,int flag);
    /**
     * 查询二级分类商品(分页，排序)
     * @param class2Id 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    List<Goods> findGoodsByClass2(int class2Id,int page,int size,int flag);
    /**
     * 根据用户输入的商品名称模糊查询
     * @param name 商品名称
     * @return 查询结果
     */
    List<Goods> findGoodsLikeName(String name,int page,int size,int flag);
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return 查询商品
     */
    Goods findGoodsById(int id);

    /**
     * 查询购物车中的商品
     * @param cartsList 购物车
     * @return 商品集合
     */
    List<Goods> findGoodsByCart(List<Carts> cartsList);

    /**
     *  更改商品库存
     * @param id 传入id
     */
    void updateGoodsStock(int id,int num);
}
