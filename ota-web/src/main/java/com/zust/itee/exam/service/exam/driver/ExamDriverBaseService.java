package com.zust.itee.exam.service.exam.driver;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.service.BaseService;

public interface ExamDriverBaseService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 驾驶员获取我的考试
     */
    List<ExamExposer> getMyExam(Integer driverId, PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取驾驶员我的考试分页信息
     */
    PageBaseDto updateMyExamPageBaseDto(Integer driverId,
            PageBaseDto pageBaseDto) throws RuntimeException;
}
