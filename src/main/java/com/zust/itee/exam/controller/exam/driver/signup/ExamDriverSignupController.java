package com.zust.itee.exam.controller.exam.driver.signup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.service.exam.driver.signup.ExamDriverSignupService;

@Controller
@RequestMapping("exam/driver/signup")
public class ExamDriverSignupController {

    @Autowired
    ExamDriverSignupService examService;

    /**
     * @author sdy
     * @why 分页展示可报名的考试（供驾驶员选择报名）
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView driverHome(PageBaseDto pageBaseDto,
            SessionInfo sessionInfo) {
        ModelAndView modelAndView = new ModelAndView("exam/driver/signup");
        Integer driverId = sessionInfo.getUserId();
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            // 获取报名中所有考试
            List<ExamExposer> examExposers = examService.getExamHome(pageBaseDto,
                    driverId);
            // 获取页码信息
            pageBaseDto = examService.getExamPageBaseDto(pageBaseDto);

            modelAndView.addObject("exams", examExposers);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }

        return modelAndView;
    }

    /**
     * 驾驶员报名考试
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> examSignUp(SessionInfo sessionInfo,
            @PathVariable("examId") int examId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        int driverId = sessionInfo.getUserId();
        try {
            examService.signup(examId, driverId);
        } catch (Exception e) {
            // 返回出错页面
            jsonResult.setSuccess(false);
            return jsonResult;
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 驾驶员报名取消报名考试
     */
    @RequestMapping(value = "/{examId}/signup", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> signupCancel(
            @PathVariable("examId") Integer examId, SessionInfo sessionInfo) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        Integer driverId = sessionInfo.getUserId();
        try {
            Short memberOgrType = sessionInfo.getOrganizationType().getType();
            examService.signupCancel(examId, driverId, memberOgrType);
        } catch (Exception e) {
            // 返回错误页面
            jsonResult.setSuccess(false);
            return jsonResult;
        }
        return jsonResult;
    }
}
