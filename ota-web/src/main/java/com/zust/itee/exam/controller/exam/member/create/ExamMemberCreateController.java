package com.zust.itee.exam.controller.exam.member.create;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.exam.member.OrganizeExam;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;
import com.zust.itee.exam.service.AnnouncementService;
import com.zust.itee.exam.service.exam.PaperMemberService;
import com.zust.itee.exam.service.exam.member.create.ExamMemberCreateService;

@Controller
@RequestMapping("exam/member")
public class ExamMemberCreateController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat sdfAjax = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @Autowired
    ExamMemberCreateService examService;

    @Autowired
    PaperMemberService paperMemberService;

    @Autowired
    AnnouncementService announcementService;

    /**
     * @author sdy
     * @why 跳转到创建考试页面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView toCreateExam(
            @RequestParam(name = "examId", required = false) Integer examId) {
        ModelAndView modelAndView = new ModelAndView("2/exam/member/createExam");
        return modelAndView;
    }

    /**
     * @throws
     * @author sdy
     * @why 工作人员创建考试
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView organizeExam(SessionInfo sessionInfo,
            OrganizeExam organizeExam) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/paper/member/create");
        Integer memberId = sessionInfo.getUserId();
        Integer examId;
        try {
            // 创建考试
            examId = examService.organizeExam(memberId, organizeExam);
            // 将考试id传入下一个页面
            modelAndView.addObject("examId", examId);
            modelAndView.addObject("examType", organizeExam.getExamType());
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        if (organizeExam.getPaperType().equals("exist")) {
            // 选择已有试卷,跳转到试卷选择页
            modelAndView.clear();
            modelAndView.setViewName("redirect:/paper/member/" + examId
                    + "/select");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 跳转至编辑考试
     */
    @RequestMapping(value = "/edit/{examId}", method = RequestMethod.GET)
    public ModelAndView toEditExam(@PathVariable("examId") Integer examId) {
        ModelAndView modelAndView = new ModelAndView("2/exam/member/editExam");
        try {
            OrganizeExam organizeExam = examService.getExamInfo(examId);
            modelAndView.addObject("exam", organizeExam);
            modelAndView.addObject("examId", examId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 提交编辑考试
     */
    @RequestMapping(value = "/edit/{examId}", method = RequestMethod.POST)
    public ModelAndView editExam(@PathVariable("examId") Integer examId,
            OrganizeExam organizeExam, SessionInfo sessionInfo) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/home");
        try {
            Integer memberId = sessionInfo.getUserId();
            examService.organizeExam(memberId, organizeExam);
        } catch (Exception e) {
            new ModelAndView("redirect:/404");
        }
        //		if (organizeExam.getPaperType().equals("exist")) {
        //			// 选择已有试卷,跳转到试卷选择页
        //			modelAndView.clear();
        //			modelAndView.setViewName("redirect:/paper/member/" + examId
        //					+ "/select");
        //		} else if (organizeExam.getPaperType().equals("new")) {
        //			// 修改已选试卷，跳转至试卷修改页
        //			Integer paperId = examService.getPaperId(examId);
        //			modelAndView.clear();
        //			modelAndView.setViewName("redirect:/paper/member/edit/" + paperId);
        //			modelAndView.addObject("examType", organizeExam.getExamType());
        //		}
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 工作人员删除考试
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> deleteExam(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        Integer memberId = sessionInfo.getUserId();
        try {
            examService.deleteExam(memberId, examId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "删除考试失败");
        }
        return jsonResult;
    }

    /**
     * 跳转至为考试发布通知
     */
    @RequestMapping(value = "/{examId}/announcement", method = RequestMethod.GET)
    public ModelAndView toSetTraining(@PathVariable("examId") Integer examId) {
        ModelAndView modelAndView = new ModelAndView(
                "2/exam/member/setAnnouncement");
        modelAndView.addObject("examId", examId);
        modelAndView.addObject("typeEnum", AnnouncementTypeEnum.EXAMNOTICE);
        OrganizeExam organizeExam = examService.getExamInfo(examId);
        String examName = organizeExam.getExamName();
        modelAndView.addObject("examName", examName);
        return modelAndView;
    }

    /**
     * 提交考试公告
     */
    @RequestMapping(value = "/{examId}/announcement", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> setTraining(
            @PathVariable("examId") Integer examId,
            AnnouncementDto announcementDto,
            @RequestParam(value = "closureDateStr", required = false) String closureDateStr,
            SessionInfo sessionInfo) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
			/*
			 * 创建公告
			 */
            announcementDto.setType(AnnouncementTypeEnum.EXAMNOTICE);
            if (closureDateStr != null && closureDateStr.length() > 0) {
                announcementDto.setClosureDate(sdfAjax.parse(closureDateStr));
            }
            announcementDto.setCreateDate(new Date());
            // 从sessionInfo获取信息
            announcementDto.setMemberId(sessionInfo.getUserId());
            announcementDto.setOrganizationId(sessionInfo.getOrganizationId());
            announcementDto.setReadCount(0);
            Integer annId = announcementService.createAnn(announcementDto);
			/*
			 * 关联考试与公告
			 */
            examService.connectExamAndAnnouncement(examId, annId);

            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "创建通知失败");
        }
        return jsonResult;
    }
}
