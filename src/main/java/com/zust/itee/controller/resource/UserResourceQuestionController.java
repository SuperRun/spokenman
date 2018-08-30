package com.zust.itee.controller.resource;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.UserResourceQuestionDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.resource.UserResourceQuestionSaveDto;
import com.zust.itee.manager.resource.UserResourceQuestionManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户培训资源答题记录 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/user-resource-question")
@Api(tags = "用户培训资源答题记录相关接口")
@Slf4j
public class UserResourceQuestionController {

    @Resource
    private UserResourceQuestionManager userResourceQuestionManager;

    @PostMapping("")
    @ApiOperation("增加答题记录")
    public JsonResponse<String> saveUserResourceQuestion(
            UserResourceQuestionSaveDto saveDto,
            @ApiIgnore SessionInfo sessionInfo) {
        saveDto.setUserId(sessionInfo.getOtaUserId());
        userResourceQuestionManager.saveUserResourceQuestion(saveDto);
        return JsonResponse.success("save user resource question ok");
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("获取用户所有答题记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<UserResourceQuestionDto> listByUser(@PathVariable Integer userId,
            @ApiIgnore PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(userResourceQuestionManager.listByUser(userId, pageBaseDto));
    }

    @GetMapping("/question/{questionId}")
    @ApiOperation("根据资源问题获取答题记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<UserResourceQuestionDto> listByQuestion(@PathVariable Integer questionId,
            @ApiIgnore PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                userResourceQuestionManager.listByResourceQuestion(questionId, pageBaseDto));
    }
}
