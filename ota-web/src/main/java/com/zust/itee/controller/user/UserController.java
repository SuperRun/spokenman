package com.zust.itee.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zust.itee.dto.UserDto;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.dto.base.LayuiResponse;
import com.zust.itee.dto.base.PageBaseDto;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.dto.request.user.UserSearchDto;
import com.zust.itee.dto.request.user.UserUpdateDto;
import com.zust.itee.enums.EnumsType;
import com.zust.itee.enums.user.UserStatusType;
import com.zust.itee.enums.user.UserType;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.dto.request.user.OrgUserSaveDto;
import com.zust.itee.dto.request.user.PersonalUserSaveDto;

import com.zust.itee.manager.user.UserManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Resource
    private UserManager userManager;

    @ApiOperation("用户登录")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名/手机号", required = true,
            paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true,
                    paramType = "query") })
    @PostMapping("/login")
    public JsonResponse<String> userLogin(HttpSession session, @ApiIgnore UserDto userDto) {
        log.info("==用户登录== user:{}", userDto);
        try {
            userManager.userLogin(userDto, session);
            return JsonResponse.success("user login ok");
        } catch (BusinessException e) {
            log.warn("==用户登录异常== user:{},e:{}", userDto, e);
            return JsonResponse.error(e.getMessage(), e.getCode());
        } catch (Exception e) {
            log.error("==用户登录出错== user:{},e:{}", userDto, e);
            return JsonResponse.error();
        }
    }

    @ApiOperation("用户注销")
    @PostMapping("/logout")
    public JsonResponse<String> userLogout(HttpSession session) {
        session.invalidate();
        return JsonResponse.success("user logout ok");
    }

    @ApiOperation("获取登录 session 信息-测试用")
    @GetMapping("/login")
    public JsonResponse<SessionInfo> loginInfo(@ApiIgnore SessionInfo sessionInfo) {
        return JsonResponse.success(sessionInfo);
    }

    @ApiOperation("平台用户-用户管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型,1-个人用户,2-单位用户,3-讲师",
                    allowableValues = "1,2,3",
                    required = true, paramType = "path"),
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int")
    })
    @GetMapping("/{type}/list")
    public LayuiResponse<UserDto> listUser(@PathVariable("type") Short type,
            @ApiIgnore PageBaseDto pageBaseDto, UserSearchDto searchDto) {
        log.info("==搜索用户==  type:{},  user:{}, page:{}", type, searchDto, pageBaseDto);
        pageBaseDto.ensureNotNull();
        // 获取用户信息及分页信息
        pageBaseDto = userManager.listUserBySearch(type, searchDto, pageBaseDto);
        //noinspection unchecked
        return LayuiResponse.success(pageBaseDto);
    }

    @ApiOperation("获取用户详情")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path")
    @GetMapping("/{id}")
    public JsonResponse<UserDto> getUser(@PathVariable("id") Integer id) {
        log.info("==获取用户详情== id:{}", id);
        return JsonResponse.success(userManager.getById(id));
    }

    @ApiOperation("更新用户信息。单位用户-组织信息联动更新。单位中个人用户-组织信息不允许更新。个人用户-组织资料信息更新")
    @ApiImplicitParams(
            { @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path") })
    @PutMapping("/{id}")
    public JsonResponse<String> updateUser(@PathVariable("id") Integer id,
            UserUpdateDto updateDto) {
        log.info("==更新用户信息== id:{},user:{}", id, updateDto);
        userManager.updateUser(id, updateDto);
        return JsonResponse.success("update user ok");
    }

    @ApiOperation("更新用户状态")
    @ApiImplicitParams(
            { @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path")
                    , @ApiImplicitParam(name = "status",
                    value = "用户状态,-1-删除,1-正常,0-审核拒绝,2-申请中,-2-账号冻结",
                    allowableValues = "-1,1,0,2,-2", dataType = "int") })
    @PutMapping("/{id}/status")
    public JsonResponse<Integer> updateUserStatus(@PathVariable("id") Integer id, Short status) {
        log.info("==更新用户状态== id:{},status:{}", id, status);
        try {
            return JsonResponse.success(userManager.updateUserStatus(id, status));
        } catch (BusinessException e) {
            log.warn("==更新用户状态异常== id:{},status:{}", id, status);
            return JsonResponse.error(e.getMessage(), e.getCode());
        } catch (Exception e) {
            log.error("==更新用户状态出错== id:{},status:{}", id, status);
            return JsonResponse.error();
        }
    }

    @ApiOperation("平台管理员-新增个人用户/讲师用户，初始密码‘123456’")
    @PostMapping("/personal")
    public JsonResponse<String> savePersonalUser(@ApiIgnore SessionInfo sessionInfo,
            PersonalUserSaveDto personalUserSaveDto) {
        log.info("==平台管理员新增个人/讲师用户== session:{},user:{}", personalUserSaveDto);
        userManager.savePersonalUser(sessionInfo.getOtaUserId(), personalUserSaveDto);
        return JsonResponse.success("save personal user ok");
    }

    @ApiOperation("平台管理员-新增单位用户")
    @PostMapping("/org")
    public JsonResponse<String> saveOrgUser(@ApiIgnore SessionInfo sessionInfo,
            OrgUserSaveDto orgUserSaveDto) {
        log.info("==平台用户新增单位用户== session:{},user:{}", sessionInfo, orgUserSaveDto);
        userManager.saveOrgUser(sessionInfo.getOtaUserId(), orgUserSaveDto);
        return JsonResponse.success("save org user ok");
    }

    @ApiOperation("单位用户-获取单位中个人用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名，模糊搜索",
                    paramType = "query"),
            @ApiImplicitParam(name = "status", value = "用户状态。-1:删除，1：正常，0：审核拒绝，2：申请中，-2：账号冻结",
                    allowableValues = "-1,1,0,2,-2", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页码", defaultValue = "1",
                    paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每行页数", defaultValue = "10",
                    paramType = "query", dataType = "int") })
    @GetMapping("/org")
    public LayuiResponse<UserDto> listSubUsers(@ApiIgnore SessionInfo sessionInfo,
            @ApiIgnore UserDto userDto, @ApiIgnore PageBaseDto pageBaseDto) {
        log.info("==单位用户获取单位中个人用户== session:{},user:{},page:{}", sessionInfo, userDto, pageBaseDto);
        pageBaseDto.ensureNotNull();
        return LayuiResponse.success(
                userManager.listSubUsers(sessionInfo.getOrgId(), userDto, pageBaseDto));
    }

    @ApiOperation("单位管理员/平台-新增个人用户（关联单位）,新增用户初始密码为'123456'")
    @PostMapping("/org/{orgId}")
    public JsonResponse<String> saveOrgUser(@PathVariable Integer orgId,
            @ApiIgnore SessionInfo sessionInfo,
            PersonalUserSaveDto personalUserSaveDto) {
        log.info("==单位管理员新增单位中个人用户== session:{},orgId:{},user:{}", sessionInfo, orgId,
                personalUserSaveDto);
        userManager.saveOrgPersonalUser(sessionInfo.getOtaUserId(), orgId, personalUserSaveDto);
        return JsonResponse.success("save org personal user ok");
    }

    @GetMapping("/status")
    @ApiOperation("获取所有用户状态")
    public LayuiResponse<EnumsType> getAllUserStatus() {
        List<EnumsType> res = UserStatusType.getEnumsTypes();
        return LayuiResponse.success(res);
    }

    @GetMapping("/type")
    @ApiOperation("获取所有用户类型")
    public LayuiResponse<EnumsType> getAllUserTypes() {
        List<EnumsType> res = UserType.getEnumsTypes();
        return LayuiResponse.success(res);
    }
}
