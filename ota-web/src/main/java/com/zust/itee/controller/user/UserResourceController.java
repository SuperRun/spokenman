package com.zust.itee.controller.user;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.UserResourceDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.user.UserResourceSaveDto;
import com.zust.itee.dto.request.user.UserResourceSearchDto;
import com.zust.itee.dto.request.user.UserResourceUpdateDto;
import com.zust.itee.manager.user.UserResourceManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户资源关联 controller
 *
 * @author pojun
 */
@Slf4j
@RestController
@RequestMapping("/user-resource")
@Api(tags = "用户学习资源相关接口")
public class UserResourceController {

    @Resource
    private UserResourceManager userResourceManager;

    @ApiOperation("获取用户的学习资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int") })
    @GetMapping("/{userId}/list")
    public JsonResponse<PageBaseDto<UserResourceDto>> listUserResource(
            @PathVariable Integer userId,
            @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==获取用户学习资源== userId:{},page:{}", userId, pageBaseDto);
        pageBaseDto = userResourceManager.listUserResources(userId, pageBaseDto);
        return JsonResponse.success(pageBaseDto);
    }

    @ApiOperation("获取某个用户学习资源")
    @GetMapping("/{id}")
    public JsonResponse<UserResourceDto> getUserResource(@PathVariable("id") Integer id) {
        log.info("==获取某个用户学习资源== id:{}", id);
        UserResourceDto userResourceDto = userResourceManager.getUserResourceById(id);
        return JsonResponse.success(userResourceDto);
    }

    @ApiOperation("用户添加学习资源")
    @PostMapping("")
    public JsonResponse<String> saveUserResource(@ApiIgnore SessionInfo sessionInfo,
            UserResourceSaveDto userResourceSaveDto) {
        log.info("==用户添加学习用户== session:{},userResourceSaveDto:{}", sessionInfo,
                userResourceSaveDto);
        userResourceManager.saveUserResource(sessionInfo, userResourceSaveDto);
        return JsonResponse.success("save successfully");
    }

    @ApiOperation("用户更新学习资源")
    @PutMapping("/{id}")
    public JsonResponse<String> updateUserResource(@PathVariable Integer id,
            UserResourceUpdateDto userResourceUpdateDto) {
        log.info("==用户更新学习资源== id:{},userResourceUpdateDto:{}", id, userResourceUpdateDto);
        userResourceManager.updateUserResource(id, userResourceUpdateDto);
        return JsonResponse.success("update successfully");
    }

    @ApiOperation("用户删除学习资源")
    @DeleteMapping("/{id}")
    public JsonResponse<String> deleteUserResource(@PathVariable Integer id) {
        log.info("==用户删除某个学习资源== id:{}", id);
        userResourceManager.deleteUserResource(id);
        return JsonResponse.success("delete successfully");
    }

    @ApiOperation("搜索用户的学习资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int") })
    @GetMapping("")
    public JsonResponse<PageBaseDto<UserResourceDto>> listUserResourceBySearch(
            UserResourceSearchDto userResourceSearchDto,
            @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==搜索用户学习资源== userResourceDto:{},page:{}", userResourceSearchDto, pageBaseDto);
        pageBaseDto = userResourceManager.listUserResourceBySearch(userResourceSearchDto,
                pageBaseDto);
        return JsonResponse.success(pageBaseDto);
    }

    @ApiOperation("根据用户资源ID获取学习记录")
    @GetMapping("/{resourceId}/learn")
    public JsonResponse<UserResourceDto> getUserResource(
            @ApiIgnore SessionInfo sessionInfo,
            @PathVariable Integer resourceId) {
        log.info("==用户资源ID== userId:{},resourceId:{}", sessionInfo.getOtaUserId(), resourceId);
        UserResourceDto userResourceDto = userResourceManager.getUserResource(
                sessionInfo.getOtaUserId(), resourceId);
        return JsonResponse.success(userResourceDto);
    }
}
