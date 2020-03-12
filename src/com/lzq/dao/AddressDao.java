package com.lzq.dao;

import com.lzq.bean.Address;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/18 20:47
 * @email 邮箱:905866484@qq.com
 * @description 描述：地址
 */
public interface AddressDao {

    /**
     * 查询用户的所有收货地址
     * @param userId 用户id
     * @return 用户地址集合
     */
    List<Address> findByUserId(int userId);

    /**
     * 查询某个收获地址
     * @param id 地址id
     * @return 查询地址
     */
    Address findAddressById(int id);

    /**
     * 地址信息的插入
     * @param address 地址对象
     * @return 插入结果
     */
    boolean insertAddress(Address address);
    /**
     * 地址信息的修改
     * @param newAddress 地址对象
     * @return 修改结果
     */
    boolean updateAddress(int id,Address newAddress);
    /**
     * 地址信息的删除
     * @param id 地址id
     * @return 删除结果
     */
    boolean deleteAddress(int id);

    /**
     * 设置默认
     * @param id 默认id
     * @return 结果
     */
    boolean setDefault(int id);

    int getDefault();
}
