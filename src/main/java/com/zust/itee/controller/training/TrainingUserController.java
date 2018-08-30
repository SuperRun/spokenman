package com.zust.itee.controller.training;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.TrainingUserDto;
import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingUserSaveDto;
import com.zust.itee.dto.request.training.TrainingUserSearchDto;
import com.zust.itee.dto.request.training.TrainingUserUpdateDto;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.manager.training.TrainingUserManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 培训用户 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/training-user")
@Slf4j
@Api(tags = "培训用户相关接口")
public class TrainingUserController {

    @Resource
    private TrainingUserManager trainingUserManager;

    @GetMapping("/{id}")
    @ApiOperation("获取用户培训记录详情")
    public JsonResponse<TrainingUserDto> getById(@PathVariable Integer id) {
        return JsonResponse.success(trainingUserManager.getById(id));
    }

    @GetMapping("/training/{trainingId}/unselected")
    @ApiOperation("获取未参与该培训的用户。平台用户可选择所有用户，单位用户仅可选择本单位用户。需登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<UserDto> listUnselectedUser(@ApiIgnore SessionInfo sessionInfo,
            @PathVariable Integer trainingId,
            @ApiIgnore PageBaseDto pageBaseDto, UserSearchDto searchDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                trainingUserManager.listUnselectedUserBySearch(sessionInfo, trainingId, searchDto,
                        pageBaseDto));
    }

    @GetMapping("/training/{trainingId}")
    @ApiOperation("根据培训搜索获取某次培训用户培训情况-不包含已取消用户培训")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<TrainingUserDto> listByTraining(@PathVariable Integer trainingId,
            TrainingUserSearchDto searchDto, @ApiIgnore PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                trainingUserManager.listByTraining(trainingId, searchDto, pageBaseDto));
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("根据用户搜索获取某用户用户培训情况-不包含已取消用户培训")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<TrainingUserDto> listByUser(@PathVariable Integer userId,
            TrainingUserSearchDto searchDto, @ApiIgnore PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                trainingUserManager.listByUser(userId, searchDto, pageBaseDto));
    }

    @PostMapping("")
    @ApiOperation("添加用户培训记录：用户培训报名/培训名单添加")
    public JsonResponse<String> saveTrainingUser(TrainingUserSaveDto saveDto) {
        trainingUserManager.saveTrainingUser(saveDto);
        return JsonResponse.success("save training user ok");
    }

    @PutMapping("/status/{id}")
    @ApiOperation("修改用户培训记录状态")
    @ApiImplicitParams({ @ApiImplicitParam(name = "status", value = "用户培训记录状态，1：通过，2：不通过",
            allowableValues = "1,2", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "用户培训记录 id", paramType = "path") })
    public JsonResponse<String> updateTrainingUserStatus(Short status, @PathVariable Integer id) {
        trainingUserManager.updateTrainingUserStatus(id, status);
        return JsonResponse.success("update training user status ok");
    }

    @PutMapping("/{id}")
    @ApiOperation("更新用户培训记录信息")
    @ApiImplicitParam(name = "id", value = "用户培训记录 id", paramType = "path", required = true)
    public JsonResponse<String> updateTrainingUser(@PathVariable Integer id,
            TrainingUserUpdateDto updateDto) {
        trainingUserManager.updateTrainingUser(id, updateDto);
        return JsonResponse.success("update training user ok");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户培训记录")
    public JsonResponse<String> deleteTrainingUser(@PathVariable Integer id) {
        trainingUserManager.deleteTrainingUser(id);
        return JsonResponse.success("delete training user ok");
    }

    @GetMapping("sign-up/{userId}/{trainingId}")
    @ApiOperation("判断用户是否参加某培训")
    public JsonResponse<Boolean> judgeSignUp(Integer userId, Integer trainingId) {
        return JsonResponse.success(trainingUserManager.judgeSignUp(userId, trainingId));
    }

    @GetMapping("detail/{userId}/{trainingId}/")
    @ApiOperation("获取用户培训情况")
    public JsonResponse<TrainingUserDto> getUserTrainingDetail(Integer userId, Integer trainingId) {
        return JsonResponse.success(trainingUserManager.getUserTraining(userId, trainingId));
    }
}
