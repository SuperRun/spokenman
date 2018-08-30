package com.zust.itee.exam.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.service.TestService;

/**
 * TODO 类描述
 *
 * @author pojun
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private BaseDao<Tmember> memberDao;

    @Override
    public void test() {
        String loginName = "18800008888";
        String hql = "from Tmember d where lower(d.loginName) = :l";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("l", loginName.toLowerCase());
        Tmember tmember = memberDao.get(hql, params);
        System.out.println(JSONObject.toJSONString(tmember));
    }
}
