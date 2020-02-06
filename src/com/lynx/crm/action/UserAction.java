package com.lynx.crm.action;


import com.lynx.crm.domain.User;
import com.lynx.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.Servlet;

/*
* 用户管理Action
* */
public class UserAction extends ActionSupport implements ModelDriven<User> {


    //模型驱动使用
    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    //注入service
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    //用户注册方法
    public String  userRegist(){
        userService.userRegist(user);
        return LOGIN;
    }


    //用户登录的方法
    public String login(){

        User exisUser = userService.login(user);
        if (exisUser == null){
            this.addActionError("用户名或者密码错误");
            return LOGIN;
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("exisUser",exisUser);
            ActionContext.getContext().getSession().put("exisUser",exisUser);
            return SUCCESS;
        }
    }




}
