package com.lzq.dao.impl;

import com.lzq.bean.User;
import com.lzq.dao.UserDao;
import com.lzq.util.DruidUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/7 21:30
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class UserDaoImpl implements UserDao {
private static final String SQL_INSERT = "INSERT INTO JJ_USERS(USERPHONE,PASSWORD,NICKNAME,USERPHOTO,CREATETIME) VALUES (?,?,?,?,?)";
private static final String SQL_SELECT_ONE = "SELECT * FROM JJ_USERS WHERE USERPHONE = ?";
private static final String SQL_UPDATE = "UPDATE JJ_USERS  SET USERPHONE=?,EMAIL=?,PASSWORD=?,NICKNAME=?,USERPHOTO=?,LASTIP=?,LASTTIME=?,CREATETIME=? WHERE ID = ?";
    @Override
    public boolean regist(User user) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int result = 0;

        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_INSERT);
            pstat.setString(1,user.getUserphone());
            pstat.setString(2,user.getPassword());
            pstat.setString(3,user.getUserphone()+"用户");
            pstat.setString(4,"ntx.png");
            pstat.setDate(5,new Date(System.currentTimeMillis()));
            result = pstat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        if(result == 0)
            return  false;
        else
            return true;
    }

    @Override
    public User selectOne(String userPhone) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_SELECT_ONE);
            pstat.setString(1,userPhone);
            rs = pstat.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setUserphone(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setNickName(rs.getString(5));
                user.setUserPhoto(rs.getString(6));
                user.setLastIp(rs.getString(7));
                user.setLastTime(rs.getDate(8));
                user.setCreateTime(rs.getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int result = 0;

        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_UPDATE);
            pstat.setString(1,user.getUserphone());
            pstat.setString(2,user.getEmail());
            pstat.setString(3,user.getPassword());
            pstat.setString(4,user.getNickName());
            pstat.setString(5,user.getUserPhoto());
            pstat.setString(6,user.getLastIp());
            pstat.setDate(7,user.getLastTime());
            pstat.setDate(8,user.getCreateTime());
            pstat.setInt(9,user.getId());
            result = pstat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        if(result == 0)
            return  false;
        else
            return true;
    }
}
