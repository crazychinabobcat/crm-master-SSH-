package com.lynx.crm.service;

import com.lynx.crm.domain.LinkMan;
import com.lynx.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {

    //根据id查询用户
    LinkMan findById(Long lkm_id);

    //保存用户信息
    void save(LinkMan linkMan);

    //更新用户信息
    void update(LinkMan linkMan);

    //删除用户信息
    void delete(LinkMan linkMan);

    //分页查询用户信息
    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);
}
