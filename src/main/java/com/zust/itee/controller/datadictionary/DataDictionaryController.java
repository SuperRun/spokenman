package com.zust.itee.controller.datadictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.DataDictionaryDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.enums.datadictionry.RootDataDictionary;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.datadictionary.DataDictionaryManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 数据字典 controller
 *
 * @author pojun
 */
@RestController("ota-dataDictionary")
@RequestMapping("/data-dictionary")
@Slf4j
@Api(tags = "数据字典相关接口")
public class DataDictionaryController {

    @Resource
    private DataDictionaryManager dataDictionaryManager;

    @GetMapping("/org-types")
    @ApiOperation("获取所有组织条线")
    public LayuiResponse<DataDictionaryDto> listOrgTypes() {
        log.info("==获取所有组织条线==");
        List<DataDictionaryDto> res = dataDictionaryManager.listBySubDDByRoot(
                RootDataDictionary.ORG_TYPE);
        return LayuiResponse.success(res);
    }

    @PostMapping("/org")
    @ApiOperation("新增组织条线")
    @ApiImplicitParams({ @ApiImplicitParam(name = "valueStr", value = "条线名", required = true,
            paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "备注", paramType = "query") })
    public JsonResponse<Integer> saveOrgType(@ApiIgnore DataDictionaryDto dataDictionaryDto) {
        log.info("==新增组织条线== data:{}", dataDictionaryDto);
        try {
            return JsonResponse.success(dataDictionaryManager.saveOrgType(dataDictionaryDto));
        } catch (BusinessException e) {
            log.warn("==新增组织条线异常== data:{},e:{}", dataDictionaryDto, e);
            return JsonResponse.error(e.getMessage(), e.getCode());
        } catch (Exception e) {
            log.error("==新增组织条线出错== data:{},e:{}", dataDictionaryDto, e);
            return JsonResponse.error();
        }
    }

    @GetMapping("/{id}/subs")
    public LayuiResponse<DataDictionaryDto> listSubs(@PathVariable("id") Long id) {
        log.info("==根据父节点id获取子节点==\n id:{}", id);
        List<DataDictionaryDto> subs = dataDictionaryManager.listSubDDByRootId(id);
        return LayuiResponse.success(subs);
    }
}
