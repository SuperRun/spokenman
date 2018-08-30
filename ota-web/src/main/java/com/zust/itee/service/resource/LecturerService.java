package com.zust.itee.service.resource;

import java.util.List;

import com.zust.itee.entity.resource.Lecturer;

/**
 * 讲师 service
 *
 * @author pojun
 */
public interface LecturerService {

    /**
     * 创建或更新
     */
    Integer upsert(Lecturer lecturer);

    /**
     * 根据id获取
     */
    Lecturer getById(Integer id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<Lecturer> listByFilter(Lecturer lecturer, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Lecturer lecturer);

    /**
     * 根据搜索条件分页查询
     */
    List<Lecturer> listBySearch(Lecturer lecturer, Integer page, Integer limit);

    Long countBySearch(Lecturer lecturer);
}
