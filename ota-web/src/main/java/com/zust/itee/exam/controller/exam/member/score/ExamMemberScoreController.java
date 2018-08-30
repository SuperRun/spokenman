package com.zust.itee.exam.controller.exam.member.score;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.OrganizeExam;
import com.zust.itee.exam.service.exam.member.create.ExamMemberCreateService;
import com.zust.itee.exam.service.exam.member.score.ExamMemberScoreService;

@Controller
@RequestMapping("exam/member/score")
public class ExamMemberScoreController {

    @Autowired
    ExamMemberScoreService examService;

    @Autowired
    ExamMemberCreateService examMemberCreateService;

    /**
     * @author sdy
     * @why 获取某场考试所有成绩录入信息
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    public ModelAndView getScoreList(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            @RequestParam(name = "examName", required = false) String examName,
            PageBaseDto pageBaseDto, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("2/exam/member/score");
        if (pageBaseDto.getSearchKey() == null) {
            pageBaseDto.setSearchKey("");
        }
        try {
            // 获取所有驾驶员信息
            List<DriverInfoInExamManagement> drivers = examService
                    .getScoreDrivers(examId, pageBaseDto);
            modelAndView.addObject("drivers", drivers);
            // 获取excel模板信息
            String excelPath = examService.createScoreDriversExcel(request,
                    examId);
            modelAndView.addObject("excelPath", excelPath);
            if (pageBaseDto.getSum() == null) {
                pageBaseDto = examService.getScorePageBaseDto(examId,
                        pageBaseDto);
            }
            modelAndView.addObject("pageBaseDto", pageBaseDto);
            if (examName == null) {
                OrganizeExam organizeExam = examMemberCreateService
                        .getExamInfo(examId);
                examName = organizeExam.getExamName();
            }
            modelAndView.addObject("examName", examName);

            modelAndView.addObject("examId", examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 工作人员给一位驾驶员输入成绩
     */
    @RequestMapping(value = "/{examId}/{driverId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> scoreOneDriver(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            @PathVariable("driverId") Integer driverId,
            @RequestParam(name = "score", required = true) Double score) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            if (examService.scoreOneDriver(examId, driverId, score)) {
                jsonResult.setSuccess(true);
            } else {
                jsonResult.setSuccess(false);
            }
        } catch (Exception e) {

            return new JsonResult<>(false, "保存成绩失败！");
        }
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 成绩excel批量导入
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.POST)
    public ModelAndView scoreAllDrivers(
            SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            HttpServletRequest request,
            @RequestParam(name = "scoreExcelUrl", required = true) String scoreExcelUrl,
            @RequestParam(name = "basePath", required = true) String basePath) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/score/" + examId + "");
        try {
            //根据url获取文件
            String realPath = request.getSession().getServletContext().getRealPath("");
            File file = new File(realPath + "/" + scoreExcelUrl);
            examService.scoreAllDrivers(file, examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 提交成绩录入
     */
    @RequestMapping(value = "/save/{examId}", method = RequestMethod.POST)
    public ModelAndView submitScore(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/home");
        try {
            examService.submitScore(examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }
}
