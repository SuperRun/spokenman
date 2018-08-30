package com.zust.itee.manager.impl.datadictionary;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.DataDictionaryDto;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.datadictionry.DataDictionaryLevel;
import com.zust.itee.enums.datadictionry.RootDataDictionary;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.datadictionary.DataDictionaryManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据字典 manager 实现类
 *
 * @author pojun
 */
@Service
@Slf4j
public class DataDictionaryManagerImpl implements DataDictionaryManager {

    @Resource
    private DataDictionaryService dataDictionaryService;

    @Override
    public List<DataDictionaryDto> listSubDDByRootId(Long rootId) {
        log.info("==根据父节点id获取子节点== rootId:{}", rootId);
        List<DataDictionary> dataDictionaries = dataDictionaryService.listSubData(rootId, 1,
                Integer.MAX_VALUE);
        log.info("==根据父节点id获取子节点，data-dictionary响应== subs:{}", dataDictionaries);
        List<DataDictionaryDto> dataDictionaryDtos = convertToDtoList(dataDictionaries);
        log.info("==根据父节点id获取子节点，转换后结果== res:{}", dataDictionaryDtos);
        return dataDictionaryDtos;
    }

    @Override
    public List<DataDictionaryDto> listBySubDDByRoot(RootDataDictionary rootDataDictionary) {
        log.info("==根据root节点信息获取子节点== root:{}", rootDataDictionary);
        List<DataDictionary> dataDictionaries = dataDictionaryService.listSubData(
                rootDataDictionary);
        log.info("==根据root节点信息获取子节点，data-dictionary响应== subs:{}", dataDictionaries);
        List<DataDictionaryDto> res = convertToDtoList(dataDictionaries);
        log.info("==根据root节点信息获取子节点，转换后结果== res:{}", res);
        return res;
    }

    @Override
    public Integer saveOrgType(DataDictionaryDto dataDictionaryDto) throws BusinessException {
        log.info("==新增组织条线== data:{}", dataDictionaryDto);
        List<DataDictionary> dataDictionaries = dataDictionaryService.listByFilter(
                DataDictionary.builder()
                        .level(RootDataDictionary.ORG_TYPE.getLevel())
                        .valueStr(RootDataDictionary.ORG_TYPE.getValueStr())
                        .build(), 1, 1);
        if (dataDictionaries == null || dataDictionaries.isEmpty()) {
            throw new BusinessException("组织条线根数据字典节点不存在");
        }
        DataDictionary orgTypeRoot = dataDictionaries.get(0);
        dataDictionaryDto.setLevel(DataDictionaryLevel.SUB.getLevel());
        dataDictionaryDto.setUplink(orgTypeRoot.getId());
        dataDictionaryDto.setCtrlId(orgTypeRoot.getId());
        dataDictionaryDto.setStatus(NormalStatusType.NORMAL.getStatus());
        DataDictionary orgType = convertToEntity(dataDictionaryDto);
        log.info("==新增组织条线，转换后entity == orgType:{}", orgType);
        return dataDictionaryService.upsert(orgType);
    }

    private DataDictionaryDto convertToDto(DataDictionary dataDictionary) {
        DataDictionaryDto dataDictionaryDto = new DataDictionaryDto();
        BeanUtils.copyProperties(dataDictionary, dataDictionaryDto);
        return dataDictionaryDto;
    }

    private List<DataDictionaryDto> convertToDtoList(List<DataDictionary> dataDictionaries) {
        return dataDictionaries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DataDictionary convertToEntity(DataDictionaryDto dataDictionaryDto) {
        DataDictionary dataDictionary = new DataDictionary();
        BeanUtils.copyProperties(dataDictionaryDto, dataDictionary);
        return dataDictionary;
    }
}
