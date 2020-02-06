package com.lynx.crm.dao;

import com.lynx.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {

    //dao层保存用户
    void save(Customer customer);

    //dao层查询用户方法
    List<Customer> findAll();



    //分页查询客户信息
    List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    Integer findCount(DetachedCriteria detachedCriteria, Integer currPage);


    //根据id查询用户信息
    Customer findById(Long cust_id);

    //删除用户信息
    void delete(Customer customer);
    //更改用户信息
    void update(Customer customer);
}
