package com.zust.itee.exam.service.exam.member;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.service.BaseService;

public interface ExamMemberBaseService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 工作人员获取权限内的所有考试
     */
    List<ExamExposer> getMemberExamHome(int memberId, PageBaseDto pageBaseDto)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 工作人员考试列表分页信息
     */
    PageBaseDto getMemberExamPageBaseDto(PageBaseDto pageBaseDto, Integer memberId)
            throws RuntimeException;
}
