package com.lzq.service;

import com.lzq.bean.CnRegion;
import com.lzq.dao.RegoinDao;
import com.lzq.dao.impl.RegoinDaoImpl;
import com.lzq.util.DataUtil;

import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/17 21:27
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class RegionService {
    private static RegoinDao dao = new RegoinDaoImpl();

    public static List<CnRegion> findByLevel(int level){
        if(level == 1){
            List<CnRegion > citys = (List<CnRegion>) DataUtil.data.get("citys");
            if(citys == null){
                citys = dao.findByLevel(level);
                DataUtil.data.put("citys",citys);
            }
            return citys;
        }
        return dao.findByLevel(level);
    }


    public static CnRegion findByCode(String code){
        return dao.findByCode(code);
    }


    public static List<CnRegion> findByParentCode(String parentCode){
        return dao.findByParentCode(parentCode);
    }
}
