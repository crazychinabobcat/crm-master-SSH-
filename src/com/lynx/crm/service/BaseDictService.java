package com.lynx.crm.service;

import com.lynx.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictService {

    //字典查询
    List<BaseDict> findByTypeCode(String getDict_type_code);
}
