package com.zust.itee.exam.service;

import com.zust.itee.exam.dto.exam.DriverDetailInfo;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.Tdriver;

/**
 * 考试相关公用接口
 *
 * @author Admini
 */
public interface ExamBaseService {

    /**
     * @author sdy
     * @why 点击报名出现考试详情
     */
    ExamExposer getexamDetail(Integer examId);

    /**
     * @author sdy
     * @why 获取驾驶员详细信息
     */
    DriverDetailInfo getDriverDetailInfo(Integer driverId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why Tdriver转化成dto
     */
    DriverDetailInfo transTdriverToDriverDetailInfo(Tdriver tdriver);
}
