package com.lynx.crm.service;

import com.lynx.crm.domain.PageBean;
import com.lynx.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {


    void save(SaleVisit saleVisit);

    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currpage, Integer pageSize);
}
