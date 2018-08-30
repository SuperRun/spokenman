package com.zust.itee.exam.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.service.BaseService;

@Service
@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public void saveOrupdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    @Override
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    public T getById(String id) {
        Class<Object> clazz = getGenericType(0);
        StringBuffer sb = new StringBuffer();
        String name = clazz.getName();
        sb.append("from ").append(name).append(" where id='").append(id)
                .append("'");
        return baseDao.get(sb.toString());
    }

    @Override
    public T getById(Integer id) {
        Class<Object> clazz = getGenericType(0);
        StringBuffer sb = new StringBuffer();
        String name = clazz.getName();
        sb.append("from ").append(name).append(" where id='").append(id)
                .append("'");
        return baseDao.get(sb.toString());
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void deleteById(String id) {
        T t = getById(id);
        if (t != null) {
            delete(t);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Object> getGenericType(int index) {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<Object>) params[index];
    }

    @Override
    public T getByHql(String hql) {
        T t = baseDao.get(hql);
        if (t != null) {
            return t;
        }
        return null;
    }
}
