package com.zust.itee.exam.controller.exam.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.service.exam.member.ExamMemberBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("exam/member")
@Api(tags = "考试相关接口")
public class ExamMemberBaseController {

    private Logger logger = LoggerFactory.getLogger(ExamMemberBaseController.class);

    @Autowired
    ExamMemberBaseService examService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("获取有权限的所有考试列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<ExamExposer> examList(@ApiIgnore SessionInfo sessionInfo,
            @ApiIgnore com.zust.itee.dto.base.PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        PageBaseDto examPage = new PageBaseDto();
        examPage.setPage(pageBaseDto.getPage());
        examPage.setRows(pageBaseDto.getRows());
        examPage.setSearchKey("");
        examPage.setStatus((short) -2);
        List<ExamExposer> examExposers = examService.getMemberExamHome(sessionInfo.getUserId(),
                examPage);
        pageBaseDto.setData(examExposers);
        return LayuiResponse.success(examExposers);
    }

    /**
     * @author sdy
     * @why 工作人员获取有权限编辑的所有考试列表
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView memberHome(SessionInfo sessionInfo,
            PageBaseDto pageBaseDto) {
        ModelAndView modelAndView = new ModelAndView("2/exam/member/home");
        Integer memberId = sessionInfo.getUserId();
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            List<ExamExposer> memberExamExposers = examService
                    .getMemberExamHome(memberId, pageBaseDto);
            modelAndView.addObject("exams", memberExamExposers);
            pageBaseDto = examService.getMemberExamPageBaseDto(pageBaseDto, memberId);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
        } catch (Exception e) {
            logger.error("e:{}", e);
            return new ModelAndView("redirect:/404");
        }

        return modelAndView;
    }
}
