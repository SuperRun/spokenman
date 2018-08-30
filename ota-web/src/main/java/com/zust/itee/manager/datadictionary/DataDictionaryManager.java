package com.zust.itee.manager.datadictionary;

import java.util.List;

import com.zust.itee.dto.DataDictionaryDto;
import com.zust.itee.enums.datadictionry.RootDataDictionary;
import com.zust.itee.exception.BusinessException;

/**
 * 数据字典manager
 *
 * @author pojun
 */
public interface DataDictionaryManager {

    /**
     * 根据父节点 id 获取所有子节点
     */
    List<DataDictionaryDto> listSubDDByRootId(Long rootId);

    /**
     * 根据 root dd获取所有子节点
     */
    List<DataDictionaryDto> listBySubDDByRoot(RootDataDictionary rootDataDictionary);

    /**
     * 新增组织条线
     */
    Integer saveOrgType(DataDictionaryDto dataDictionaryDto) throws BusinessException;
}
