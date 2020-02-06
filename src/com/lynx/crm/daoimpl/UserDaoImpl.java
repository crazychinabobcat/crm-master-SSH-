package com.lynx.crm.daoimpl;

import com.lynx.crm.dao.UserDao;
import com.lynx.crm.domain.User;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {




    //dao层用户注册方法
    @Override
    public void saveUser(User user) {
        this.getHibernateTemplate().save(user);
    }



    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code= ? and user_password= ? ",user.getUser_code(),user.getUser_password());
        if (list.size() >0){
            return  list.get(0);
        }
        return null;
    }
}
