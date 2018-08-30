package com.zust.itee.controller.resource;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.resource.LearningResourceSaveDto;
import com.zust.itee.dto.request.resource.LearningResourceSearchDto;
import com.zust.itee.manager.resource.LearningResourceManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 学习资源 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/learning-resource")
@Api(tags = "学习资源相关接口")
@Slf4j
public class LearningResourceController {

    @Resource
    private LearningResourceManager learningResourceManager;

    @GetMapping("")
    @ApiOperation("学习资源搜索-需登录。平台管理员可见所有资源；单位管理员可见本单位发布资源；个人用户可见有权限的资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<LearningResourceDto> searchLearningResource(
            @ApiIgnore SessionInfo sessionInfo,
            LearningResourceSearchDto searchDto, @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==学习资源搜索== session:{},search:{},page:{}", sessionInfo, searchDto, pageBaseDto);
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                learningResourceManager.searchLearningResource(sessionInfo.getType(),
                        sessionInfo.getOrgId(), sessionInfo.getLevel(), searchDto, pageBaseDto));
    }

    @PostMapping("")
    @ApiOperation("创建资源-需登录")
    public JsonResponse<String> saveLearningResource(@ApiIgnore SessionInfo sessionInfo,
            LearningResourceSaveDto saveDto) {
        log.info("==新增学习资源== session:{},save:{}", sessionInfo, saveDto);
        learningResourceManager.saveLearningResource(sessionInfo, saveDto);
        return JsonResponse.success("save learningResource ok");
    }

    @GetMapping("/{id}")
    public JsonResponse<LearningResourceDto> getById(@PathVariable Integer id) {
        log.info("==根据 id 获取学习资源详情== id:{}", id);
        return JsonResponse.success(learningResourceManager.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新资源信息")
    public JsonResponse<String> updateLearningResource(@PathVariable("id") Integer id,
            LearningResourceSaveDto updateDto) {
        log.info("==更新学习资源== id:{},updateDto:{}", id, updateDto);
        learningResourceManager.updateLearningResource(id, updateDto);
        return JsonResponse.success("update learning resource ok");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除学习资源")
    public JsonResponse<String> deleteLearningResource(@PathVariable("id") Integer id) {
        log.info("==删除学习资源== id:{}", id);
        learningResourceManager.deleteLearningResource(id);
        return JsonResponse.success("delete learning resource ok");
    }

    @PutMapping("/status/{id}")
    @ApiOperation("修改资源状态")
    @ApiImplicitParam(name = "status", allowableValues = "-1,1", value = "资源状态。1：正常，-1：已删除",
            paramType = "query", dataType = "int", required = true)
    public JsonResponse<String> updateResourceStatus(@PathVariable Integer id, Short status) {
        learningResourceManager.updateResourceStatus(id, status);
        return JsonResponse.success("update learning resource status ok");
    }
}
