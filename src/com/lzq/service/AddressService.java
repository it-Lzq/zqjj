package com.lzq.service;

import com.lzq.bean.Address;
import com.lzq.dao.AddressDao;
import com.lzq.dao.impl.AddressDaoImpl;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/18 23:07
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class AddressService {
    private static AddressDao dao = new AddressDaoImpl();
    /**
     * 查询用户的所有收货地址
     * @param userId 用户id
     * @return 用户地址集合
     */
    public static List<Address> findByUserId(int userId){
       return  dao.findByUserId(userId);
    }

    /**
     * 查询某个收获地址
     * @param id 地址id
     * @return 查询地址
     */
    public static Address findAddressById(int id){
        return dao.findAddressById(id);
    }

    /**
     * 地址信息的插入
     * @param address 地址对象
     * @return 插入结果
     */
    public static boolean insertAddress(Address address){
        return dao.insertAddress(address);
    }
    /**
     * 地址信息的修改
     * @param newAddress 地址对象
     * @return 修改结果
     */
    public static boolean updateAddress(int id,Address newAddress){
        return dao.updateAddress(id,newAddress);
    }
    /**
     * 地址信息的删除
     * @param id 地址id
     * @return 删除结果
     */
    public static boolean deleteAddress(int id){
        return dao.deleteAddress(id);
    }

    public static boolean setDefault(int id){
      return dao.setDefault(id);
    }

    public static int getDefault(){return dao.getDefault();}
}
