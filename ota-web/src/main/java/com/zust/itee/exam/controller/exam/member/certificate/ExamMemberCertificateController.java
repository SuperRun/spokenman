package com.zust.itee.exam.controller.exam.member.certificate;

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
import com.zust.itee.exam.service.exam.member.certificate.ExamMemberCertificateService;
import com.zust.itee.exam.service.exam.member.create.ExamMemberCreateService;

@Controller
@RequestMapping("exam/member/certificate")
public class ExamMemberCertificateController {

    @Autowired
    ExamMemberCertificateService examService;

    @Autowired
    ExamMemberCreateService examMemberCreateService;

    /**
     * @author sdy
     * @why 获取一场考试中所有可以录入证书的驾驶员信息
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.GET)
    public ModelAndView getDriversForCertificating(SessionInfo sessionInfo,
            HttpServletRequest request, @PathVariable("examId") Integer examId,
            PageBaseDto pageBaseDto,
            @RequestParam(name = "examName", required = false) String examName) {
        ModelAndView modelAndView = new ModelAndView(
                "2/exam/member/certificating");
        if (pageBaseDto.getSearchKey() == null) {
            pageBaseDto.setSearchKey("");
        }
        try {
            // 获取驾驶员信息
            List<DriverInfoInExamManagement> drivers = examService
                    .getCertificateDrivers(examId, pageBaseDto);
            modelAndView.addObject("drivers", drivers);
            // 获取excel模板信息
            String excelPath = examService.createDriverCertificateExcel(
                    request, examId);
            modelAndView.addObject("excelPath", excelPath);
            // 获取分页信息
            if (pageBaseDto.getSum() == null) {
                pageBaseDto = examService.getCertificatePageBaseDto(examId,
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

            return new ModelAndView("redirect/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 批量导入证书号信息
     */
    @RequestMapping(value = "/{examId}", method = RequestMethod.POST)
    public ModelAndView certificateAllDrivers(
            @PathVariable("examId") Integer examId,
            HttpServletRequest request,
            @RequestParam(name = "certificateExcelUrl", required = true) String certificateExcelUrl,
            @RequestParam(name = "basePath", required = true) String basePath) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/certificate/" + examId + "");
        try {
            // 根据url获取文件
            String realPath = request.getSession().getServletContext()
                    .getRealPath("");
            File file = new File(realPath + "/" + certificateExcelUrl);
            examService.certificateAllDrivers(file, examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 为一位驾驶员保存证书信息
     */
    @RequestMapping(value = "/{examId}/{driverId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> certificateOneDriver(
            SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId,
            @PathVariable("driverId") Integer driverId,
            @RequestParam(name = "certificate", required = false) String certificateNo,
            @RequestParam(name = "certificatePhoto", required = false) String certificatePhoto) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        try {
            examService.certificateOneDriver(examId, driverId, certificateNo, certificatePhoto);
        } catch (Exception e) {

            return new JsonResult<>(false, "证书保存失败！");
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 提交证书录入
     */
    @RequestMapping(value = "save/{examId}", method = RequestMethod.POST)
    public ModelAndView submitCertificate(SessionInfo sessionInfo,
            @PathVariable("examId") Integer examId) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/home");
        try {
            examService.submitCertificate(examId);
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }
}
