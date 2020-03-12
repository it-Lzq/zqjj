package com.lzq.service;

import com.lzq.bean.Carts;
import com.lzq.bean.Goods;
import com.lzq.dao.GoodsDao;
import com.lzq.dao.impl.GoodsDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/5 22:09
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class GoodsService {
    private static GoodsDao dao = new GoodsDaoImpl();

    /**
     * 查询所有商品
     * @return 查询结果
     */
    public static List<Goods> fingAll(){
       return  dao.fingAll();
    }

    /**
     * 查询所有商品排序
     * @param orderBy
     * @return List<Goods>
     */
    public static List<Goods> findAllOrder(int orderBy){
        return dao.findAllOrder(orderBy);
    }

    /**
     * 查询一级分类商品(分页，排序)
     * @param classId1 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    public static  List<Goods> findGoodsByClass1(int classId1,int page,int size,int flag){
        return dao.findGoodsByClass1(classId1,(page-1)*size,size,flag);
    }
    /**
     * 查询二级分类商品(分页，排序)
     * @param classId2 一级分类编号
     * @param page  分页查询的页数
     * @param size 每页显示数目
     * @param flag 0 正序 1 倒序
     * @return 查询结果
     */
    public static  List<Goods> findGoodsByClass2(int classId2, int page, int size, int flag){
        return dao.findGoodsByClass2(classId2,(page-1)*size,size,flag);
    }
    /**
     * 根据用户输入的商品名称模糊查询
     * @param name 商品名称
     * @return 查询结果
     */
    public static  List<Goods> findGoodsLikeName(String name,int page,int size,int flag){
        return dao.findGoodsLikeName(name,(page-1)*size,size,flag);
    }
    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return 查询商品
     */
    public static  Goods findGoodsById(int id){
        return dao.findGoodsById(id);
    }

    /**
     * 查询购物车中的商品
     * @param cartsList 购物车
     * @return 商品集合
     */
    public static List<Goods> findGoodsByCart(List<Carts> cartsList){
        if(cartsList == null || cartsList.size() == 0) return null;
        return dao.findGoodsByCart(cartsList);
    }

    /**
     *  更改商品库存
     * @param id 传入id
     */
    void updateGoodsStock(int id,int num){dao.updateGoodsStock(id,num);}
}
