package com.lzq.dao;

import com.lzq.bean.Payments;
import com.lzq.bean.Transports;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:05
 * @email 邮箱:905866484@qq.com
 * @description 描述：快递
 */
public interface TransportsDao {

    /**
     * 获取所有的快递方式
     * @return 快递
     */
    List<Transports> getTransports();

    /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
   Transports getById(int id);
}
