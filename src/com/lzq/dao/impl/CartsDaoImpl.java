package com.lzq.dao.impl;

import com.lzq.bean.Carts;
import com.lzq.dao.CartsDao;
import com.lzq.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/15 16:09
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class CartsDaoImpl implements CartsDao {
    private static final String SQL_INSERT_CARTS = "INSERT INTO JJ_CARTS(USERID,ISCHECK,GOODSID,CARTNUM) VALUE(?,0,?,?)";
    private static final String SQL_FIND_ISEXIST = "SELECT * FROM JJ_CARTS WHERE USERID = ? AND GOODSID = ?";
    private static final String SQL_UPDATE_CARTNUM = "UPDATE JJ_CARTS SET CARTNUM = CARTNUM + ? WHERE USERID = ? AND GOODSID = ?";
    private static final String SQL_UPDATE_CHECKED = "UPDATE JJ_CARTS SET ISCHECK = ? WHERE USERID = ? AND GOODSID = ?";
    private static final String SQL_DELETE_CARTS = "DELETE FROM JJ_CARTS WHERE USERID = ? AND GOODSID = ?";
    private static final String SQL_FIND_CARTS_BY_USERID = "SELECT * FROM JJ_CARTS WHERE USERID = ?";

    @Override
    public void addCarts(int userId, int goodsId, int num) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            //1.从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句
            pstat = conn.prepareStatement(SQL_INSERT_CARTS);
            pstat.setInt(1,userId);
            pstat.setInt(2,goodsId);
            pstat.setInt(3,num);
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

    }

    @Override
    public int updateCartNum(int userId, int goodsId, int num) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1.从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句
            pstat = conn.prepareStatement(SQL_UPDATE_CARTNUM);
            pstat.setInt(1,num);
            pstat.setInt(2,userId);
            pstat.setInt(3,goodsId);
            result = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

        return result;
    }

    @Override
    public int updateCartChecked(int userId, int goodsId, int checked) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1.从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句
            pstat = conn.prepareStatement(SQL_UPDATE_CHECKED);
            pstat.setInt(1,checked);
            pstat.setInt(2,userId);
            pstat.setInt(3,goodsId);
            result = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

        return result;
    }

    @Override
    public Carts findUserCartsExist(int userId, int goodsId) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Carts carts = null;
        try {
        //1.从连接池中获取连接
        conn = DruidUtil.getConnection();
        //2.预编译sql语句
        pstat = conn.prepareStatement(SQL_FIND_ISEXIST);
        pstat.setInt(1,userId);
        pstat.setInt(2,goodsId);
        rs = pstat.executeQuery();
        while (rs.next()){
            carts = new Carts(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return carts;
    }

    @Override
    public List<Carts> findCartsByUserId(int userId) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
       List<Carts> cartsList = new ArrayList<>();
        try {
            //1.从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句
            pstat = conn.prepareStatement(SQL_FIND_CARTS_BY_USERID);
            pstat.setInt(1,userId);
            rs = pstat.executeQuery();
            while (rs.next()){
                Carts carts = new Carts(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                cartsList.add(carts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return cartsList;
    }

    @Override
    public int delCarts(int userId, int goodsId) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int result = 0;
        try {
            //1.从连接池中获取连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句
            pstat = conn.prepareStatement(SQL_DELETE_CARTS);
            pstat.setInt(1,userId);
            pstat.setInt(2,goodsId);
            result = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

        return result;
    }
}
