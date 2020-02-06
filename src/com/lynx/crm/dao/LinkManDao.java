package com.lynx.crm.dao;

import com.lynx.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface LinkManDao {
    //分页查询客户信息
    Integer findCount(DetachedCriteria detachedCriteria);

    List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    //删除客户
    void delete(LinkMan linkMan);
    //修改客户
    void update(LinkMan linkMan);
    //保存客户
    void save(LinkMan linkMan);
    //根据id查询客户
    LinkMan findById(Long lkm_id);
}
