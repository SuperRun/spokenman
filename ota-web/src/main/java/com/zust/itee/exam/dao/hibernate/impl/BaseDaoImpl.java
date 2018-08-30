package com.zust.itee.exam.dao.hibernate.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zust.itee.exam.dao.hibernate.BaseDao;

@SuppressWarnings("all")
@Repository("baseDao1")
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * @return org.hibernate.Session
     */
    public Session getCurrentSession() {
        return this.entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#save(Object)
     */
    public Serializable save(T o) {
        if (o != null) {
            return this.getCurrentSession().save(o);
        }
        return null;
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#delete(Object)
     */
    public void delete(T o) {
        if (o != null) {
            this.getCurrentSession().delete(o);
        }
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#update(Object)
     */
    public void update(T o) {
        if (o != null) {
            this.getCurrentSession().update(o);
        }
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#saveOrUpdate(Object)
     */
    public void saveOrUpdate(T o) {
        if (o != null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#get(Class, Serializable)
     */
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#get(String)
     */
    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#get(String, Map)
     */
    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#find(String)
     */
    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#find(String, Map)
     */
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    public List<T> find(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.list();
    }

    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List<T> find(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#find(String, int, int)
     */
    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     * @author chengzg（来源于windflower）
     * @since 2014-07-11
     */
    public int countHqlResult(final String hql, final List<Object> param) {
        int count = 0;
        String fromHql = hql;
        // select子句与order by子句会影响count查询,进行简单的排除.
        fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        String countHql = "select count(*) " + fromHql;

        try {
            // System.out.println(list.size());
            // count = Integer.parseInt(list.get(0).toString());
            count = createQuery(hql, param).list().size();
        } catch (Exception e) {
            throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
        }

        return count;
    }

    /**
     * @author chengzg（来源于windflower）
     * @since 2014-07-11
     */
    protected Query createQuery(final String queryString, final List<Object> param) {
        Assert.hasText(queryString, "queryString不能为空");
        Query query = getCurrentSession().createQuery(queryString);
        // if (values != null) {
        // query.setProperties(values);
        // }
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                query.setParameter(i, param.get(i));
            }
        }
        return query;
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#find(String, Map, int, int)
     */
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#count(String)
     */
    public Long count(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (Long) q.uniqueResult();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#count(String, Map)
     */
    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public Double sum(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Double) q.uniqueResult();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#executeHql(String)
     */
    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#executeHql(String, Map)
     */
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#findBySql(String)
     */
    public List<Object[]> findBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#findBySql(String, int, int)
     */
    public List<Object[]> findBySql(String sql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#findBySql(String, Map)
     */
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#findBySql(String, Map, int, int)
     */
    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#findBySql(String, Map, int, int)
     */
    public List<Object[]> findByHql(String hql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#executeSql(String)
     */
    public int executeSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#executeSql(String, Map)
     */
    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#countBySql(String)
     */
    public BigInteger countBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#countBySql(String, Map)
     */
    public BigInteger countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (BigInteger) q.uniqueResult();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#sum(String)
     */
    public Integer sum(String sql) {
        Object obj = this.getCurrentSession().createQuery(sql).list().iterator().next();
        return ((Integer) obj).intValue();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#updateByHql(String)
     */
    public Integer updateByHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    /**
     * @see com.soer.biz.dao.BaseDaoI#updateByHql(String)
     */
    public Integer updateByHql(String hql, Object... params) {
        Query q = this.getCurrentSession().createSQLQuery(hql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                q.setParameter(i, params[i]);
            }
        }
        return q.executeUpdate();
    }

    public List<T> matchByHql(String hql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public Integer countMatchByHql(String hql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(hql);
        return (Integer) q.uniqueResult();
    }

    @Override
    public List<T> findBySqlGetEntity(String sql, Class<T> clazz) {
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql).addEntity(clazz);
        return query.list();
    }

    @Override
    public List<T> findBySqlGetEntity(String sql, Class<T> clazz, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql).addEntity(clazz);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }



	/*
    public <E> List<E> findBySql(String sql, Map<String, Object> params, int page,
			int rows, Class<E> clazz) {
		SQLQuery sqlQuery = this.getCurrentSession()
				.createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				sqlQuery.setParameter(key, params.get(key));
			}
		}
		sqlQuery.addEntity(clazz);
		@SuppressWarnings("unchecked")
		// 分页查询
		List<E> list = sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	*/
}