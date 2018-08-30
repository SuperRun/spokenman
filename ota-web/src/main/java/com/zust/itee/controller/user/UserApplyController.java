package com.zust.itee.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.userapply.OrgUserApply;
import com.zust.itee.dto.request.userapply.PersonalApproveDto;
import com.zust.itee.dto.request.userapply.PersonalUserApplyDto;
import com.zust.itee.enums.EnumsType;
import com.zust.itee.enums.user.UserApplyStatusType;
import com.zust.itee.manager.user.UserApplyManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户申请 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/user-apply")
@Slf4j
@Api(tags = "用户申请相关接口")
public class UserApplyController {

    @Resource
    private UserApplyManager userApplyManager;

    @ApiOperation("个人用户注册")
    @PostMapping("/personal")
    public JsonResponse<String> personalUserApply(PersonalUserApplyDto personalUserApplyDto) {
        log.info("==发起个人用户注册== apply:{}", personalUserApplyDto);
        userApplyManager.personalUserApply(personalUserApplyDto);
        return JsonResponse.success("personal user apply ok");
    }

    @ApiOperation("个人用户资料申请-需登录个人用户")
    @PostMapping("/personal/approval")
    public JsonResponse<String> personalApprove(@ApiIgnore SessionInfo sessionInfo,
            PersonalApproveDto approveDto) {
        userApplyManager.personalApprove(sessionInfo.getOtaUserId(), approveDto);
        return JsonResponse.success("personal approval ok");
    }

    @ApiOperation("单位用户注册")
    @PostMapping("org")
    public JsonResponse<String> orgUserApply(OrgUserApply orgUserApply) {
        log.info("==发起单位用户注册== apply:{}", orgUserApply);
        userApplyManager.orgUserApply(orgUserApply);
        return JsonResponse.success("org user apply ok");
    }

    @ApiOperation("更新用户申请状态（用户管理中“审核通过”按钮调用此接口）")
    @ApiImplicitParams(
            { @ApiImplicitParam(name = "id", value = "申请id", required = true, paramType = "path"),
                    @ApiImplicitParam(name = "status", value = "申请状态,1-申请中,0-拒绝,2-通过",
                            allowableValues = "1,0,2", required = true, paramType = "query") })
    @PutMapping("/{id}/status")
    public JsonResponse<String> updateUserApplyStatus(@PathVariable("id") Integer id,
            Short status) {
        log.info("==更新用户申请状态== id:{},stauts:{}", id, status);
        userApplyManager.updateUserApplyStatus(id, status);
        return JsonResponse.success("ok");
    }

    @GetMapping("/status")
    @ApiOperation("获取所有用户申请状态")
    public LayuiResponse<EnumsType> getAllUserApplyStatus() {
        List<EnumsType> res = UserApplyStatusType.getEnumsTypes();
        return LayuiResponse.success(res);
    }
}
