package com.lzq.service;

import com.lzq.bean.User;
import com.lzq.dao.UserDao;
import com.lzq.dao.impl.UserDaoImpl;

import java.sql.Date;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/7 22:29
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public class UserService {
    private static UserDao dao = new UserDaoImpl();

    public static boolean regist(User user){
        User user1 = dao.selectOne(user.getUserphone());
        System.out.println(user1);
        if(user1 == null){
            return dao.regist(user);
        }else {
            return false;
        }
    }

    public static boolean login(User u){
        User user = dao.selectOne(u.getUserphone());
        if(user==null){
            return false;
        }else{
            if(user.getPassword().equals(u.getPassword())){
                updateLastIp(user);
                return true;
            }
        }
        return false;
    }

    public static void updateLastIp(User u){
        User user = dao.selectOne(u.getUserphone());
        user.setLastIp(u.getLastIp());
        dao.update(user);
    }

    public static User getUser(String userphone){
        return  dao.selectOne(userphone);
    }

    public static void updateUserInfo(User u){
        User user = dao.selectOne(u.getUserphone());
        user.setEmail(u.getEmail());
        user.setNickName(u.getNickName());
        dao.update(user);
    }

    public static void update(User user){
        dao.update(user);
    }
}
