package com.lynx.crm.action;

import com.lynx.crm.domain.PageBean;
import com.lynx.crm.domain.SaleVisit;
import com.lynx.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private  SaleVisit saleVisit = new SaleVisit();


    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    @Resource(name="saleVisitService")
    private SaleVisitService saleVisitService;


    //接受分页数据

    private Integer currpage = 1;
    private Integer pageSize = 3;



    public void setCurrpage(Integer currpage) {
        if(currpage == null) {
            currpage = 1;
        }
        this.currpage = currpage;
    }



    public void setPageSize(Integer pageSize) {
        if(pageSize == null) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }


    private Date visit_endtime;


    public void setVisit_endtime(Date visit_endtime) {
        this.visit_endtime = visit_endtime;
    }



    public Date getVisit_endtime() {
        return visit_endtime;
    }

    //拜访记录添加页面
    public String saveUI(){
        return "saveUI";
    }

    public String save(){
        saleVisitService.save(saleVisit);
        return "saveSuccess";
    }

    //查询全部
    public String findAll() {

        //创建离线查询条件
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        //设置条件
        if(saleVisit.getVisit_time() !=null) {
            detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
        }

        if(visit_endtime !=null) {
            detachedCriteria.add(Restrictions.le("visit_time", visit_endtime));
        }
        //调用业务层
        PageBean<SaleVisit> pageBean =  saleVisitService.findByPage(detachedCriteria,currpage,pageSize);
        //存入值栈
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }
}
