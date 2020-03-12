package com.lzq.service;

import com.lzq.bean.Payments;
import com.lzq.dao.PayMentsDao;
import com.lzq.dao.impl.PayMentsDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:03
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class PayMentsService {
    private static PayMentsDao dao = new PayMentsDaoImpl();

    /**
     * 获取所有支付方式
     * @return 支付方式集合
     */
    public static List<Payments> getPayments(){
        return dao.getPayments();
    }

      /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
    public static Payments getById(int id){
        return dao.getById(id);
    }
}
