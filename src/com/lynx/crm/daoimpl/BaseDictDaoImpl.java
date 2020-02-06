package com.lynx.crm.daoimpl;

import com.lynx.crm.dao.BaseDictDao;
import com.lynx.crm.domain.BaseDict;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

    @Override
        public List<BaseDict> findByTypeCode(String dict_code) {

        return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?",dict_code);
    }
}
