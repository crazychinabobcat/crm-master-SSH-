package com.lynx.crm.action;

import com.lynx.crm.domain.Customer;
import com.lynx.crm.domain.LinkMan;
import com.lynx.crm.domain.PageBean;
import com.lynx.crm.service.CustomerService;
import com.lynx.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManAction  extends ActionSupport implements ModelDriven<LinkMan> {

    private LinkMan linkMan = new LinkMan();

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }


    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //分页参数
    private Integer currPage = 1;
    private Integer pageSize = 3;



    public void setCurrPage(Integer currPage) {
        if(currPage == null) {
            currPage = 1;
        }

        this.currPage = currPage;
    }


    public void setPageSize(Integer pageSize) {
        if(pageSize == null ) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }


    //跳转到添加联系人的Action
    public String saveUI(){
        List<Customer> list = customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    //跳转到修改页面
    public String edit(){
        List<Customer> list = customerService.findAll();
        linkMan = linkManService.findById(linkMan.getLkm_id());
        ActionContext.getContext().getValueStack().set("list",list);
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }

    //保存联系人方法
    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    //修改联系人方法
    public String update(){
        linkManService.update(linkMan);
        return "updateSuccess";
    }

    //删除联系人方法
    public String delete(){
        linkMan = linkManService.findById(linkMan.getLkm_id());
        linkManService.delete(linkMan);
        return "deleteSucess";
    }


    //查询联系人列表的Action

    public String findAll(){
        //创建离线查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //设置查询条件
        if(linkMan.getLkm_name() !=null) {
            //设置按名称查询的一个条件
            detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
        }
        //设置性别的条件
        if(linkMan.getLkm_gender() !=null && !"".equals(linkMan.getLkm_gender())) {
            //设置按性别查询的条件
            detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
        }

        //调用业务层
        PageBean<LinkMan> pageBean =  linkManService.findAll(detachedCriteria,currPage,pageSize);
        //放入值栈
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";

    }

}
