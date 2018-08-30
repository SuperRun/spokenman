package com.zust.itee.controller.resource;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.LecturerDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.manager.resource.LecturerManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 讲师 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/lecturer")
@Slf4j
@Api(tags = "讲师经历相关接口")
public class LecturerController {

    @Resource
    private LecturerManager lecturerManager;

    @GetMapping("")
    @ApiOperation("讲师管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "讲师姓名-模糊搜索", paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "组织id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "level", value = "讲师等级。1：国家级，2：省部级，3：司厅级，4：县处级，5：乡镇级",
                    allowableValues = "1,2,3,4,5", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "条线id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态码。-1：删除，1：正常", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    public LayuiResponse<LecturerDto> listLecture(
            @ApiIgnore LecturerDto lecturerDto,
            @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==讲师管理== lecturer:{},page:{}", lecturerDto, pageBaseDto);
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(lecturerManager.listLecturers(lecturerDto, pageBaseDto));
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("用户讲师经历管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "name", value = "讲师姓名-模糊搜索", paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "组织id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "level", value = "讲师等级。1：国家级，2：省部级，3：司厅级，4：县处级，5：乡镇级",
                    allowableValues = "1,2,3,4,5", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "条线id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态码。-1：删除，1：正常", paramType = "query",
                    dataType = "int")
    })
    public LayuiResponse<LecturerDto> listUserLecture(
            @PathVariable("userId") Integer userId,
            @ApiIgnore LecturerDto lecturerDto,
            @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==用户讲师管理== lecturer:{},page:{}", lecturerDto, pageBaseDto);
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                lecturerManager.listUserLecturers(userId, lecturerDto, pageBaseDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("讲师详情")
    @ApiImplicitParam(name = "id", value = "讲师id", paramType = "path")
    public JsonResponse<LecturerDto> getLecturer(@PathVariable("id") Integer id) {
        log.info("==获取讲师详情== id:{}", id);
        return JsonResponse.success(lecturerManager.getLecturerById(id));
    }

    @PutMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "讲师姓名-模糊搜索", paramType = "query"),
            @ApiImplicitParam(name = "introduction", value = "讲师简介", paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "组织id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "level", value = "讲师等级。1：国家级，2：省部级，3：司厅级，4：县处级，5：乡镇级",
                    allowableValues = "1,2,3,4,5", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "条线id", paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态码。-1：删除，1：正常", paramType = "query",
                    dataType = "int")
    })
    @ApiOperation("修改讲师信息")
    public JsonResponse<String> updateLecturer(@PathVariable("id") Integer id,
            @ApiIgnore LecturerDto lecturerDto) {
        log.info("==修改讲师信息==id:{},lecturer:{}", id, lecturerDto);
        lecturerManager.updateLecturer(lecturerDto, id);
        return JsonResponse.success("update lecturer ok");
    }

    @PostMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "name", value = "讲师姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "introduction", value = "讲师简介", paramType = "query"),
            @ApiImplicitParam(name = "orgId", value = "组织id", required = true, paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "level", value = "讲师等级。1：国家级，2：省部级，3：司厅级，4：县处级，5：乡镇级",
                    allowableValues = "1,2,3,4,5", required = true, paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "条线id", required = true, paramType = "query",
                    dataType = "int")
    })
    @ApiOperation("新增讲师")
    public JsonResponse<String> saveLecturer(@ApiIgnore LecturerDto lecturerDto) {
        log.info("==新增讲师== lecturer:{}", lecturerDto);
        lecturerManager.saveLecturer(lecturerDto);
        return JsonResponse.success("save lecturer ok");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除讲师")
    @ApiImplicitParam(name = "id", value = "讲师id", required = true, paramType = "path")
    public JsonResponse<String> deleteLecturer(@PathVariable("id") Integer id) {
        log.info("==删除讲师== id:{}", id);
        lecturerManager.deleteLecturer(id);
        return JsonResponse.success("delete lecturer ok");
    }
}
