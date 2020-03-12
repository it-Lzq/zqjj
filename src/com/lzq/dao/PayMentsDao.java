package com.lzq.dao;

import com.lzq.bean.Payments;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/20 23:56
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public interface PayMentsDao {

    /**
     * 获取所有支付方式
     * @return 支付方式集合
     */
    List<Payments> getPayments();



    /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
    Payments getById(int id);


}
