package com.zust.itee.controller.resource;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.LearningResourceQuestionDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.request.resource.LearningResourceQuestionSaveDto;
import com.zust.itee.manager.resource.LearningResourceQuestionManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 学习资源问题关联 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("learning-resource/question")
@Api(tags = "学习资源问题相关接口")
@Slf4j
public class LearningResourceQuestionController {

    @Resource
    private LearningResourceQuestionManager learningResourceQuestionManager;

    @ApiOperation("获取学习资源问题详情")
    @GetMapping("/{id}")
    public JsonResponse<LearningResourceQuestionDto> getQuestion(@PathVariable Integer id) {
        log.info("==获取学习资源问题== id:{}", id);
        return JsonResponse.success(learningResourceQuestionManager.getById(id));
    }

    @ApiOperation("添加学习资源问题")
    @PostMapping("")
    public JsonResponse<String> saveQuestion(LearningResourceQuestionSaveDto saveDto) {
        log.info("==添加学习资源问题== save:{}", saveDto);
        learningResourceQuestionManager.saveQuestion(saveDto);
        return JsonResponse.success("save learning-resource question ok");
    }

    @ApiOperation("更新学习资源问题")
    @PutMapping("/{id}")
    public JsonResponse<String> updateQuestion(@PathVariable Integer id,
            LearningResourceQuestionSaveDto updateDto) {
        log.info("=更新学习资源问题== update:{}", updateDto);
        learningResourceQuestionManager.updateQuestion(id, updateDto);
        return JsonResponse.success("update learning-resource question ok");
    }

    @ApiOperation("删除学习资源问题")
    @DeleteMapping("/{id}")
    public JsonResponse<String> deleteQuestion(@PathVariable Integer id) {
        log.info("==删除学习资源问题== id:{}", id);
        learningResourceQuestionManager.deleteQuestion(id);
        return JsonResponse.success("delete learning-resource question ok");
    }

    @ApiOperation("获取学习资源中问题-不包含已删除问题")
    @GetMapping("/{resourceId}/list")
    public LayuiResponse<LearningResourceQuestionDto> listResourceQuestions(
            @PathVariable Integer resourceId) {
        log.info("==获取学习资源中问题== resourceId:{}", resourceId);
        return LayuiResponse.success(
                learningResourceQuestionManager.listResourceQuestions(resourceId));
    }
}
