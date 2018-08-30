package com.zust.itee.otadatadictionary.client.service;

import java.util.List;

import com.zust.itee.otadatadictionary.client.domain.DataDictionary;
import com.zust.itee.otadatadictionary.client.enums.RootDataDictionary;

/**
 * 数据字典 service
 *
 * @author pojun
 */
public interface DataDictionaryService {

    /**
     * 创建或更新
     */
    Integer upsert(DataDictionary datadictionary);

    /**
     * 根据id获取
     */
    DataDictionary getById(Long id);

    /**
     * 根据查询条件分页获取(精确查询)
     */
    List<DataDictionary> listByFilter(DataDictionary datadictionary, Integer page, Integer limit);

    /**
     * 根据精确查询 count
     */
    Long countByFilter(DataDictionary datadictionary);

    /**
     * 获取子数据字典
     */
    List<DataDictionary> listSubData(Long uplink, Integer page, Integer limit);

    /**
     * 获取根节点所有子节点(uplink 为该节点)
     */
    List<DataDictionary> listSubData(RootDataDictionary rootDataDictionary);
}
