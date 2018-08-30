package com.zust.itee.manager.resource;

import com.zust.itee.dto.LecturerDto;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.exception.BusinessException;

/**
 * 讲师 manager
 *
 * @author pojun
 */
public interface LecturerManager {

    /**
     * 分页搜索讲师
     *
     * @throws BusinessException
     */
    PageBaseDto<LecturerDto> listLecturers(LecturerDto lecturerDto, PageBaseDto pageBaseDto)
            throws BusinessException;

    PageBaseDto<LecturerDto> listUserLecturers(Integer userId, LecturerDto lecturerDto,
            PageBaseDto pageBaseDto) throws BusinessException;

    /**
     * 根据 id 获取讲师详情
     *
     * @throws BusinessException
     */
    LecturerDto getLecturerById(Integer id) throws BusinessException;

    /**
     * 更新讲师信息
     *
     * @throws BusinessException
     */
    void updateLecturer(LecturerDto lecturerDto, Integer id) throws BusinessException;

    /**
     * 新增讲师
     *
     * @throws BusinessException
     */
    void saveLecturer(LecturerDto lecturerDto) throws BusinessException;

    /**
     * 删除讲师
     *
     * @throws BusinessException
     */
    void deleteLecturer(Integer id) throws BusinessException;
}
