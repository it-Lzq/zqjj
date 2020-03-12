package com.lzq.dao.impl;

import com.lzq.bean.Order;
import com.lzq.dao.OrderDao;
import com.lzq.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:12
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class OrderDaoImpl implements OrderDao {
    private static final String SQL_INSERT = "INSERT INTO JJ_ORDERS(USERID,STATUS,MONEY,PAYMENTID,TRANSPORTID,ADDRESSID,EXPRESSNO,CREATETIME) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE JJ_ORDERS SET STATUS=?,PAYMENTID=?,TRANSPORTID=?,ADDRESSID=? WHERE ID = ?";
    private static final String SQL_FING_BY_USERID = "SELECT * FROM JJ_ORDERS WHERE  USERID = ?";
    private static final String SQL_FING_BY_ID = "SELECT * FROM JJ_ORDERS WHERE  ID = ?";

    @Override
    public void insertOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstat.setInt(1,order.getUserId());
            pstat.setInt(2,order.getStatus());
            pstat.setDouble(3,order.getMoney());
            pstat.setInt(4,order.getPaymentId());
            pstat.setInt(5,order.getTransportId());
            pstat.setInt(6,order.getAddressId());
            pstat.setString(7,order.getExpressNo());
            pstat.setDate(8,order.getCreateTime());
            pstat.executeUpdate();
            ResultSet keys = pstat.getGeneratedKeys();
            if(keys.next()){
                id = keys.getInt(1);
                order.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
    }

    @Override
    public void updateOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_UPDATE);
            pstat.setInt(1,order.getStatus());
            pstat.setInt(2,order.getPaymentId());
            pstat.setInt(3,order.getTransportId());
            pstat.setInt(4,order.getAddressId());
            pstat.setInt(5,order.getId());
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
    }

    @Override
    public List<Order> getByUserId(int userId) {
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FING_BY_USERID);
            pstat.setInt(1,userId);
            rs = pstat.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(userId);
                order.setStatus(rs.getInt("status"));
                order.setMoney(rs.getDouble("money"));
                order.setPaymentId(rs.getInt("paymentId"));
                order.setTransportId(rs.getInt("transportId"));
                order.setAddressId(rs.getInt("addressId"));
                order.setExpressNo(rs.getString("expressNo"));
                order.setCreateTime(rs.getDate("createTime"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FING_BY_ID);
            pstat.setInt(1,id);
            rs = pstat.executeQuery();
            while(rs.next()){
                order = new Order();
                order.setId(id);
                order.setUserId(rs.getInt("userId"));
                order.setStatus(rs.getInt("status"));
                order.setMoney(rs.getDouble("money"));
                order.setPaymentId(rs.getInt("paymentId"));
                order.setTransportId(rs.getInt("transportId"));
                order.setAddressId(rs.getInt("addressId"));
                order.setExpressNo(rs.getString("expressNo"));
                order.setCreateTime(rs.getDate("createTime"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return order;
    }
}
