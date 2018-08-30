package com.zust.itee.exam.dao.hibernate;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 *
 * 其他DAO继承此类获取常用的数据库操作方法
 *
 * @author haniun
 *
 * @param <T>模型
 */
public interface BaseDao<T> {

	/**
	 * 保存一个对象
	 *
	 * @param o对象
	 * @return 对象的ID
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 *
	 * @param o对象
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 *
	 * @param o对象
	 */
	public void update(T o);

	/**
	 * 保存或更新一个对象
	 *
	 * @param o对象
	 *
	 */
	public void saveOrUpdate(T o);

	/**
	 * 通过主键获得对象
	 *
	 * @param c类名
	 *            .class
	 * @param id主键
	 * @return 对象
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 通过HQL语句获取一个对象
	 *
	 * @param hql
	 *            HQL语句
	 * @return 对象
	 */
	public T get(String hql);

	/**
	 * 通过HQL语句获取一个对象
	 *
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 对象
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * 获得对象列表
	 *
	 * @param hql
	 *            HQL语句
	 * @return List
	 */
	public List<T> find(String hql);

	/**
	 * 获得对象列表
	 *
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params);
	public List<T> find(String hql, Object[] param);

	public List<T> find(String hql, List<Object> param);

	public List<T> find(String hql, List<Object> param, Integer page, Integer rows);
	/**
	 * 获得分页后的对象列表
	 *
	 * @param hql
	 *            HQL语句
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, int page, int rows);

	/**
	 * 获得分页后的对象列表
	 *
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return List
	 */
	public List<T> find(String hql, Map<String, Object> params, int page,
            int rows);

	/**
	 * 统计数目
	 *
	 * @param hql
	 *            HQL语句(select count(*) from T)
	 * @return long
	 */
	public Long count(String hql);
	public int countHqlResult(final String hql,
            final List<Object> param);
	/**
	 * 统计数目
	 *
	 * @param hql
	 *            HQL语句(select count(*) from T where xx = :xx)
	 * @param params
	 *            参数
	 * @return long
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 计算总和
	 * @param hql
	 * 			 HQL语句(select sum(xx) from T where xx = :xx)
	 * @param params
	 * 			参数
	 * @return
	 */
	public Double sum(String hql, Map<String, Object> params);

	/**
	 * 执行一个HQL语句
	 *
	 * @param hql
	 *            HQL语句
	 * @return 响应结果数目
	 */
	public int executeHql(String hql);

	/**
	 * 执行一个HQL语句
	 *
	 * @param hql
	 *            HQL语句
	 * @param params
	 *            参数
	 * @return 响应结果数目
	 */
	public int executeHql(String hql, Map<String, Object> params);

	/**
	 * 获得结果集
	 *
	 * @param sql
	 *            SQL语句
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql);

	/**
	 *
	 *
	 * @author sdy
	 *
	 * @why 获得实体类list
	 * T 与Object同类型
	 *
	 * @param sql
	 * @param object
	 * 			与T的具体类同类
	 * @return
	 */
	public List<T> findBySqlGetEntity(String sql, Class<T> clazz);

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
	 * 获得结果�?
	 *
	 * @param sql
	 *            SQL语句
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, int page, int rows);

	/**
	 * 获得结果�?
	 *
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params);

	/**
	 * 获得结果�?
	 *
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @param page
	 *            要显示第几页
	 * @param rows
	 *            每页显示多少条
	 * @return 结果集
	 */
	public List<Object[]> findBySql(String sql, Map<String, Object> params,
            int page, int rows);

	public List<Object[]> findByHql(String hql, Map<String, Object> params,
            int page, int rows);
	/**
	 * 执行SQL语句
	 *
	 * @param sql
	 *            SQL语句
	 * @return 响应行数
	 */
	public int executeSql(String sql);

	/**
	 * 执行SQL语句
	 *
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 响应行数
	 */
	public int executeSql(String sql, Map<String, Object> params);

	/**
	 * 统计
	 *
	 * @param sql
	 *            SQL语句
	 * @return 数目
	 */
	public BigInteger countBySql(String sql);

	/**
	 * 统计
	 *
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数
	 * @return 数目
	 */
	public BigInteger countBySql(String sql, Map<String, Object> params);

	/**
	 *
	 * @param sql
	 *            SQL语句 select sum(*) from a;
	 * @return
	 */
	public Integer sum(String sql);

	/**
	 * 更新
	 *
	 * @param hql
	 * @return
	 */
	public Integer updateByHql(String hql);

	/**
	 * 更新
	 *
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer updateByHql(String hql, Object... params);

	/**
	 * 匹配信息
	 *
	 * @param hql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> matchByHql(String hql, int page, int rows);

	/**
	 * 获取匹配总的条数
	 *
	 * @param hql
	 * @return
	 */
	public Integer countMatchByHql(String hql);
}
