package com.lynx.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao <T>{

    public void save(T t );

    public void update(T t);

    public void delete(T t);

    //根据ID查询
    public T findById(Serializable id);
    //查询所有
    public List<T> findAll();
    //统计个数方法
    public Integer findCount(DetachedCriteria detachedCriteria);
    //分页查询方法
    public List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}
