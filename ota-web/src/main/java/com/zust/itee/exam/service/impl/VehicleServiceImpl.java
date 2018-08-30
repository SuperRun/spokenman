package com.zust.itee.exam.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.service.VehicleService;

/**
 * Created by liy on 2016/10/15.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao<DataDictionary> dataDictionaryDao;

    @Override
    public boolean checkOrgIsCompany(Integer orgId) {
        return organizationDao.get(Torganization.class, orgId)
                .getType() == OrganizationTypeEnum.COMPANY.getType();
    }
}




































