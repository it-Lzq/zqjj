package com.lzq.service;

import com.lzq.bean.GoodsClass;
import com.lzq.dao.GoodsClassDao;
import com.lzq.dao.impl.GoodsClassDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/4 21:27
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class GoodsClassService {

    private static GoodsClassDao dao = new GoodsClassDaoImpl();

    //查询所有分类
    public static List<GoodsClass> findAll(){
        return dao.findAll();
    }

    //查询单个分类
    public static GoodsClass findById(int id){
        return dao.findById(id);
    }
}
