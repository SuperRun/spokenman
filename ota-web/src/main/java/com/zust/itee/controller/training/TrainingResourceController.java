package com.zust.itee.controller.training;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.LearningResourceDto;
import com.zust.itee.dto.TrainingResourceDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingResourceSaveDto;
import com.zust.itee.dto.request.training.TrainingResourceSearchDto;
import com.zust.itee.dto.request.training.TrainingResourceUpdateDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.manager.training.TrainingResourceManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 培训资源 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/training/resource")
@Slf4j
@Api(tags = "培训资源相关接口")
public class TrainingResourceController {

    @Resource
    private TrainingResourceManager trainingResourceManager;

    @PostMapping("/")
    @ApiOperation("添加培训资源")
    public JsonResponse<String> saveTrainingResource(TrainingResourceSaveDto saveDto) {
        log.info("==添加培训资源== saveDto:{}", saveDto);
        trainingResourceManager.saveTrainingResource(saveDto);
        return JsonResponse.success("save training resource ok");
    }

    @GetMapping("/list/{trainingId}/unselected")
    @ApiOperation("获取未选择进培训的资源，平台用户可选择所有资源，单位用户可选择除其他单位发布的资源-需登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<LearningResourceDto> listUnselectedResource(
            @ApiIgnore SessionInfo sessionInfo,
            @PathVariable Integer trainingId, @ApiIgnore PageBaseDto pageBaseDto,
            TrainingSearchDto searchDto) {
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                trainingResourceManager.listUnselectedResources(sessionInfo, trainingId, searchDto,
                        pageBaseDto));
    }

    @GetMapping("/list/{trainingId}")
    @ApiOperation("获取培训配置的资源-不包含已删除资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<TrainingResourceDto> listTrainingResources(
            @PathVariable Integer trainingId, @ApiIgnore PageBaseDto pageBaseDto,
            TrainingResourceSearchDto searchDto) {
        pageBaseDto.ensureNotNull();
        log.info("==获取培训资源== trainingId:{},page:{}", trainingId, pageBaseDto);
        return LayuiResponse.success(
                trainingResourceManager.listTrainingResources(trainingId, pageBaseDto, searchDto));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新培训配置")
    public JsonResponse<String> updateTrainingResource(@PathVariable Integer id,
            TrainingResourceUpdateDto updateDto) {
        trainingResourceManager.updateTrainingResource(id, updateDto);
        return JsonResponse.success("update training resource ok");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除培训配置")
    public JsonResponse<String> deleteTrainingResourceById(@PathVariable Integer id) {
        log.info("==删除培训资源== id:{}", id);
        trainingResourceManager.deleteTrainingResource(id);
        return JsonResponse.success("delete training resource ok");
    }

    @DeleteMapping("/{trainingId}/{resourceId}")
    @ApiOperation("删除培训配置")
    public JsonResponse<String> deleteTrainingResource(@PathVariable Integer trainingId,
            @PathVariable Integer resourceId) {
        log.info("==删除培训资源== trainingId:{},resourceId:{}", trainingId, resourceId);
        trainingResourceManager.deleteTrainingResource(trainingId, resourceId);
        return JsonResponse.success("delete training resource ok");
    }
}
