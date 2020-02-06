package com.lynx.crm.serviceImpl;

import com.lynx.crm.dao.LinkManDao;
import com.lynx.crm.domain.LinkMan;
import com.lynx.crm.domain.PageBean;
import com.lynx.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkManServiceImpl implements LinkManService {

    //注入dao层

    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }


    //根据id查询用户信息
    @Override
    public LinkMan findById(Long lkm_id) {

        return linkManDao.findById(lkm_id);
    }


    //保存用户信息
    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);

    }

    //修改用户信息
    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);

    }

    //删除用户信息
    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);

    }

    //分页查询用户信息

    @Override
    public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
        //设置当前页数
        pageBean.setCurrPage(currPage);
        //设置每页记录数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        double tc = totalCount;
        Double num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //每页显示数据的集合
        Integer begin = (currPage - 1) * pageSize;
        List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }
}
