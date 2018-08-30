package com.zust.itee.exam.service.companyQuery.exam;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverExamDetailInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverListInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.ExamDetailInCompanyQuery;

/**
 * 车企查询考试安排相关接口
 *
 * @author sdy
 */
public interface CompanyExamQueryService {

    /**
     * 车企工作人员获取所有上级组织发起的考试
     *
     * @throws RuntimeException
     */
    List<ExamExposer> getAllExams(Integer memberId, PageBaseDto pageBaseDto)
            throws RuntimeException;

    /**
     * 获取上级发起考试分页信息
     */
    PageBaseDto getAllExamsPageBaseDto(Integer memberId, PageBaseDto pageBaseDto);

    /**
     * 获取考试详情
     */
    ExamDetailInCompanyQuery getExamDetail(Integer examId);

    /**
     * 获取参加该考试的所有驾驶员
     */
    List<DriverListInfoInCompanyQuery> getAllDrivers(Integer examId,
            Integer memberId, PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * 获取参加考试驾驶员分页信息
     */
    PageBaseDto getAllDriversPageBaseDto(Integer examId, Integer memberId,
            PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * 获取驾驶员参加考试详情
     */
    DriverExamDetailInfoInCompanyQuery getDriverExamDetail(Integer examId,
            Integer driverId);
}
