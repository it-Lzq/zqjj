package com.lzq.dao.impl;

import com.lzq.bean.Payments;
import com.lzq.bean.Transports;
import com.lzq.dao.TransportsDao;
import com.lzq.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:06
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class TransportsDaoImpl implements TransportsDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_TRANSPORTS";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_TRANSPORTS WHERE ID = ?";

    @Override
    public List<Transports> getTransports() {
        ArrayList<Transports> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstat.executeQuery();
            while (rs.next()){
                Transports transports = new Transports();
                transports.setId(rs.getInt("id"));
                transports.setName(rs.getString("name"));
                list.add(transports);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }

    @Override
    public Transports getById(int id) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Transports transports = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_ID);
            pstat.setInt(1,id);
            rs = pstat.executeQuery();
            while (rs.next()){
                transports = new Transports();
                transports.setId(rs.getInt("id"));
                transports.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return transports;
    }


}
