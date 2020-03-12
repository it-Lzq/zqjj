package com.lzq.dao.impl;

import com.lzq.bean.OrderGoods;
import com.lzq.dao.OrderGoodsDao;
import com.lzq.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 13:30
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class OrderGoodsDaoImpl implements OrderGoodsDao {
    private static final String SQL_INSERT = "INSERT INTO JJ_ORDER_GOODS(ORDERID,GOODSID,GOODSNUM,GOODSPRICE,GOODSNAME,GOODSIMG) VALUE(?,?,?,?,?,?)";
    private static final String SQL_GET_BY_ORDERID = "SELECT * FROM JJ_ORDER_GOODS WHERE ORDERID = ?";

    @Override
    public void insert(OrderGoods orderGoods) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstat.setInt(1,orderGoods.getOrderId());
            pstat.setInt(2,orderGoods.getGoodsId());
            pstat.setInt(3,orderGoods.getGoodsNum());
            pstat.setDouble(4,orderGoods.getGoodsPrice());
            pstat.setString(5,orderGoods.getGoodsName());
            pstat.setString(6,orderGoods.getGoodsImg());
            pstat.executeUpdate();
            ResultSet keys = pstat.getGeneratedKeys();
            if(keys.next()){
                id = keys.getInt(1);
                orderGoods.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
    }

    @Override
    public List<OrderGoods> getByOrderId(int orderId) {
        List<OrderGoods> ogList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_GET_BY_ORDERID);
            pstat.setInt(1,orderId);
            rs = pstat.executeQuery();
            while(rs.next()){
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setId(rs.getInt("id"));
                orderGoods.setOrderId(orderId);
                orderGoods.setGoodsId(rs.getInt("goodsId"));
                orderGoods.setGoodsNum(rs.getInt("goodsNum"));
                orderGoods.setGoodsPrice(rs.getDouble("goodsPrice"));
                orderGoods.setGoodsName(rs.getString("goodsName"));
                orderGoods.setGoodsImg(rs.getString("goodsImg"));
                ogList.add(orderGoods);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return ogList;
    }
}
