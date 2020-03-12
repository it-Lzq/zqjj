package com.lzq.dao.impl;

import com.lzq.bean.Address;
import com.lzq.bean.CnRegion;
import com.lzq.dao.AddressDao;
import com.lzq.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/18 20:56
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@SuppressWarnings("all")
public class AddressDaoImpl implements AddressDao {

    private static final String SQL_FIND_BY_USERID = "SELECT * FROM JJ_ADDRESS WHERE USERID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM JJ_ADDRESS WHERE ID = ?";
    private static final String SQL_INSERT = "INSERT INTO JJ_ADDRESS(USERID,USERNAME,USERPHONE,PROVINCEID,CITYID,AREAID,STREEID,USERADDRESS,ISDEFAULT,CREATETIME) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE JJ_ADDRESS SET USERNAME = ?,USERPHONE = ?,PROVINCEID = ?,CITYID = ?,AREAID = ?,STREEID = ?,USERADDRESS = ?,ISDEFAULT = ?,CREATETIME = ? WHERE ID = ?";
    private static final String SQL_SET_DEFAULT_ZERO = "UPDATE  JJ_ADDRESS SET ISDEFAULT = 0 WHERE ISDEFAULT = 1";
    private static final String SQL_SET_DEFAULT = "UPDATE  JJ_ADDRESS SET ISDEFAULT = 1 WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM JJ_ADDRESS WHERE ID = ?";
    private static final String SQL_GET_DEFAULT = "SELECT ID FROM JJ_ADDRESS WHERE ISDEFAULT = 1";


    @Override
    public List<Address> findByUserId(int userId) {
        ArrayList<Address> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_USERID);
            pstat.setInt(1,userId);
            rs = pstat.executeQuery();
            while (rs.next()){
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setUserId(userId);
                address.setUserName(rs.getString("userName"));
                address.setProvinceId(rs.getInt("provinceid"));
                address.setCityId(rs.getInt("cityid"));
                address.setAreaId(rs.getInt("areaid"));
                address.setStreeId(rs.getInt("streeid"));
                address.setUserPhone(rs.getString("userPhone"));
                address.setUserAddress(rs.getString("userAddress"));
                address.setIsDefault(rs.getInt("isDefault"));
                address.setCreateTime(rs.getDate("createTime"));
                list.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return list;
    }

    @Override
    public Address findAddressById(int id) {
        Address address = null;
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_FIND_BY_ID);
            pstat.setInt(1,id);
            rs = pstat.executeQuery();
            if (rs.next()){
                address = new Address();
                address.setId(rs.getInt("id"));
                address.setUserId(rs.getInt("userid"));
                address.setUserName(rs.getString("userName"));
                address.setUserPhone(rs.getString("userPhone"));
                address.setProvinceId(rs.getInt("provinceid"));
                address.setCityId(rs.getInt("cityid"));
                address.setAreaId(rs.getInt("areaid"));
                address.setStreeId(rs.getInt("streeid"));
                address.setUserAddress(rs.getString("userAddress"));
                address.setIsDefault(rs.getInt("isDefault"));
                address.setCreateTime(rs.getDate("createTime"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return address;
    }

    @Override
    public boolean insertAddress(Address address) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int id = 0;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstat.setInt(1,address.getUserId());
            pstat.setString(2,address.getUserName());
            pstat.setString(3,address.getUserPhone());
            pstat.setInt(4,address.getProvinceId());
            pstat.setInt(5,address.getCityId());
            pstat.setInt(6,address.getCityId());
            pstat.setInt(7,address.getStreeId());
            pstat.setString(8,address.getUserAddress());
            pstat.setInt(9,address.getIsDefault());
            pstat.setDate(10,address.getCreateTime());
            pstat.executeUpdate();
            ResultSet keys = pstat.getGeneratedKeys();

            if(keys.next()){
                id = keys.getInt(1);
                address.setId(id);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return false;
    }

    @Override
    public boolean updateAddress(int id, Address address) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_UPDATE);
            pstat.setString(1,address.getUserName());
            pstat.setString(2,address.getUserPhone());
            pstat.setInt(3,address.getProvinceId());
            pstat.setInt(4,address.getCityId());
            pstat.setInt(5,address.getCityId());
            pstat.setInt(6,address.getStreeId());
            pstat.setString(7,address.getUserAddress());
            pstat.setInt(8,address.getIsDefault());
            pstat.setDate(9,address.getCreateTime());
            pstat.setInt(10,id);
            int result = pstat.executeUpdate();
            if(result != 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return false;

    }

    @Override
    public boolean deleteAddress(int id) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_DELETE);
            pstat.setInt(1,id);
            int result = pstat.executeUpdate();
            if(result != 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

        return false;

    }

    @Override
    public boolean setDefault(int id) {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_SET_DEFAULT_ZERO);
            pstat.executeUpdate();
            pstat.close();
            pstat = conn.prepareStatement(SQL_SET_DEFAULT);
            pstat.setInt(1,id);
            int result = pstat.executeUpdate();
            if(result != 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }

        return false;
    }

    @Override
    public int getDefault() {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtil.getConnection();
            pstat = conn.prepareStatement(SQL_GET_DEFAULT);
            rs = pstat.executeQuery();
            if(rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtil.close(conn,pstat,rs);
        }
        return 0;
    }
}
