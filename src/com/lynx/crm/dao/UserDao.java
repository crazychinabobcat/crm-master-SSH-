package com.lynx.crm.dao;

import com.lynx.crm.domain.User;

public interface UserDao {
    //dao层用户注册
    void saveUser(User user);

    //用户登录
    User login(User user);
}
