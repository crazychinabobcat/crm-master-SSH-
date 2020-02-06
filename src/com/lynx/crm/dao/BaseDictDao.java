package com.lynx.crm.dao;

import com.lynx.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictDao {

    List<BaseDict> findByTypeCode(String dict_code);
}
