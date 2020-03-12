package com.lzq.dao;

import com.lzq.bean.GoodsClass;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/4 20:55
 * @email 邮箱:905866484@qq.com
 * @description 描述：商品分类表的数据访问对象模板
 */
public interface GoodsClassDao {

    /**
     * 用于查询所有分类
     * @return 商品分类表list
     */
    List<GoodsClass> findAll();

    /**
     * 查询商品分类
     * @param id 根据id
     * @return GoodsClass
     */
    GoodsClass findById(int id);
}
