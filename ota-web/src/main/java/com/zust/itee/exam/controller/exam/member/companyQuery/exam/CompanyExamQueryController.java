package com.zust.itee.exam.controller.exam.member.companyQuery.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.PageBaseDtoForSignup;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverExamDetailInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverListInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.ExamDetailInCompanyQuery;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.service.companyQuery.exam.CompanyExamQueryService;
import com.zust.itee.exam.service.exam.member.signup.ExamMemberSignupService;

/**
 * 车企查看考试安排
 *
 * @author sdy
 */
@Controller
@RequestMapping("exam/company")
public class CompanyExamQueryController {

    @Autowired
    CompanyExamQueryService companyExamQueryService;

    @Autowired
    ExamMemberSignupService examMemberSignupService;

    @RequestMapping("/list")
    public ModelAndView getAllExams(SessionInfo sessionInfo,
            PageBaseDto pageBaseDto) {
        ModelAndView modelAndView = new ModelAndView("2/exam/company/exams");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            Integer memberId = sessionInfo.getUserId();
            // 获取考试
            List<ExamExposer> exams = companyExamQueryService.getAllExams(
                    memberId, pageBaseDto);
            modelAndView.addObject("exams", exams);
            // 获取分页信息
            pageBaseDto = companyExamQueryService.getAllExamsPageBaseDto(
                    memberId, pageBaseDto);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * 获取考试详情
     */
    @RequestMapping(value = "/{examId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getExamDetail(
            @PathVariable("examId") Integer examId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            ExamDetailInCompanyQuery exam = companyExamQueryService
                    .getExamDetail(examId);
            jsonResult.setData(exam);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "获取考试详情失败");
        }
        return jsonResult;
    }

    /**
     * 获取报名了该考试的所有本车企的驾驶员
     */
    @RequestMapping(value = "/{examId}/drivers", method = RequestMethod.GET)
    public ModelAndView getOneExamDrivers(
            @PathVariable("examId") Integer examId, SessionInfo sessionInfo,
            PageBaseDto pageBaseDto) {
        ModelAndView modelAndView = new ModelAndView(
                "2/exam/company/examDrivers");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            Integer memberId = sessionInfo.getUserId();
            // 获取所有驾驶员
            List<DriverListInfoInCompanyQuery> drivers = companyExamQueryService
                    .getAllDrivers(examId, memberId, pageBaseDto);
            modelAndView.addObject("drivers", drivers);
            // 获取分页信息
            pageBaseDto = companyExamQueryService.getAllDriversPageBaseDto(
                    examId, memberId, pageBaseDto);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
            modelAndView.addObject("examId", examId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * 获取驾驶员参加考试详情
     */
    @RequestMapping(value = "{examId}/{driverId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getDriverExamInfo(
            @PathVariable("examId") Integer examId,
            @PathVariable("driverId") Integer driverId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            DriverExamDetailInfoInCompanyQuery driverExamDetail = companyExamQueryService
                    .getDriverExamDetail(examId, driverId);
            jsonResult.setData(driverExamDetail);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "获取详细信息失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/{examId}/drivers/signup", method = RequestMethod.GET)
    public ModelAndView getSignUpDrivers(
            SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            PageBaseDto pageBaseDto,
            PageBaseDtoForSignup pageBaseDtoForSignup,
            @RequestParam(name = "signupStatus", required = false,defaultValue = "-1") String signupStatus) {
        ModelAndView modelAndView = new ModelAndView("2/exam/company/signup");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        pageBaseDtoForSignup = PageBaseDtoForSignup.ENSURENOTNULL(pageBaseDtoForSignup);
        try {
            Integer orgId = sessionInfo.getOrganizationId();

            // 获取当前组织已报名驾驶员信息
            List<DriverInfoInExamManagement> drivers = examMemberSignupService
                    .getSignupDrivers(examId, pageBaseDto, orgId);
            modelAndView.addObject("drivers", drivers);
            // 获取当前组织已报名驾驶员分页信息
            pageBaseDto = examMemberSignupService.getSignupDriversPageBaseDto(examId,
                    pageBaseDto, orgId);
            modelAndView.addObject("pageBaseDto", pageBaseDto);

            // 获取当前组织未报名的驾驶员信息
            List<DriverInfoInExamManagement> driversForSignup = examMemberSignupService
                    .getHavenotSignedupDrivers(examId, pageBaseDtoForSignup,
                            orgId, signupStatus);
            modelAndView.addObject("driversForSignup", driversForSignup);
            // 获取当前组织未报名驾驶员分页信息
            pageBaseDtoForSignup = examMemberSignupService
                    .getHavenotSignupDriversPageBaseDto(examId,
                            pageBaseDtoForSignup, orgId, signupStatus);
            modelAndView
                    .addObject("pageBaseDtoForSignup", pageBaseDtoForSignup);

            // 传递筛选状态
            if (signupStatus == null) {
                signupStatus = DriverStatusEnum.NEEDTTRAINED.getStatus() + ";";
            }
            modelAndView.addObject("signupStatus", signupStatus);

            modelAndView.addObject("examId", examId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }
}
