package com.zust.itee.controller.training;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.TrainingDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.training.TrainingSaveDto;
import com.zust.itee.dto.request.training.TrainingSearchDto;
import com.zust.itee.dto.request.training.TrainingUpdateDto;
import com.zust.itee.manager.training.TrainingManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 培训 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("training")
@Slf4j
@Api(tags = "培训相关接口")
public class TrainingController {

    @Resource
    private TrainingManager trainingManager;

    @PostMapping("")
    @ApiOperation("创建培训")
    public JsonResponse<String> saveTraining(@ApiIgnore SessionInfo sessionInfo,
            TrainingSaveDto saveDto) {
        log.info("==创建培训== session:{},saveDto:{}", sessionInfo, saveDto);
        trainingManager.saveTraining(sessionInfo.getOtaUserId(), sessionInfo.getOrgId(), saveDto);
        return JsonResponse.success("save training ok");
    }

    @GetMapping("")
    @ApiOperation("查找培训")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<TrainingDto> searchTraining(@ApiIgnore SessionInfo sessionInfo,
            TrainingSearchDto searchDto, @ApiIgnore PageBaseDto pageBaseDto) {
        pageBaseDto.ensureNotNull();
        log.info("==查找培训== session：{}，search：{},page:{}", sessionInfo, searchDto, pageBaseDto);
        return LayuiResponse.success(
                trainingManager.listBySearch(sessionInfo.getOtaUserId(), sessionInfo.getType(),
                        sessionInfo.getOrgId(), searchDto, pageBaseDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取培训详情")
    public JsonResponse<TrainingDto> getById(@PathVariable Integer id) {
        log.info("==查询培训详情== id:{}", id);
        return JsonResponse.success(trainingManager.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新培训信息")
    public JsonResponse<String> updateTraining(@PathVariable Integer id,
            TrainingUpdateDto updateDto) {
        log.info("==更新培训== id:{},update:{}", id, updateDto);
        trainingManager.updateTraining(id, updateDto);
        return JsonResponse.success("update training ok");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除培训")
    public JsonResponse<String> deleteTraining(@PathVariable Integer id) {
        log.info("==删除培训== id:{}", id);
        trainingManager.deleteTraining(id);
        return JsonResponse.success("delete training ok");
    }
}

