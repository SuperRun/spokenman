package com.zust.itee.otadatadictionary.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zust.itee.otadatadictionary.client.domain.DataDictionary;

/**
 * 数据字典 mapper
 *
 * @author pojun
 */
@Repository
public interface DataDictionaryMapper {

    /**
     * 插入
     */
    Integer save(DataDictionary datadictionary);

    /**
     * 根据id获取
     */
    DataDictionary getById(Long id);

    /**
     * 根据查询条件获取（精确查询）
     *
     * @param params 字段、分页字段（offset、limit）
     */
    List<DataDictionary> listByFilter(Map params);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(DataDictionary datadictionary);

    /**
     * 更新
     */
    Integer update(DataDictionary datadictionary);
}
