package com.lzq.dao;

import com.lzq.bean.User;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/7 21:21
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
public interface UserDao {
    /**
     * 用于注册用户
     * @param user 要注册的用户信息
     * @return 注册结果
     */
    boolean regist(User user);

    /**
     *  用于登陆验证
     * @param id 登录的的用户id
     * @return 登陆成功时，此对象中其他值会自动赋值
     */
    User selectOne(String id);


    boolean update(User user);
}
