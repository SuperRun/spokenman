package com.zust.itee.exam.controller.hr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.MemberCreateDto;
import com.zust.itee.exam.dto.MemberDto;
import com.zust.itee.exam.dto.MemberModifyDto;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;
import com.zust.itee.exam.enums.LoginStateEnum;
import com.zust.itee.exam.service.AnnouncementService;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;

@Controller
@RequestMapping("/member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 获取员工的登录页面
     *
     * @author dxb
     * @why 返回员工的登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "member/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 员工登录验证
     *
     * @author dxb
     * @why 员工登录验证的ajax请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<LoginStateEnum> doLoginAjax(
            @RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session,
            HttpServletRequest request) {

        // 验证账号密码
        LoginStateEnum loginStateEnum = memberService.verifyAccount(username,
                password, session, request);

        // 登录成功
        if (loginStateEnum.getStatus() > 0) {
            return new JsonResult<>(true, loginStateEnum);
        }

        // 登录失败
        return new JsonResult<>(false, loginStateEnum.getInfo());
    }

    /**
     * 根据登录名获取用户的名字
     *
     * @author dxb
     * @why 在创建一个机构的时候 需要填写负责人的登录名，此处需要返回该用户的姓名以表确认
     */
    @RequestMapping(value = "/loginname/{loginName}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<MemberDto> getMemberNameByLoginName(
            @PathVariable String loginName) {
        MemberDto memberDto = memberService.getModel(memberService.getIdByLoginName(loginName));

        if (memberDto != null) {
            // 只返回姓名和id 避免数据暴露
            return new JsonResult<MemberDto>(true, new MemberDto(memberDto.getId(),
                    memberDto.getName()));
        }
        return new JsonResult<MemberDto>(false, "用户不存在");
    }

    /**
     * 获取添加员工页面
     *
     * @author dxb
     * @why 一个部门需要能添加员工
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreatePage() {
        return "2/member/creation";
    }

    /**
     * 添加一个员工
     *
     * @param sessionInfo 读取创建人信息
     * @return 创建完之后重定向到员工详情页
     * @author dxb
     * @why 添加一个员工
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> savePerson(MemberCreateDto memberCreateDto,
            SessionInfo sessionInfo) {
        Integer id = null;
        String error = null;
        try {
            id = memberService.createDto(memberCreateDto, sessionInfo.getUserId(),
                    sessionInfo.getOrganizationId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(id != null, id, error);
        //        return "redirect:/member/" + id;
    }

    /**
     * 修改员工资料
     *
     * @param memberId 被修改的员工id
     * @return 修改完之后重定向到员工详情页
     * @author dxb
     * @why 修改员工资料
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> moveMember(MemberModifyDto memberModifyDto,
            @PathVariable Integer memberId

    ) {

        boolean success = false;
        String error = "";

        try {
            success = memberService.modify(memberId, memberModifyDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(success, success ? memberId : null, !success ? error : null);
    }

    /**
     * 删除员工
     *
     * @author dxb
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<String> deleteMember(@PathVariable Integer memberId) {
        try {
            if (memberService.deleteMember(memberId)) {
                return new JsonResult<>(true, null, null);
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return new JsonResult<>(false, null, e.getMessage());
        }
        return new JsonResult<>(false, null, "未知错误");
    }

    /**
     * 员工离职
     *
     * @param memberId 被离职员工
     * @return 操作结果
     * @author dxb
     * @why
     */
    @RequestMapping(value = "/{memberId}/dismiss", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<String> dissmissMember(@PathVariable Integer memberId) {
        try {
            if (memberService.moveMember(null, null, memberId)) {
                return new JsonResult<>(true, null, null);
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return new JsonResult<>(false, null, e.getMessage());
        }
        return new JsonResult<>(false, null, "未知错误");
    }

    /**
     * 从删除中恢复员工
     *
     * @author dxb
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult<String> undoDeleteMember(@PathVariable Integer memberId) {
        try {
            if (memberService.undoDeleteMember(memberId)) {
                return new JsonResult<>(true, null, null);
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return new JsonResult<>(false, null, e.getMessage());
        }
        return new JsonResult<>(false, null, "未知错误");
    }

    /**
     * 查询一个员工是否存在
     *
     * @author dxb
     * @why 创建org的时候需要查询
     */
    @RequestMapping(value = "/loginname/exsit", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkExsitByLoginName(
            @RequestParam(value = "directorId", required = true) String loginName,
            @RequestParam(value = "fanzhuan", required = false) boolean fanzhuan) {

        Integer id = memberService.getIdByLoginName(loginName);
        return fanzhuan ? id == null : id != null;
    }

    /**
     * @return 更改密码的页面
     * @author dxb
     * @why 员工需要修改密码
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String getChangePasswordPage() {
        return "2/member/changePassword";
    }

    /**
     * @author dxb
     * @why 员工修改密码
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> changePassword(
            @RequestParam(value = "oldPwd", required = true) String oldPassword,
            @RequestParam(value = "newPwd", required = true) String newpw1,
            SessionInfo sessionInfo, Model model
    ) {

        String msg = "修改失败";
        boolean success = false;
        try {
            success = memberService.setPwd(sessionInfo.getUserId(), oldPassword, newpw1);
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            msg += (" " + e.getMessage());
        }

        return new JsonResult<>(success, success ? sessionInfo.getUserId() : null,
                !success ? msg : null);
    }

    /**
     * @author dxb
     * @why 旧密码表单验证
     */
    @RequestMapping(value = "/verifypwd", method = RequestMethod.POST)
    @ResponseBody
    public boolean verifyPassword(@RequestParam String oldPassword, SessionInfo sessionInfo) {
        return memberService.verifyPwd(sessionInfo.getUserId(), oldPassword);
    }

    /**
     * 查看自己的资料
     *
     * @author dxb
     * @why 快速查看自己的资料
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String getSelfDetailPage(SessionInfo sessionInfo) {
        return "forward:" + sessionInfo.getUserId();
    }

    /**
     * 查看别人的资料卡
     *
     * @author dxb
     * @why 主要为了给别人查看一下联系方式
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public String getMemberDetailPage(@PathVariable Integer memberId, Model model) {

        MemberDto memberDto = memberService.getModel(memberId);
        if (memberDto == null) {
            return "redirect:/404";
        } else {

            model.addAttribute("member", memberDto);

            if (memberDto.getOrganizationId() != null) {
                model.addAttribute("org",
                        organizationService.getModel(memberDto.getOrganizationId()));
            }

            //            return "member/detail";
            return "2/member/detail";
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> modifyPage(SessionInfo sessionInfo,
            MemberModifyDto memberModifyDto) {

        boolean success = false;
        String error = "";

        try {
            success = memberService.modify(sessionInfo.getUserId(), memberModifyDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(success, success ? sessionInfo.getUserId() : null,
                !success ? error : null);
    }

    //
    //    /**
    //     * 获取修改员工的页面
    //     *
    //     * @param memberId 被修改人的id
    //     * @param model    模型
    //     * @return 修改员工的页面
    //     * @author dxb
    //     * @why
    //     */
    //    @RequestMapping(value = "/{memberId}/modify", method = RequestMethod.GET)
    //    public String getMemberModifyPage(@PathVariable Integer memberId, Model model) {
    //        MemberDto memberDto = memberService.getModel(memberId);
    //        if (memberDto == null) {
    //            return "redirect:/404";
    //
    //        } else {
    //
    //            model.addAttribute("member", memberDto);
    //
    //            if (memberDto.getOrganizationId() != null) {
    //                model.addAttribute("org", organizationService.getModel(memberDto
    // .getOrganizationId()));
    //                List<DepartmentDto> departmentDtosInOrganization = departmentService
    // .getDepartmentDtosInOrganization(memberDto.getOrganizationId());
    //                model.addAttribute("depList", departmentDtosInOrganization);
    //                logger.debug("depList.size() = " + departmentDtosInOrganization.size());
    //            }
    //
    //            if (memberDto.getDepartmentId() != null) {
    //                DepartmentDto departmentDto = departmentService.getModel(memberDto
    // .getDepartmentId());
    //                model.addAttribute("dep", departmentDto);
    //                model.addAttribute("org", organizationService.getModel(departmentDto
    // .getOrganizationId()));
    //            }
    //
    //            return "member/modify";
    //        }
    //
    //    }

    /**
     * 获取员工的首页
     *
     * @param sessionInfo 员工信息
     * @return 员工的首页viewName
     * @author dxb
     * @why
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView pageIndex(SessionInfo sessionInfo) {
        ModelAndView mv = new ModelAndView("2/member/home");
        List<AnnouncementDto> annpla = announcementService.
                getAnnouncementByType(AnnouncementTypeEnum.PLATFORMNOTICE.getType());
        List<AnnouncementDto> annsafe = announcementService.
                getAnnouncementByType(AnnouncementTypeEnum.SAFENOTICE.getType());
        mv.addObject("ann1", annpla);
        mv.addObject("ann2", annsafe);
        return mv;
    }

    @RequestMapping(value = "/{mId}/rmorg", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Integer> removeMemberFromOrg(@PathVariable Integer mId) {
        boolean b = false;
        String error = null;
        try {
            b = memberService.removeMemberFromOrg(mId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(b, mId, error);
    }
}
