package com.lzq.service;

import com.lzq.bean.Transports;
import com.lzq.dao.TransportsDao;
import com.lzq.dao.impl.TransportsDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:08
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class TransportsService {
    private static TransportsDao dao = new TransportsDaoImpl();
    /**
     * 获取所有的快递方式
     * @return 快递
     */
    public static List<Transports> getTransports(){
        return dao.getTransports();
    }

    public static Transports getById(int id){
        return dao.getById(id);
    }
}
