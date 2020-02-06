package com.lynx.crm.daoimpl;

import com.lynx.crm.dao.CustomerDao;
import com.lynx.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {


    //保存用户的方法
    @Override
    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }


    //查询用户方法
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.getHibernateTemplate().find("from Customer");
    }



    //分页查询客户数据


    @Override
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        // TODO Auto-generated method stub
        detachedCriteria.setProjection(null);
        return	(List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);


    }

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria, Integer currPage) {
        // TODO Auto-generated method stub
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long>  list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0) {
            return list.get(0).intValue();
        }
        return null;
    }


    //根据id查询用户信息
    @Override
    public Customer findById(Long cust_id) {
        return this.getHibernateTemplate().get(Customer.class,cust_id);
    }


    //删除用户信息
    @Override
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }

    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }
}
