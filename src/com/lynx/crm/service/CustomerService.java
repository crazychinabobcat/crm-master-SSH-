package com.lynx.crm.service;

import com.lynx.crm.domain.Customer;
import com.lynx.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    //保存客户的方法
    void save(Customer customer);

    //查询客户方法
    List<Customer> findAll();

    //分页查询客户数据
    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    //根据id查询用户
    Customer findByid(Long cust_id);

    //删除用户信息
    void delete(Customer customer);
    //更改用户信息
    void update(Customer customer);
}
