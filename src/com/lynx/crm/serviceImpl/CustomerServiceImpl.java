package com.lynx.crm.serviceImpl;

import com.lynx.crm.dao.CustomerDao;
import com.lynx.crm.domain.Customer;
import com.lynx.crm.domain.PageBean;
import com.lynx.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl  implements CustomerService {

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //保存客户的方法
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    //查询客户方法
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }




    //分页查询客户数据
    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean<Customer> pageBean = new PageBean<Customer>();
        //封装当前页数
        pageBean.setCurrPage(currPage);
        //封装每页显示记录数
        pageBean.setPageSize(pageSize);
        //封装总的记录数
        Integer totalCount =  this.customerDao.findCount(detachedCriteria,currPage);

        pageBean.setTotalCount(totalCount);
        //封装总的页数
        Double tc = totalCount.doubleValue();
        Double  num = Math.ceil(tc/pageSize);
        pageBean.setTotalPage(num.intValue());
        //封装每页显示数据的集合
        Integer begin = (currPage - 1) * pageSize;
        List <Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        return pageBean;
    }


    //根据用户id查询用户
    @Override
    public Customer findByid(Long cust_id) {
        return customerDao.findById(cust_id);
    }


    //删除用户信息
    @Override
    public void delete(Customer customer) {
     customerDao.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
