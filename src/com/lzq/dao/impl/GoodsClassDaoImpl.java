package com.lzq.dao.impl;

import com.lzq.bean.GoodsClass;
import com.lzq.dao.GoodsClassDao;
import com.lzq.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/4 21:00
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class GoodsClassDaoImpl implements GoodsClassDao {
    private static final String SQL_FIND_All = "SELECT * FROM JJ_CLASS";
    private static final String SQL_FING_BY_ID = "SELECT * FROM JJ_CLASS WHERE ID = ?";

    @Override
    public List<GoodsClass> findAll() {
        //0.创建list集合，用于存储查询结果
        List<GoodsClass> date = new ArrayList<>();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            //1.连接数据库
            conn = DruidUtil.getConnection();
            //2.预编译SQL语句
            state = conn.prepareStatement(SQL_FIND_All);
            //3.执行sql语句
            resultSet = state.executeQuery();
            //4.遍历结果集，将结果集中的每一行数据 封装为一个GoodsClass对象
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int parentId = resultSet.getInt("parentId");
                String className = resultSet.getString("className");
                //将对象添加到集合中
                date.add(new GoodsClass(id,parentId,className));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,resultSet);
        }
        return date;
    }

    @Override
    public GoodsClass findById(int id) {
        GoodsClass goodsClass = new GoodsClass();
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;
        try {
            //1.连接数据库
            conn = DruidUtil.getConnection();
            //2.预编译SQL语句
            state = conn.prepareStatement(SQL_FING_BY_ID);
            state.setInt(1,id);
            //3.执行sql语句
            resultSet = state.executeQuery();
            //4.遍历结果集，将结果集中的每一行数据 封装为一个GoodsClass对象
            while(resultSet.next()){
                goodsClass.setId(resultSet.getInt("id"));
                goodsClass.setParentId(resultSet.getInt("parentId"));
                goodsClass.setClassName(resultSet.getString("className"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,resultSet);
        }
        return goodsClass;
    }
}
