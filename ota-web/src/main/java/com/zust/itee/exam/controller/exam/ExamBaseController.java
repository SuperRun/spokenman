package com.zust.itee.exam.controller.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.exam.DriverDetailInfo;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.service.ExamBaseService;

/**
 * 工作人员，驾驶员共有的操作
 *
 * @author sdy
 */

@Controller
@RequestMapping("/exam")
public class ExamBaseController {

    @Autowired
    ExamBaseService examService;

    /**
     * @author sdy
     * @why 点击考试链接获取考试详情
     */
    @RequestMapping(value = "/{examId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<ExamExposer> examDetail(@PathVariable("examId") Integer examId) {
        JsonResult<ExamExposer> jsonResult = new JsonResult<ExamExposer>();
        try {
            ExamExposer examExposer = examService.getexamDetail(examId);
            jsonResult.setData(examExposer);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 点击驾驶员姓名获取驾驶员详细信息
     */
    @RequestMapping(value = "/{driverId}/driverInfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<DriverDetailInfo> getDriverDetailInfo(
            @PathVariable("driverId") Integer driverId) {
        JsonResult<DriverDetailInfo> jsonResult = new JsonResult<DriverDetailInfo>();
        try {
            DriverDetailInfo detailInfo = examService
                    .getDriverDetailInfo(driverId);
            jsonResult.setData(detailInfo);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }
}
