package com.lzq.dao.impl;

import com.lzq.bean.CnRegion;
import com.lzq.dao.RegoinDao;
import com.lzq.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/17 20:58
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class RegoinDaoImpl implements RegoinDao {
    private static final String SQL_FIND_BY_LEVEL="SELECT * FROM CN_REGION WHERE LEVEL = ? ";
    private static final String SQL_FIND_BY_CODE = "SELECT * FROM CN_REGION WHERE CODE = ?";
    private static final String SQL_FIND_BY_PARENTCODE = "SELECT * FROM CN_REGION WHERE SUPERIOR_CODE = ?";

    @Override
    public List<CnRegion> findByLevel(int level) {
        ArrayList<CnRegion> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_LEVEL);
            pstat.setInt(1,level);
            rs = pstat.executeQuery();
            while (rs.next()){
                CnRegion cnRegion = new CnRegion();
                cnRegion.setId(rs.getInt("id"));
                cnRegion.setCode(rs.getString("code"));
                cnRegion.setName(rs.getString("name"));
                cnRegion.setLevel(rs.getInt("level"));
                list.add(cnRegion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }

    @Override
    public CnRegion findByCode(String code) {
        CnRegion cnRegion =null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_CODE);
            pstat.setString(1,code);
            rs = pstat.executeQuery();
            if (rs.next()){
                cnRegion = new CnRegion();
                cnRegion.setId(rs.getInt("id"));
                cnRegion.setCode(rs.getString("CODE"));
                cnRegion.setName(rs.getString("NAME"));
                cnRegion.setShortName(rs.getString("SHORT_NAME"));
                cnRegion.setLng(rs.getString("lng"));
                cnRegion.setLat(rs.getString("lat"));
                cnRegion.setLevel(rs.getInt("level"));
                cnRegion.setParentCode(rs.getString("SUPERIOR_CODE"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return cnRegion;
    }

    @Override
    public List<CnRegion> findByParentCode(String parentCode) {
        ArrayList<CnRegion> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_PARENTCODE);
            pstat.setString(1,parentCode);
            rs = pstat.executeQuery();
            while (rs.next()){
                CnRegion cnRegion = new CnRegion();
                cnRegion.setId(rs.getInt("id"));
                cnRegion.setCode(rs.getString("CODE"));
                cnRegion.setName(rs.getString("NAME"));
                cnRegion.setShortName(rs.getString("SHORT_NAME"));
                cnRegion.setLng(rs.getString("lng"));
                cnRegion.setLat(rs.getString("lat"));
                cnRegion.setLevel(rs.getInt("level"));
                cnRegion.setParentCode(rs.getString("SUPERIOR_CODE"));
                list.add(cnRegion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }
}
