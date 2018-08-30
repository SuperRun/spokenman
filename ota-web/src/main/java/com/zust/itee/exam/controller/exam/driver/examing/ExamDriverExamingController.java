package com.zust.itee.exam.controller.exam.driver.examing;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.zust.itee.exam.dto.exam.driver.ChangeQuestion;
import com.zust.itee.exam.dto.exam.driver.ExamingBaseInfo;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.service.exam.PaperMemberService;
import com.zust.itee.exam.service.exam.driver.examing.ExamDriverExamingService;

@Controller
@RequestMapping("exam/driver")
public class ExamDriverExamingController {

    @Autowired
    ExamDriverExamingService examService;

    @Autowired
    PaperMemberService paperMemberService;

    /**
     * @author sdy
     * @why 第一次进入正式考试以及正式考试时切题
     */
    @RequestMapping(value = "/examing/execution/{examId}", method = RequestMethod.POST)
    public ModelAndView examing(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            ChangeQuestion changeQuestion) {
        ModelAndView modelAndView = new ModelAndView("2/exam/driver/examingNew");
        Integer driverId = sessionInfo.getUserId();
        /*
         * 获取题目信息
		 */
        if (changeQuestion == null || changeQuestion.getQuestionIdNow() == null) {
            // 第一次点进考试，先生成考试结果
            if (!examService.createExamResult(examId, driverId)) {
                // 还未组卷，先组卷
                try {
                    Integer paperId = examService.getPaperId(examId);
                    List<Tquestion> questions = paperMemberService
                            .createTquestionsAndShowQuestions(paperId);
                    examService.createExamResultWithQuestions(questions,
                            examId, driverId);
                } catch (Exception e) {

                    return new ModelAndView("redirect:/404");
                }
            }
            // 获取第一题
            changeQuestion = new ChangeQuestion();
            changeQuestion.setSequenceNext(1);
            changeQuestion.setSubmitable(!examService.checkHaveSubmit(examId,
                    driverId));
            changeQuestion.setHaveSubmit(examService.checkHaveSubmit(examId,
                    driverId));
        } else {
            // 做题中切题，先保存当前题答案，再得到下一题
            if (changeQuestion.isSubmitable()
                    && changeQuestion.getAnswer() != null
                    && changeQuestion.getAnswer() != "") {
                // 可以提交答案再保存答案
                try {
                    examService.saveAnswer(driverId, examId, changeQuestion);
                } catch (Exception e) {
                    return new ModelAndView("redirect:404");
                }
            }
        }
        QuestionModel questionModel = examService.getQuestion(driverId, examId,
                changeQuestion);
        modelAndView.addObject("question", questionModel);
        /*
         * 获取切题所需信息
		 */
        changeQuestion = examService.updateChangeQuestion(driverId, examId,
                changeQuestion, questionModel);
        if (changeQuestion.getSum() == null) {
            Long questionSum = examService.getQuestionSum(driverId, examId);
            changeQuestion.setSum(questionSum);
        }
        modelAndView.addObject("changeQuestion", changeQuestion);
        /*
         * 获取考试基本信息
		 */
        ExamingBaseInfo examingBaseInfo = examService.getExamingBaseInfo(
                driverId, examId);
        modelAndView.addObject("examingBaseInfo", examingBaseInfo);
        /*
		 * 获取考试时间信息
		 */
        Map<String, Object> examTime = examService.getexamDate(examId);
        modelAndView.addObject("examTime", examTime);

        modelAndView.addObject("examId", examId);
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 正式考试交卷
     */
    @RequestMapping(value = "/examing/save/{examId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> submitPaper(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            ChangeQuestion changeQuestion) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        Integer driverId = sessionInfo.getUserId();
        try {
            // 先保存当前题答案
            examService.saveAnswer(driverId, examId, changeQuestion);
            // 统计总分
            examService.countScoreInOfficialExam(examId, driverId);
        } catch (Exception e) {

            return new JsonResult<>(false, "交卷失败" + e.getMessage());
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 跳转至考生登陆考试页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toExamLogin() {
        ModelAndView modelAndView = new ModelAndView("2/exam/driver/login");
        return modelAndView;
    }

    /**
     * @param sfzNo :考生身份证
     * @author sdy
     * @why 考生登录考试
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> examLogin(
            @RequestParam(name = "sfzNo", required = true) String sfzNo,
            SessionInfo sessionInfo, HttpSession session) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        try {
            Integer examId = examService.examLogin(sfzNo);
            jsonResult.setData(examId);
            jsonResult.setSuccess(true);
            Tdriver driver = examService.driverLogin(sfzNo);
            boolean hasSession = true;
            if (null == sessionInfo) {
                sessionInfo = new SessionInfo();
                hasSession = false;
            }
            sessionInfo.setUserId(driver.getId());
            sessionInfo.setRealName(driver.getName());
            if (!hasSession) {
                session.setAttribute("sessionInfo", sessionInfo);
            }
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        return jsonResult;
    }

    /**
     * @author sdy 驾驶员考试退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> examLogout(SessionInfo sessionInfo) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            sessionInfo.setUserId(null);
            sessionInfo.setRealName(null);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("退出失败");
        }
        return jsonResult;
    }
}
