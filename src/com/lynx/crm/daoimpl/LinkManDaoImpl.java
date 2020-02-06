package com.lynx.crm.daoimpl;

import com.lynx.crm.dao.LinkManDao;
import com.lynx.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;


public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {


    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {

        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() >0){
            return  list.get(0).intValue();
        }
        return null;
    }

    @Override
    public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
        return list;
    }

    @Override
    public void delete(LinkMan linkMan) {
        this.getHibernateTemplate().delete(linkMan);

    }

    @Override
    public void update(LinkMan linkMan) {
        this.getHibernateTemplate().update(linkMan);

    }

    //保存用户信息
    @Override
    public void save(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    //根据id查询用户信息
    @Override
    public LinkMan findById(Long lkm_id) {
        return this.getHibernateTemplate().get(LinkMan.class,lkm_id);
    }
}
