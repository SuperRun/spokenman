package com.zust.itee.exam.controller.exam.member.signup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.PageBaseDtoForSignup;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.exception.NoSonCompanyException;
import com.zust.itee.exam.exception.SignupCancelException;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.service.exam.driver.signup.ExamDriverSignupService;
import com.zust.itee.exam.service.exam.member.signup.ExamMemberSignupService;

@Controller
@RequestMapping("exam/member/signup")
public class ExamMemberSignupController {

    @Autowired
    ExamMemberSignupService examService;

    @Autowired
    ExamDriverSignupService examDriverSignupService;

    @Autowired
    OrganizationService organizationService;

    /**
     * @author sdy
     * @why 获取报名某场考试报名的所有驾驶员（外）
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    public ModelAndView signup(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            @RequestParam(name = "tid", required = false) Integer orgId) {
        ModelAndView modelAndView = new ModelAndView("2/exam/member/signup");
        try {
            Integer memberId = sessionInfo.getUserId();
            Integer memberOrgId = sessionInfo.getOrganizationId();
            // 获取当前车企
            orgId = organizationService.findTheNearestCompany(memberOrgId,
                    orgId);
            modelAndView.addObject("orgId", orgId);
            // 获取ztree所需数据
            OrganizationTree organizationTree = organizationService
                    .getOrganizationTreeWithCompany(sessionInfo
                            .getOrganizationId());
            modelAndView
                    .addObject("ztree", JSON.toJSONString(organizationTree));

            modelAndView.addObject("examId", examId);
        } catch (NoSonCompanyException e1) {
            // 获取ztree所需数据
            OrganizationTree organizationTree = organizationService
                    .getOrganizationTreeWithCompany(sessionInfo
                            .getOrganizationId());
            modelAndView
                    .addObject("ztree", JSON.toJSONString(organizationTree));

            modelAndView.addObject("examId", examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }

        return modelAndView;
    }

    /**
     * @author sdy
     * @why 获取报名某场考试报名的所有驾驶员（内）
     */
    @RequestMapping(value = "/{examId}/content", method = RequestMethod.GET)
    public ModelAndView signupContent(
            SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            PageBaseDto pageBaseDto,
            @RequestParam(name = "tid", required = false) Integer orgId,
            PageBaseDtoForSignup pageBaseDtoForSignup,
            @RequestParam(name = "signupStatus", required = false, defaultValue = "-1")
                    String signupStatus) {
        ModelAndView modelAndView = new ModelAndView(
                "2/exam/member/signupContent");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        pageBaseDtoForSignup = PageBaseDtoForSignup
                .ENSURENOTNULL(pageBaseDtoForSignup);
        try {
            Integer memberId = sessionInfo.getUserId();
            Integer memberOrgId = sessionInfo.getOrganizationId();
            // 获取当前车企
            orgId = organizationService.findTheNearestCompany(memberOrgId,
                    orgId);
            modelAndView.addObject("orgId", orgId);

            // 获取当前组织已报名驾驶员信息
            List<DriverInfoInExamManagement> drivers = examService
                    .getSignupDrivers(examId, pageBaseDto, orgId);
            modelAndView.addObject("drivers", drivers);
            // 获取当前组织已报名驾驶员分页信息
            pageBaseDto = examService.getSignupDriversPageBaseDto(examId,
                    pageBaseDto, orgId);
            modelAndView.addObject("pageBaseDto", pageBaseDto);

            // 获取当前组织未报名的驾驶员信息
            List<DriverInfoInExamManagement> driversForSignup = examService
                    .getHavenotSignedupDrivers(examId, pageBaseDtoForSignup,
                            orgId, signupStatus);
            modelAndView.addObject("driversForSignup", driversForSignup);
            // 获取当前组织未报名驾驶员分页信息
            pageBaseDtoForSignup = examService
                    .getHavenotSignupDriversPageBaseDto(examId,
                            pageBaseDtoForSignup, orgId, signupStatus);
            modelAndView
                    .addObject("pageBaseDtoForSignup", pageBaseDtoForSignup);

            // 获取当前车企名
            String companyName = examService.getCompanyName(orgId);
            modelAndView.addObject("companyName", companyName);

            // 传递筛选状态
            if (signupStatus == null) {
                signupStatus = DriverStatusEnum.NEEDTTRAINED.getStatus() + ";";
            }
            modelAndView.addObject("signupStatus", signupStatus);

            modelAndView.addObject("examId", examId);
        } catch (NoSonCompanyException e1) {
            // 获取当前组织已报名驾驶员信息
            List<DriverInfoInExamManagement> drivers = new ArrayList<>();
            modelAndView.addObject("drivers", drivers);
            // 获取当前组织已报名驾驶员分页信息
            pageBaseDto.setSum((long) 0);
            modelAndView.addObject("pageBaseDto", pageBaseDto);

            // 获取当前组织未报名的驾驶员信息
            List<DriverInfoInExamManagement> driversForSignup = new ArrayList<>();
            modelAndView.addObject("driversForSignup", driversForSignup);
            // 获取当前组织未报名驾驶员分页信息
            pageBaseDtoForSignup.setSumForSignup((long) 0);
            modelAndView
                    .addObject("pageBaseDtoForSignup", pageBaseDtoForSignup);

            // 获取当前车企名
            String companyName = "";
            modelAndView.addObject("companyName", companyName);

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

    /**
     * 取消驾驶员考试资格
     *
     * @author sdy
     * @why
     */
    @RequestMapping(value = "/{examId}/{driverId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> cancelDriverSignup(
            @PathVariable("examId") Integer examId,
            @PathVariable("driverId") Integer driverId, SessionInfo sessionInfo) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        try {
            Short memberOrgType = sessionInfo.getOrganizationType().getType();
            examDriverSignupService.signupCancel(examId, driverId, memberOrgType);
        } catch (SignupCancelException e1) {
            return new JsonResult<>(false, "取消时间已过");
        } catch (Exception e) {

            return new JsonResult<>(false, "取消失败" + e.getMessage());
        }

        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 批量报名
     */
    @RequestMapping(value = "/{examId}/import", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> signupImport(
            @PathVariable("examId") Integer examId,
            SessionInfo sessionInfo,
            @RequestParam(name = "signupDriversString", required = true)
                    String signupDriversString) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            examService.signupImport(signupDriversString, examId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "批量报名失败");
        }
        return jsonResult;
    }
}
