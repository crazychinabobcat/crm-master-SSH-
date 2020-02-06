package com.lynx.crm.interceptor;


import org.aopalliance.intercept.Invocation;
import org.apache.struts2.ServletActionContext;

import com.lynx.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/*
 * 权限拦截器
 * */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//判断session中是否有登录用户的信息
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("exisUser");
		if(existUser == null) {
			//存取错误信息，页面跳转到登录页面
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录，没有权限访问");
			return  actionSupport.LOGIN;
		}else {
			return invocation.invoke();
		}
		
	}
	
	

}
