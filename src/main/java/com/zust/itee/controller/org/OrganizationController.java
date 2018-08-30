package com.zust.itee.controller.org;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.OrganizationDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.enums.EnumsType;
import com.zust.itee.enums.org.OrgLevelType;
import com.zust.itee.manager.org.OrganizationManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 组织 controller
 *
 * @author pojun
 */
@RestController("ota-organizationController")
@RequestMapping("/org")
@Slf4j
@Api(tags = "组织相关接口")
public class OrganizationController {

    @Resource
    private OrganizationManager organizationManager;

    @ApiOperation("筛选组织")
    @ApiImplicitParams({ @ApiImplicitParam(name = "areaId", value = "区域国标码", paramType = "query"),
            @ApiImplicitParam(name = "typeId", value = "条线id", paramType = "query",
                    dataType = "long"),
            @ApiImplicitParam(name = "level", value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级",
                    allowableValues = "1,2,3,4,5", paramType = "query", dataType = "int") })
    @GetMapping("")
    public LayuiResponse<OrganizationDto> listOrg(Long areaId, Long typeId, Short level) {
        log.info("==筛选组织== areaId:{},typeId:{}", areaId, typeId);
        List<OrganizationDto> res = organizationManager.listByAreaAndTypeAndLevel(areaId, typeId,
                level);
        return LayuiResponse.success(res);
    }

    @ApiOperation("平台用户-新增组织")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "组织全程", required = true,
            paramType = "query"),
            @ApiImplicitParam(name = "shortName", value = "简称", required = true,
                    paramType = "query"),
            @ApiImplicitParam(name = "areaId", value = "区域国标码", required = true,
                    paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "level", value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级",
                    allowableValues = "1,2,3,4,5", required = true, paramType = "query",
                    dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "条线id", required = true, paramType = "query",
                    dataType = "long"),
            @ApiImplicitParam(name = "phone", value = "组织电话", required = true, paramType = "query"),
            @ApiImplicitParam(name = "email", value = "组织邮箱", required = false,
                    paramType = "query"),
            @ApiImplicitParam(name = "parentId", value = "父组织id", required = false,
                    paramType = "query", dataType = "int")
    })
    @PostMapping("")
    public JsonResponse<String> saveOrg(@ApiIgnore SessionInfo sessionInfo,
            @ApiIgnore OrganizationDto organizationDto) {
        log.info("==新增组织== org:{}", organizationDto);
        organizationManager.saveOrg(sessionInfo.getOtaUserId(), organizationDto);
        return JsonResponse.success("org save ok");
    }

    @GetMapping("/level")
    @ApiOperation("获取所有组织等级")
    public LayuiResponse<EnumsType> getAllOrgLevel() {
        return LayuiResponse.success(OrgLevelType.getEnumsTypes());
    }
}
