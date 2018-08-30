package com.zust.itee.exam.controller.exam.member.finish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.exam.member.ExamFinishInfo;
import com.zust.itee.exam.service.exam.member.finish.ExamMemberFinishService;

/**
 * 已完成的考试相关操作
 *
 * @author terry
 */
@Controller
@RequestMapping("exam/member/finish")
public class ExamMemberFinishController {

    @Autowired
    private ExamMemberFinishService examService;

    /**
     * @author sdy
     * @why 查看具体已完成考试的考试情况
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getExamFinishInfo(@PathVariable("examId") Integer examId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            ExamFinishInfo examInfo = examService.getFinishExamInfo(examId);
            jsonResult.setData(examInfo);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        return jsonResult;
    }
}
