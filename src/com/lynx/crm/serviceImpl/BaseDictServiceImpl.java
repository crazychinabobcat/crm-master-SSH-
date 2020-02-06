package com.lynx.crm.serviceImpl;

import com.lynx.crm.dao.BaseDictDao;
import com.lynx.crm.domain.BaseDict;
import com.lynx.crm.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {

    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String getDict_type_code) {

        return baseDictDao.findByTypeCode(getDict_type_code);
    }
}
