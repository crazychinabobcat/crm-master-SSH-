package com.lynx.crm.serviceImpl;

import com.lynx.crm.dao.SaleVisitDao;
import com.lynx.crm.domain.PageBean;
import com.lynx.crm.domain.SaleVisit;
import com.lynx.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SaleVisitServiceImpl  implements SaleVisitService {

    @Resource(name="saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    public void save(SaleVisit saleVisit) {

        saleVisitDao.save(saleVisit);
    }

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currpage, Integer pageSize) {

        PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
        //设置当前页数
        pageBean.setCurrPage(currpage);
        //设置当前记录数
        pageBean.setPageSize(pageSize);
        //设置总的记录数
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        double  tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        Integer begin = (currpage-1)*pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;

    }
}
