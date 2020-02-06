package com.lynx.crm.action;

import com.lynx.crm.domain.BaseDict;
import com.lynx.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

    private BaseDict baseDict = new BaseDict();

    @Override
    public BaseDict getModel() {
        return baseDict;
    }


    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    //根据类型名称查询字典方法
    public String findByTypeCode()throws  Exception{

        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        System.out.println(jsonArray);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(jsonArray.toString());
        return NONE;
    }


}
