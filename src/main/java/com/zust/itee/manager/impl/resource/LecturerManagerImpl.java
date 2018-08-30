package com.zust.itee.manager.impl.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.zust.itee.dto.LecturerDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.entity.datadictionary.DataDictionary;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.entity.resource.Lecturer;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.resource.LecturerManager;
import com.zust.itee.service.datadictionary.DataDictionaryService;
import com.zust.itee.service.org.OrganizationService;
import com.zust.itee.service.resource.LecturerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 讲师 manager 实现
 *
 * @author pojun
 */
@Service
@Slf4j
public class LecturerManagerImpl implements LecturerManager {

    @Resource
    private LecturerService lecturerService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private DataDictionaryService dataDictionaryService;

    @SuppressWarnings("unchecked")
    @Override
    public PageBaseDto<LecturerDto> listLecturers(LecturerDto lecturerDto,
            PageBaseDto pageBaseDto) throws BusinessException {
        return searchLecturers(lecturerDto, pageBaseDto);
    }

    @Override
    public PageBaseDto<LecturerDto> listUserLecturers(Integer userId, LecturerDto lecturerDto,
            PageBaseDto pageBaseDto) throws BusinessException {
        lecturerDto.setUserId(userId);
        return searchLecturers(lecturerDto, pageBaseDto);
    }

    @Override
    public LecturerDto getLecturerById(Integer id) throws BusinessException {
        log.info("==获取讲师详情== id:{}", id);
        Lecturer lecturer = validateNotNull(id);
        log.info("==获取讲师详情,service 响应== lecturer:{}", lecturer);
        LecturerDto lecturerDto = convertToDto(lecturer);
        log.info("==获取讲师详情,转换后结果== res:{}", lecturerDto);
        return lecturerDto;
    }

    @Override
    public void updateLecturer(LecturerDto lecturerDto, Integer id) throws BusinessException {
        log.info("==更新讲师信息== id:{},lecturer:{}", id, lecturerDto);
        validateNotNull(id);
        lecturerDto.setId(id);
        Lecturer lecturer = convertToEntity(lecturerDto);
        log.info("==更新讲师信息，转换后信息== lecturer:{}", lecturer);
        lecturerService.upsert(lecturer);
    }

    @Override
    public void saveLecturer(LecturerDto lecturerDto) throws BusinessException {
        log.info("==新增讲师== lecturer:{}", lecturerDto);
        lecturerDto.setStatus(NormalStatusType.NORMAL.getStatus());
        Lecturer lecturer = convertToEntity(lecturerDto);
        log.info("==新增讲师，转化后结果== lecturer:{}", lecturer);
        lecturerService.upsert(lecturer);
    }

    @Override
    public void deleteLecturer(Integer id) throws BusinessException {
        log.info("==删除讲师== id:{}", id);
        Lecturer lecturer = validateNotNull(id);
        lecturer.setStatus(NormalStatusType.DELETE.getStatus());
        lecturerService.upsert(lecturer);
    }

    private PageBaseDto<LecturerDto> searchLecturers(LecturerDto lecturerDto,
            PageBaseDto pageBaseDto) {
        log.info("==搜索讲师== lecture:{},page:{}", lecturerDto, pageBaseDto);
        Lecturer query = convertToEntity(lecturerDto);
        log.info("==搜索讲师，搜索== query:{},page:{}", query, pageBaseDto);
        List<Lecturer> lecturers = lecturerService.listBySearch(query, pageBaseDto.getPage(),
                pageBaseDto.getRows());
        log.info("==搜索讲师，搜索结果== res:{}", lecturers);
        Long sum = lecturerService.countBySearch(query);
        log.info("==搜索讲师，结果总数== sum:{}", sum);

        List<LecturerDto> lecturerDtos = convertToDtoList(lecturers);
        pageBaseDto.setData(lecturerDtos);
        pageBaseDto.setSumAndPageNum(sum);
        log.info("==搜索讲师，转换后结果== res:{}", pageBaseDto);

        return pageBaseDto;
    }

    private Lecturer validateNotNull(Integer id) {
        Lecturer lecturer = lecturerService.getById(id);
        if (lecturer == null) {
            throw new BusinessException(String.format("%s 讲师不存在", id));
        }
        return lecturer;
    }

    private LecturerDto convertToDto(Lecturer lecturer) {
        LecturerDto lecturerDto = new LecturerDto();
        BeanUtils.copyProperties(lecturer, lecturerDto);

        // 组织名称
        Organization organization = organizationService.getById(lecturer.getOrgId());
        if (organization != null) {
            lecturerDto.setOrgName(organization.getShortName());
        }
        // 讲师等级名称
        OrgLevelType orgLevelType = OrgLevelType.getByLevel(lecturer.getLevel());
        if (orgLevelType != null) {
            lecturerDto.setLevelName(orgLevelType.getName());
        }
        // 条线名称
        DataDictionary dataDictionary = dataDictionaryService.getById(lecturer.getTypeId());
        if (dataDictionary != null) {
            lecturerDto.setTypeName(dataDictionary.getValueStr());
        }
        // 状态名称 TODO
        Optional<NormalStatusType> normalStatusType = NormalStatusType.getByStatus(
                lecturer.getStatus());
        normalStatusType.ifPresent(
                type -> lecturerDto.setStatusName(type.getDescription()));
        return lecturerDto;
    }

    private List<LecturerDto> convertToDtoList(List<Lecturer> lecturers) {
        return lecturers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Lecturer convertToEntity(LecturerDto lecturerDto) {
        Lecturer lecturer = new Lecturer();
        BeanUtils.copyProperties(lecturerDto, lecturer);
        return lecturer;
    }
}
