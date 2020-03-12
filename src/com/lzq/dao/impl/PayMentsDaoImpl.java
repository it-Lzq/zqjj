package com.lzq.dao.impl;

import com.lzq.bean.Address;
import com.lzq.bean.Payments;
import com.lzq.dao.PayMentsDao;
import com.lzq.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/20 23:59
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class PayMentsDaoImpl implements PayMentsDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_PAYMENTS";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_PAYMENTS WHERE ID = ?";

    @Override
    public List<Payments> getPayments() {
        ArrayList<Payments> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstat.executeQuery();
            while (rs.next()){
                Payments payments = new Payments();
                payments.setId(rs.getInt("id"));
                payments.setName(rs.getString("name"));
                payments.setImg(rs.getString("img"));
                list.add(payments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }


    @Override
    public Payments getById(int id) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Payments payments = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_ID);
            pstat.setInt(1,id);
            rs = pstat.executeQuery();
            while (rs.next()){
                payments = new Payments();
                payments.setId(rs.getInt("id"));
                payments.setName(rs.getString("name"));
                payments.setImg(rs.getString("img"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return payments;
    }
}
