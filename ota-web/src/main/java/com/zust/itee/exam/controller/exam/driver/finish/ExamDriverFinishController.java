package com.zust.itee.exam.controller.exam.driver.finish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.exam.driver.ExamFinishDriverInfo;
import com.zust.itee.exam.service.exam.driver.finish.ExamDriverFinishService;

/**
 * 驾驶员已完成考试相关操作
 *
 * @author sdy
 */
@Controller
@RequestMapping("exam/driver/finish")
public class ExamDriverFinishController {

    @Autowired
    ExamDriverFinishService examService;

    /**
     * @author sdy
     * @why 查看已完成考试个人信息
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getExamFinishInfo(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        Integer driverId = sessionInfo.getUserId();
        try {
            ExamFinishDriverInfo examInfo = examService.getExamFinishInfo(driverId, examId);
            jsonResult.setData(examInfo);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        return jsonResult;
    }
}
