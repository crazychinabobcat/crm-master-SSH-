package com.lynx.crm.serviceImpl;

import com.lynx.crm.dao.UserDao;
import com.lynx.crm.domain.User;
import com.lynx.crm.service.UserService;
import com.lynx.crm.util.MD5Utils;

public class UserServiceImpl implements UserService {

    //注入dao层
    private UserDao userDao;



    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    //业务层用户注册方法
    @Override
    public void userRegist(User user) {
        //密码加密，修改用户的状态
        String encryption_Password = MD5Utils.md5(user.getUser_password());
        user.setUser_password(encryption_Password);
        user.setUser_state("1");
        userDao.saveUser(user);

    }


    //用户登录的方法
    @Override
    public User login(User user) {
       user.setUser_password(MD5Utils.md5(user.getUser_password()));
        return userDao.login(user);
    }
}
