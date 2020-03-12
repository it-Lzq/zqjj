package com.lzq.dao.impl;

import com.lzq.bean.Carts;
import com.lzq.bean.Goods;
import com.lzq.dao.GoodsDao;
import com.lzq.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/5 20:55
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class GoodsDaoImpl implements GoodsDao {
    private static final String SQL_FIND_ALL = "SELECT * FROM JJ_GOODS";
    private static final String SQL_FIND_BY_CLASS1 = "SELECT * FROM JJ_GOODS WHERE CLASSID1 = ? ORDER BY PRICE LIMIT ?,?";
    private static final String SQL_FIND_BY_CLASS1_DESC = "SELECT * FROM JJ_GOODS WHERE CLASSID1 = ? ORDER BY PRICE DESC LIMIT ?,?";
    private static final String SQL_FIND_BY_CLASS2 = "SELECT * FROM JJ_GOODS WHERE CLASSID2 = ? ORDER BY PRICE LIMIT ?,?";
    private static final String SQL_FIND_BY_CLASS2_DESC = "SELECT * FROM JJ_GOODS WHERE CLASSID2 = ? ORDER BY PRICE DESC LIMIT ?,?";
    private static final String SQL_FIND_LIKE_NAME = "SELECT * FROM JJ_GOODS WHERE NAME LIKE ? ORDER BY PRICE LIMIT ?,?";
    private static final String SQL_FIND_LIKE_NAME_DESC = "SELECT * FROM JJ_GOODS WHERE NAME LIKE ? ORDER BY  PRICE DESC LIMIT ?,?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_GOODS WHERE ID = ?";
    private static final String SQL_FIND_ALL_ASC = "SELECT * FROM JJ_GOODS ORDER BY PRICE ";
    private static final String SQL_FIND_ALL_DESC = "SELECT * FROM JJ_GOODS ORDER BY PRICE DESC";
    private static final String SQL_UPDATE_STOCK = "UPDATE JJ_GOODS SET STOCK = ? WHERE ID = ?";

    @Override
    public List<Goods> fingAll() {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
       //0.创建一个list集合
        List<Goods> data = new ArrayList<>();
    try {
        //1.从德鲁伊连接池中 获取一个数据库的连接
        conn = DruidUtil.getConnection();
        //2.预编译sql语句，并填充参数
        pstat =conn.prepareStatement(SQL_FIND_ALL);
        //3.执行查询语句
        rs = pstat.executeQuery();
        //4.遍历结果集
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String imgs = rs.getString("imgs");
            double price = rs.getDouble("price");
            String gdesc = rs.getString("gdesc");
            int stock = rs.getInt("stock");
            int appraiseNum = rs.getInt("appraiseNum");
            String info = rs.getString("info");
            Date createTime = rs.getDate("createTime");
            int classid1 = rs.getInt("classId1");
            int classid2 = rs.getInt("classId2");
            Goods goods = new Goods(id,name,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);
            data.add(goods);
        }
        //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        DruidUtil.close(conn,pstat,rs);
    }
        return data;
    }

    @Override
    public List<Goods> findAllOrder(int orderBy) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //0.创建一个list集合
        List<Goods> data = new ArrayList<>();
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            if(orderBy == 0){
                pstat =conn.prepareStatement(SQL_FIND_ALL_ASC);
            }else{
                pstat =conn.prepareStatement(SQL_FIND_ALL_DESC);
            }
            //3.执行查询语句
            rs = pstat.executeQuery();
            //4.遍历结果集
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String imgs = rs.getString("imgs");
                double price = rs.getDouble("price");
                String gdesc = rs.getString("gdesc");
                int stock = rs.getInt("stock");
                int appraiseNum = rs.getInt("appraiseNum");
                String info = rs.getString("info");
                Date createTime = rs.getDate("createTime");
                int classid1 = rs.getInt("classId1");
                int classid2 = rs.getInt("classId2");
                Goods goods = new Goods(id,name,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);
                data.add(goods);
            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return data;
    }

    @Override
    public List<Goods> findGoodsByClass1(int classId1, int page, int size, int flag) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //0.创建一个list集合
        List<Goods> data = new ArrayList<>();
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            if(flag == 0){
                pstat =conn.prepareStatement(SQL_FIND_BY_CLASS1);
            }else{
                pstat =conn.prepareStatement(SQL_FIND_BY_CLASS1_DESC);
            }
            //3.执行查询语句
            pstat.setInt(1,classId1);
            pstat.setInt(2,page);
            pstat.setInt(3,size);
            rs = pstat.executeQuery();
            //4.遍历结果集
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String imgs = rs.getString("imgs");
                double price = rs.getDouble("price");
                String gdesc = rs.getString("gdesc");
                int stock = rs.getInt("stock");
                int appraiseNum = rs.getInt("appraiseNum");
                String info = rs.getString("info");
                Date createTime = rs.getDate("createTime");
                int classid1 = rs.getInt("classId1");
                int classid2 = rs.getInt("classId2");
                Goods goods = new Goods(id,name,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);
                data.add(goods);
            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return data;
    }

    @Override
    public List<Goods> findGoodsByClass2(int classId2, int page, int size, int flag) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //0.创建一个list集合
        List<Goods> data = new ArrayList<>();
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            if(flag == 0){
                pstat =conn.prepareStatement(SQL_FIND_BY_CLASS2);
            }else{
                pstat =conn.prepareStatement(SQL_FIND_BY_CLASS2_DESC);
            }
            //3.执行查询语句
            pstat.setInt(1,classId2);
            pstat.setInt(2,page);
            pstat.setInt(3,size);
            rs = pstat.executeQuery();
            //4.遍历结果集
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String imgs = rs.getString("imgs");
                double price = rs.getDouble("price");
                String gdesc = rs.getString("gdesc");
                int stock = rs.getInt("stock");
                int appraiseNum = rs.getInt("appraiseNum");
                String info = rs.getString("info");
                Date createTime = rs.getDate("createTime");
                int classid1 = rs.getInt("classId1");
                int classid2 = rs.getInt("classId2");
                Goods goods = new Goods(id,name,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);
                data.add(goods);
            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return data;
    }

    @Override
    public List<Goods> findGoodsLikeName(String name,int page,int size,int flag) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //0.创建一个list集合
        List<Goods> data = new ArrayList<>();
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            if(flag == 0){
                pstat =conn.prepareStatement(SQL_FIND_LIKE_NAME);
            }else{
                pstat =conn.prepareStatement(SQL_FIND_LIKE_NAME_DESC);
            }
            //3.执行查询语句
            pstat.setString(1,"%"+name+"%");
            pstat.setInt(2,page);
            pstat.setInt(3,size);
            rs = pstat.executeQuery();
            //4.遍历结果集
            while(rs.next()){
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                String imgs = rs.getString("imgs");
                double price = rs.getDouble("price");
                String gdesc = rs.getString("gdesc");
                int stock = rs.getInt("stock");
                int appraiseNum = rs.getInt("appraiseNum");
                String info = rs.getString("info");
                Date createTime = rs.getDate("createTime");
                int classid1 = rs.getInt("classId1");
                int classid2 = rs.getInt("classId2");
                Goods goods = new Goods(id,name2,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);
                data.add(goods);
            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return data;
    }

    @Override
    public Goods findGoodsById(int id) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //0.创建一个goods
        Goods goods = null;
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            pstat =conn.prepareStatement(SQL_FIND_BY_ID);
            //3.执行查询语句
            pstat.setInt(1,id);
            rs = pstat.executeQuery();
            //4.遍历结果集
            while(rs.next()){
                int id2 = rs.getInt("id");
                String name = rs.getString("name");
                String imgs = rs.getString("imgs");
                double price = rs.getDouble("price");
                String gdesc = rs.getString("gdesc");
                int stock = rs.getInt("stock");
                int appraiseNum = rs.getInt("appraiseNum");
                String info = rs.getString("info");
                Date createTime = rs.getDate("createTime");
                int classid1 = rs.getInt("classId1");
                int classid2 = rs.getInt("classId2");
                goods = new Goods(id2,name,imgs,price,gdesc,stock,appraiseNum,info,createTime,classid1,classid2);

            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return goods;
    }

    @Override
    public List<Goods> findGoodsByCart(List<Carts> cartsList) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        //0.创建一个list集合
        List<Goods> goodsList = new ArrayList<>();
        Goods goods = null;
        String param = "";
        for (int i = 0; i < cartsList.size(); i++) {
            Carts carts = cartsList.get(i);
            param += carts.getGoodsId();
            if(i != cartsList.size()-1) param += ",";
        }
        String sql = "SELECT ID,NAME,IMGS,PRICE FROM JJ_GOODS WHERE ID IN ("+param+")";
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            stat =conn.createStatement();
            //3.执行查询语句
            rs = stat.executeQuery(sql);
            //4.遍历结果集
            while(rs.next()){
                goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setImgs(rs.getString("imgs"));
                goods.setPrice(rs.getDouble("price"));
                goodsList.add(goods);
            }
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,stat,rs);
        }
        return goodsList;
    }

    @Override
    public void updateGoodsStock(int id,int num) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Goods goods = this.findGoodsById(id);
        try {
            //1.从德鲁伊连接池中 获取一个数据库的连接
            conn = DruidUtil.getConnection();
            //2.预编译sql语句，并填充参数
            pstat =conn.prepareStatement(SQL_UPDATE_STOCK);
            //3.执行查询语句
            pstat.setInt(1,goods.getStock()-num);
            pstat.setInt(2,id);
            //4.更改库存
            pstat.executeUpdate();
            //5.将每一条数据封装为商品
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
    }
}
