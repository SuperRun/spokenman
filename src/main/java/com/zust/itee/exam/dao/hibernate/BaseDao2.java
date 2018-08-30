package com.zust.itee.exam.dao.hibernate;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 *
 * @author 孙宇
 *
 */
public interface BaseDao2<T> {

	/**
	 * 保存一个对象
	 *
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 *
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 *
	 * @param o
	 */
	public void update(T o);

	/**
	 * 保存或更新对象
	 *
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 * 查询
	 *
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合(带分页)
	 *
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合(带分页)
	 *
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows);

	/**
	 * 获得一个对象
	 *
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 *
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得一个对象
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from 类
	 *
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from 类
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from 类
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 获取查询hql的结果集的大小，该方法内部会将查询hql转换成为count用的hql，如：“
	 * from Tentity t where 1=1 order by t.name”
	 * @author chengzg（来源于windflower）
	 * @param hql 查询sql
	 * @param param
	 * @return
	 * @since 2014-07-11
	 */

	public int countHqlResult(final String hql,
            final List<Object> param);
	/**
	 * 执行HQL语句
	 *
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 *
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行HQL语句
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);
	/**
	 *
	 * @param sql
	 */
	public List findBySql(String sql);

	/**
	 *
	 *
	 * @author sdy
	 *
	 * @why 返回符合要求的实体类
	 *
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List<T> findBySqlGetEntity(String sql, Class<T> clazz, Map<String, Object> params);

	/**
	 * @author sdy
	 *
	 * @why 分页返回符合要求的实体类
	 * @param sql
	 * @param clazz
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> findBySqlGetEntity(String sql, Class<T> clazz, Map<String, Object> params,
            Integer page, Integer rows);

	/**
	 * @author sdy
	 *
	 * @why
	 *
	 * @param sql
	 * @param params
	 * @return
	 */
	public BigInteger countBySQL(String sql, Map<String, Object> params);

	/**
	 * 计算总和
	 * @param hql
	 * 			 HQL语句(select sum(xx) from T where xx = :xx)
	 * @param params
	 * 			参数
	 * @return
	 */
	public Double sum(String hql, Map<String, Object> params);

}
