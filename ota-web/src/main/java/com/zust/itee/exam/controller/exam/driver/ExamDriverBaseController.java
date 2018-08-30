package com.zust.itee.exam.controller.exam.driver;

import java.util.List;

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
import com.zust.itee.exam.service.exam.driver.ExamDriverBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("exam/driver")
@Api(tags = "个人用户考试相关接口")
public class ExamDriverBaseController {

    @Autowired
    ExamDriverBaseService examService;

    @GetMapping(value = "/my/list")
    @ResponseBody
    @ApiOperation("获取个人用户参加的考试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "searchKey", value = "搜索关键字", paramType = "query"),
            @ApiImplicitParam(name = "status",
                    value = "考试状态筛选。-1：全部，0：已发布，1：报名中，2：待考试，3：考试中，4：阅卷中，5：证书录入中，6：已完成",
                    allowableValues = "-1,0,1,2,3,4,5,6",
                    defaultValue = "-1",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<ExamExposer> driverExamList(@ApiIgnore SessionInfo sessionInfo,
            @ApiIgnore PageBaseDto pageBaseDto) {
        Integer driverId = sessionInfo.getUserId();
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);

        List<ExamExposer> examExposers = examService.getMyExam(driverId,
                pageBaseDto);
        pageBaseDto = examService.updateMyExamPageBaseDto(driverId,
                pageBaseDto);

        com.zust.itee.dto.base.PageBaseDto<ExamExposer> res = new com.zust.itee.dto.base
                .PageBaseDto<>();
        res.setData(examExposers);
        res.setRows(pageBaseDto.getRows());
        res.setPage(pageBaseDto.getPage());
        res.setSumAndPageNum(pageBaseDto.getSum());

        return LayuiResponse.success(res);
    }

    /**
     * @author sdy
     * @why 驾驶员获取我的考试
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getMyExamList(SessionInfo sessionInfo,
            PageBaseDto pageBaseDto) {
        ModelAndView modelAndView = new ModelAndView("exam/driver/myExam");
        Integer driverId = sessionInfo.getUserId();
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            // 获取考试信息
            List<ExamExposer> examExposers = examService.getMyExam(driverId,
                    pageBaseDto);
            modelAndView.addObject("exams", examExposers);
            // 更新分页信息
            pageBaseDto = examService.updateMyExamPageBaseDto(driverId,
                    pageBaseDto);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
        } catch (Exception e) {

            return new ModelAndView("redirect:404");
        }

        return modelAndView;
    }
}
