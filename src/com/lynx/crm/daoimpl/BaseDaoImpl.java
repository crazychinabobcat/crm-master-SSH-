package com.lynx.crm.daoimpl;

import com.lynx.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    //提供一个构造方法，传入一个具体类型的Class
    private Class clazz;




    public BaseDaoImpl(Class clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public void save(T t) {
        // TODO Auto -generated method stub
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().delete(t);
    }


    //根据iD查询
    @Override
    public T findById(Serializable id) {
        // TODO Auto-generated method stub
        return (T) this.getHibernateTemplate().get(clazz, id);

    }


    //查询所有
    @Override
    public List<T> findAll() {
        // TODO Auto-generated method stub
        return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
    }


    //统计个数
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        // TODO Auto-generated method stub
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size() >0) {
            return list.get(0).intValue();
        }
        return null;
    }


    //分页查询
    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        // TODO Auto-generated method stub
        detachedCriteria.setProjection(null);
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }

}
