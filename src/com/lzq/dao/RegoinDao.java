package com.lzq.dao;

import com.lzq.bean.CnRegion;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/17 20:56
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public interface RegoinDao {

    List<CnRegion> findByLevel(int level);


    CnRegion findByCode(String code);


    List<CnRegion> findByParentCode(String parentCode);
}
