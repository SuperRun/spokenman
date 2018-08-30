package com.zust.itee.exam.service;

/**
 * 基础service方法，增删改
 *
 * @author Admini
 *
 * @param <T>
 */
public interface BaseService<T> {
	public T getByHql(String hql);

	public void save(T t);

	/**
	 * 保存对象
	 *
	 * @param t
	 *            对象
	 */
	public void saveOrupdate(T t);

	/**
	 * 根据id得到对象
	 *
	 * @param id
	 * @return
	 */
	public T getById(String id);

	T getById(Integer id);

	/**
	 * 删除对象
	 *
	 * @param t
	 *            对象
	 */
	public void delete(T t);

	/**
	 * 根据id删除对象
	 *
	 * @param id
	 */
	public void deleteById(String id);

}
