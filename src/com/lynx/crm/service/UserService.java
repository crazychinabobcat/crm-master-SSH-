package com.lynx.crm.service;

import com.lynx.crm.dao.UserDao;
import com.lynx.crm.domain.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserService {

    //用户注册的方法
    void userRegist(User user);

    //用户登录的方法
    User login(User user);
}
