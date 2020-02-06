package com.lynx.crm.action;

import com.lynx.crm.domain.Customer;
import com.lynx.crm.domain.PageBean;
import com.lynx.crm.service.CustomerService;

import com.lynx.crm.util.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.util.List;

public class CustomerAction  extends ActionSupport implements ModelDriven<Customer> {


    //注入模型驱动
    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }



    //注入service

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    //使用set方法来接收数据

    //客户跳转到添加页面的方法
    public String saveUI(){

        return "saveUI";
    }


    private String uploadFileName; //文件上传
    private File upload;            //上传文件
    private String uploadContentType; //文件类型


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    //保存客户的方法
    public String save() throws  Exception{
        if(upload != null){
        String path = "d:/textUpload";
        String uuidFilename = UploadUtils.getUuidFileName(uploadFileName);
        String replPath = UploadUtils.getUuidFileName(uuidFilename);
        String url = path+replPath;
        File file = new File(url);
        if (file.exists()){
            file.mkdir();
        }
        File dectFile = new File(url+"/"+uuidFilename);
            FileUtils.copyFile(upload,dectFile);
            customer.setCust_image(url+"/"+uuidFilename);
        }
        customerService.save(customer);
        return "saveSuccess";
    }


    private  Integer currPage = 1;
    private  Integer pageSize = 3;

    public void setCurrPage(Integer currPage) {
        if ( currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null){
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

  //客户列表查询
  public String findAllCusotmer() throws Exception {
      List<Customer> list =   customerService.findAll();
      //将list转成json
      JsonConfig jsonConfig = new JsonConfig();
      jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
      //转json
      JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
      ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
      ServletActionContext.getResponse().getWriter().print(jsonArray.toString());
      return NONE;
  }


    public  String findAll() {
        //接收参数：分页参数
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //设置条件
        if(customer.getCust_name() !=null) {
            detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
        }

        if(customer.getBaseDictSource() != null) {
            if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())) {
                detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
            }
        }

        if(customer.getBaseDictLevel() != null) {
            if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {
                detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
            }

        }

        if(customer.getBaseDictIndustry() != null ) {
            if(customer.getBaseDictIndustry().getDict_id() != null  && !"".equals(customer.getBaseDictIndustry().getDict_id())) {
                detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
            }
        }
        //调用业务成查询
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }


    //删除用户信息
    public String delete(){

        customer = customerService.findByid(customer.getCust_id());
        if (customer.getCust_image() !=null){
            File file = new File(customer.getCust_image());
            if (file.exists()){
                file.delete();
            }
        }
        customerService.delete(customer);
        return "deleteSuccess";
    }

    //修改用户信息

    public String edit(){
            customer = customerService.findByid(customer.getCust_id());
            ActionContext.getContext().getValueStack().push(customer);
        return "editSuccess";
    }


    public  String update() throws  Exception{

        if (upload !=null){
            String cust_image = customer.getCust_image();
            if (cust_image !=null ||!"".equals(cust_image)){
                File file = new File(cust_image);
                file.delete();
            }

            String path ="d/textUpload";
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            String realPath = UploadUtils.getPath(uuidFileName);
            String url = path+realPath;
            File file = new File(url);
            if (file.exists()){
                file.mkdir();
            }
            File dectFile = new File(url+"/"+uuidFileName);
            FileUtils.copyFile(upload,dectFile);
            customer.setCust_image(url+"/"+uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }


}
