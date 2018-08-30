package com.zust.itee.mapper.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zust.itee.entity.resource.Lecturer;

/**
 * 讲师 mapper
 *
 * @author pojun
 */
@Mapper
@Repository
public interface LecturerMapper {

    /**
     * 插入
     */
    Integer save(Lecturer lecturer);

    /**
     * 根据id获取
     */
    Lecturer getById(Integer id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<Lecturer> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(Lecturer lecturer);

    /**
     * 更新
     */
    Integer update(Lecturer lecturer);

    /**
     * 搜索
     */
    List<Lecturer> listBySearch(Map params);

    /**
     * 查询搜索条件数量
     */
    Long countBySearch(Lecturer lecturer);
}
