package com.zust.itee.exam.controller.hr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.LinkPersonCreateDto;
import com.zust.itee.exam.dto.MemberListDto;
import com.zust.itee.exam.dto.OrgBasicInfoCreateDto;
import com.zust.itee.exam.dto.OrganizationCreateDto;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.OrganizationExcelDto;
import com.zust.itee.exam.dto.OrganizationImportListDto;
import com.zust.itee.exam.dto.OrganizationListItemDto;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.util.UploadPathUtil;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MemberService memberService;

    //
    //    /**
    //     * 返回创建机构页面
    //     *
    //     * @return
    //     * @author dxb
    //     * @why 返回创建机构页面
    //     */
    //    @RequestMapping(value = "/new", method = RequestMethod.GET)
    //    public String pageCreation() {
    //        return "2/hr/organization/creation";
    //    }

    //
    //    /**
    //     * 创建子机构
    //     *
    //     * @param sessionInfo
    //     * @param model
    //     * @return
    //     */
    //    @RequestMapping(value = "/newchild", method = RequestMethod.GET)
    //    public String getCreationPage(SessionInfo sessionInfo, Model model) {
    //        OrganizationDto organizationDto = organizationService.getModel(sessionInfo
    // .getOrganizationId());
    //        model.addAttribute("org", organizationDto);
    //        model.addAttribute("childOrgType", OrganizationTypeEnum.stateOf(organizationDto
    // .getType().getType() + 1));
    ////        return "hr/organization/create_child";
    //        return "2/hr/organization/new";
    //    }

    /**
     * @param parentId 机构A id
     * @return 创建机构A的子机构页面
     */
    @RequestMapping(value = "/newchild/{parentId}", method = RequestMethod.GET)
    public String getCreationPage(@PathVariable Integer parentId, Model model) {
        OrganizationDto organizationDto = organizationService.getModel(parentId);
        model.addAttribute("org", organizationDto);
        model.addAttribute("childOrgType",
                OrganizationTypeEnum.stateOf(organizationDto.getType().getType() + 1));
        //        return "hr/organization/create_child";
        return "2/hr/organization/new2";
    }

    //    /**
    //     * 获取父级所有的机构
    //     *
    //     * @param typeCodeStr
    //     * @return
    //     * @author dxb
    //     * @why 创建机构的时候，需要选择父级机构是什么
    //     */
    //    @RequestMapping(value = "/type/{typeCode}/parent", method = RequestMethod.GET)
    //    @ResponseBody
    //    public JsonResult<List<OrganizationDto>> getParentOrganizationList(
    //            @PathVariable("typeCode") String typeCodeStr) {
    //        try {
    //            // 类型转换
    //            short typeCode = Short.parseShort(typeCodeStr);
    //            // 找父级机构
    //            List<OrganizationDto> parentOrganizationDtosByType = organizationService
    // .findParentOrganizationDtosByType(typeCode);
    //            // 返回结果
    //            return new JsonResult<>(true, parentOrganizationDtosByType);
    //        } catch (NumberFormatException e2) {
    //            return new JsonResult<>(false, "类型编码有错，无法获取列表");
    //        } catch (Exception e) {
    //            return new JsonResult<>(false, "内部错误");
    //        }
    //    }

    //    /**
    //     * 获取和传入机构有所属同一个机构的朋友机构集合
    //     *
    //     * @param orgId 需要查找的机构
    //     * @return 传入机构有所属同一个机构的朋友机构集合
    //     * @author dxb
    //     * @why 在修改一个机构的时候，需要显示这个机构所在的树状位置<br>
    //     */
    //    @RequestMapping(value = "/{orgId}/friends", method = RequestMethod.GET)
    //    @ResponseBody
    //    public JsonResult<List<OrganizationDto>> getFriendsOrganizationListByOrgId
    // (@PathVariable Integer orgId) {
    //        List<OrganizationDto> list = organizationService.getFriendsOrganizationDtoList(orgId);
    //        return new JsonResult<>(true, list);
    //    }

    //    /**
    //     * 获取和传入机构有所属机构同一个机构的朋友机构集合
    //     *
    //     * @param orgId 需要查找的机构
    //     * @return 传入机构有所属机构同一个机构的朋友机构集合
    //     * @author dxb
    //     * @why 在修改一个机构的时候，需要显示这个机构所在的树状位置<br>
    //     */
    //    @RequestMapping(value = "/{orgId}/parent/friends", method = RequestMethod.GET)
    //    @ResponseBody
    //    public Map<String, Object> getFriendsOfParentOrganizationListByOrgId(@PathVariable
    // Integer orgId) {
    //        List<OrganizationDto> list = organizationService
    // .getFriendsOfParentOrganizationDtoListByOrgId(orgId);
    //
    //        Map<String, Object> map = new HashMap<>();
    //        map.put("success", true);
    //        map.put("data", list);
    //        map.put("parentId", organizationService.getParentOrgId(orgId));
    //
    //        return map;
    //    }

    //    /**
    //     * 根据类型找出所有机构
    //     *
    //     * @param typeCodeStr
    //     * @return
    //     * @author dxb
    //     * @why 在创建员工的时候 选择员工所属的机构类型之后需要列出可以选择的机构
    //     */
    //    @RequestMapping(value = "/type/{typeCode}", method = RequestMethod.GET)
    //    @ResponseBody
    //    public JsonResult<List<OrganizationDto>> getOrganizationList(
    //            @PathVariable("typeCode") String typeCodeStr) {
    //        try {
    //            // 类型转换
    //            short typeCode = Short.parseShort(typeCodeStr);
    //            // 找机构
    //            List<OrganizationDto> parentOrganizationDtosByType = organizationService
    //                    .findParentOrganizationDtosByType((short) (typeCode + 1));
    //            // 返回结果
    //            return new JsonResult<>(true, parentOrganizationDtosByType);
    //        } catch (NumberFormatException e2) {
    //            return new JsonResult<>(false, "类型编码有错，无法获取列表");
    //        } catch (Exception e) {
    //            return new JsonResult<>(false, "内部错误");
    //        }
    //    }

    //    /**
    //     * 获取一个机构的所有部门
    //     *
    //     * @param orgIdStr
    //     * @return
    //     * @author dxb
    //     * @why 创建一个成员的时候 需要获取这个机构的的所有部门
    //     */
    //    @RequestMapping(value = "/{orgId}/department", method = RequestMethod.GET)
    //    @ResponseBody
    //    public JsonResult<List<DepartmentListItemDto>> getDepsInOrg(
    //            @PathVariable(value = "orgId") String orgIdStr) {
    //        logger.info("orgIdStr = " + orgIdStr);
    //        List<DepartmentListItemDto> list;
    //        try {
    //            Integer orgId = Integer.parseInt(orgIdStr);
    //            list = departmentService.getDepartmentListItemDtos(orgId);
    //            return new JsonResult<>(true, list);
    //        } catch (Exception e) {
    //            list = new ArrayList<>();
    //            return new JsonResult<>(true, list);
    //        }
    //    }

    /**
     * 获取一个机构的详情
     *
     * @param orgId 机构id
     * @author dxb
     * @why 机构需要查看机构的详情
     */
    @RequestMapping(value = "/{orgId}", method = RequestMethod.GET)
    public String getOrg(@PathVariable Integer orgId, Model model, SessionInfo sessionInfo) {

        model.addAttribute("orgId", orgId);

        return "2/hr/organization/detail";
    }

    /**
     * 获取机构list页面
     *
     * @author dxb
     * @why 管理员需要树状查看机构信息
     */
    @RequestMapping(value = "/list/tree", method = RequestMethod.GET)
    public String getOrgListPage(SessionInfo sessionInfo, Model model) {
        //不包含车企
        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithoutCompany(
                sessionInfo.getOrganizationId());
        model.addAttribute("ztree", JSON.toJSONString(organizationTree));
        model.addAttribute("title", "下属机构列表");

        return "2/hr/organization/list";
        //        return "hr/organization/list";
    }

    @RequestMapping(value = "/list/treec", method = RequestMethod.GET)
    public String getCompanyListPage(SessionInfo sessionInfo, Model model) {
        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithoutCompany(
                sessionInfo.getOrganizationId());
        model.addAttribute("ztree", JSON.toJSONString(organizationTree));
        model.addAttribute("title", "车企列表");

        return "2/hr/organization/companyList";
        //        return "hr/organization/list";
    }

    /**
     * 获取机构列表
     *
     * @param orgIdstr 根机构id
     * @author dxb
     * @why 打开页面后 自动获取自己机构的子机构
     */
    @RequestMapping(value = "/{orgId}/childs", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<OrganizationDto>> getOrgList(
            @PathVariable(value = "orgId") String orgIdstr) {

        logger.info("orgIdstr = " + orgIdstr);
        List<OrganizationDto> newList;

        try {
            Integer orgId = Integer.parseInt(orgIdstr);
            // 根据机构id获取自己机构
            newList = organizationService.findOrgDtosByParentId(orgId);
            return new JsonResult<>(true, newList);
        } catch (Exception e) {
            newList = new ArrayList<>();
            return new JsonResult<>(true, newList);
        }
    }

    /**
     * 禁用一个机构
     *
     * @author dxb
     * @why 删除无法挽回，组织只能禁用
     */
    @RequestMapping(value = "/{orgId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<String> disableOrg(@PathVariable Integer orgId, SessionInfo sessionInfo) {
        String error = "失败";
        if (sessionInfo.getOrganizationId().equals(orgId)) {
            error += " 不能删除自己所在的机构";
        } else if (organizationService.disable(orgId)) {
            return new JsonResult<String>(true, "成功", null);
        }
        return new JsonResult<String>(false, null, error);
    }
    //
    //    /**
    //     * 待审核的机构列表（只显示登录员工所在机构的递归子级机构）
    //     *
    //     * @param sessionInfo
    //     * @param model
    //     * @param cq          1 是车企
    //     * @param p           1 是 省机构
    //     * @param c           1 是 市机构
    //     * @param d           1 是 县区机构
    //     * @return
    //     * @author dxb
    //     * @why 车企刚申请的时候状态是待审核，需要上级机构审核
    //     */
    //    @RequestMapping(value = "/unverify", method = RequestMethod.GET)
    //    public String getUnverifyOrgList(SessionInfo sessionInfo, Model model,
    //                                     @RequestParam(required = false) Integer cq,
    //                                     @RequestParam(required = false) Integer p,
    //                                     @RequestParam(required = false) Integer c,
    //                                     @RequestParam(required = false) Integer d
    //
    //    ) {
    ////        List<OrganizationDto> list = organizationService.getUnverifyDtoList(
    ////                sessionInfo.getOrganizationId(), cq, p, c, d);
    ////        logger.info("parent org id " + sessionInfo.getOrganizationId());
    ////        logger.info("unverifylist size " + list.size());
    ////        model.addAttribute("list", list);
    ////        return "hr/organization/unverifyList";
    //        // TODO: 2016/9/19 测试待审核列表会不会出错
    //
    //        List<OrganizationUnverifyTree> organizationUnverifyTree = organizationService
    // .getOrganizationUnverifyTree(sessionInfo.getOrganizationId());
    //        model.addAttribute("ztree", JSON.toJSONString(organizationUnverifyTree));
    //        model.addAttribute("title", "待审核列表");
    //
    //        //让前端用来判断是否要审核
    //        model.addAttribute("unverify", true);
    //        return "2/hr/organization/list";
    //    }

    //    /**
    //     * 将org的状态设为可用
    //     *
    //     * @param orgId
    //     * @return
    //     * @author dxb
    //     * @why 审核org通过、禁用后启用 需要用到
    //     */
    //    @RequestMapping(value = "/{orgId}", method = RequestMethod.PUT)
    //    @ResponseBody
    //    public JsonResult<String> setOrgEnable(@PathVariable Integer orgId) {
    //        return organizationService.enable(orgId) ? new JsonResult<String>(true,
    //                "成功", null) : new JsonResult<String>(false, null, "失败");
    //    }

    @RequestMapping(value = "/members")
    public String memberListInOrganization(SessionInfo sessionInfo, Model model) {
        //        logger.info("参数 pageBaseDto= " + pageBaseDto);
        //
        //        model.addAttribute("searchKey", pageBaseDto.getSearchKey());
        //
        //        List<MemberDto> memberDtoList = (memberService.findMemberDtosInOrganization
        // (orgId, pageBaseDto));
        //        model.addAttribute("members", memberDtoList);
        //        OrganizationDto organizationDto = organizationService.getModel(orgId);
        //        model.addAttribute("org", organizationDto);
        //        model.addAttribute("sum", memberService.countTmembersInOrganization(orgId));
        //        model.addAttribute("p", memberService.updateFindMembersInOrganization(orgId,
        // pageBaseDto));

        Integer managerId = memberService.getManagerIdByOrgId(sessionInfo.getOrganizationId());
        model.addAttribute("managerId", managerId);
        return "2/member/list";
    }

    @RequestMapping(value = "/{orgId}/members", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<MemberListDto>> getmemberListDtos(@PathVariable Integer orgId) {

        List<MemberListDto> memberListDtos = memberService.findMemberListDtos(orgId);
        return new JsonResult<>(true, memberListDtos);
    }

    //    /**
    //     * 审核失败
    //     *
    //     * @param reason
    //     * @return
    //     * @author dxb
    //     * @why org 可以审核失败，失败时需要填写原因
    //     */
    //    @RequestMapping(value = "/{orgId}/verifyfail", method = RequestMethod.POST)
    //    @ResponseBody
    //    public JsonResult verifyFail(@RequestParam String reason,
    //                                 @PathVariable Integer orgId) {
    //        boolean b = organizationService.verifyFail(orgId, reason);
    //        return new JsonResult<>(b, null);
    ////        return organizationService.verifyFail(orgId, reason) ? "redirect:/organization/"
    // + orgId : "redirect:404";
    //    }

    //    /**
    //     * 查看我主管的机构
    //     *
    //     * @param sessionInfo
    //     * @param model
    //     * @return
    //     * @author dxb
    //     * @why
    //     */
    //    @RequestMapping("/mine")
    //    public String myOrg(SessionInfo sessionInfo, Model model) {
    //        model.addAttribute("list",
    //                organizationService.findOrgDtoByLinkedPerson(sessionInfo.getUserId()));
    //        return "/hr/organization/mine";
    //    }

    //    /**
    //     * 给部门设置主管
    //     * -dxb
    //     *
    //     * @param orgId
    //     * @param loginName
    //     * @return
    //     */
    //    @RequestMapping(value = "{orgId}/lp", method = RequestMethod.POST)
    //    @ResponseBody
    //    public JsonResult<Integer> setOrgLinkedPerson(@RequestParam String loginName,
    // @PathVariable Integer orgId) {
    //        try {
    //
    //            //更新主管
    //            organizationService.setLinkedPerson(orgId, loginName);
    //
    //            //设置主管的就职信息
    //            memberService.moveMember(orgId, null, memberService.getIdByLoginName(loginName));
    //            return new JsonResult<>(true, orgId);
    //
    //        } catch (RuntimeException e) {
    //            return new JsonResult<>(false, "失败 " + e.getMessage());
    //        }
    //    }

    //    /**
    //     * 获取结构树
    //     * -dxb
    //     *
    //     * @param sessionInfo 获取树根的主键
    //     * @return 由自己所在的机构下属机构
    //     */
    //    @RequestMapping(value = "tree", method = RequestMethod.GET)
    //    @ResponseBody
    //    public JsonResult<OrganizationTree> getOrganizationTree(SessionInfo sessionInfo) {
    //        OrganizationTree organizationTree = organizationService
    // .getOrganizationTreeWithCompany(sessionInfo.getOrganizationId());
    //        return new JsonResult<>(true, organizationTree);
    //    }

    @RequestMapping(value = "{orgId}/org", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<OrganizationDto> getOrgInfo(@PathVariable Integer orgId) {
        OrganizationDto a = organizationService.getModel(orgId);
        return new JsonResult<>(a != null, a);
    }

    /**
     * 创建一个子机构
     * 创建只能创建子机构，父级机构直接从创建人的登录信息中读取
     * -dxb
     *
     * @param organizationCreateDto 机构信息
     * @param orgBasicInfoCreateDto 车企必要资料
     * @return 成功返回创建后的主键，失败则返回错误信息
     */

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Integer> createNewOrg(
            OrganizationCreateDto organizationCreateDto,
            OrgBasicInfoCreateDto orgBasicInfoCreateDto,
            LinkPersonCreateDto linkPersonCreateDto

    ) {

        String error = "错误 - 未知";
        Integer orgId = null;
        try {
            //默认密码 123456
            if (linkPersonCreateDto.getPassword() == null) {
                linkPersonCreateDto.setPassword("123456");
            }
            orgId = organizationService.create(linkPersonCreateDto, organizationCreateDto,
                    orgBasicInfoCreateDto);
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(orgId != null, orgId, error);
    }

    @RequestMapping(value = "/import/company", method = RequestMethod.GET)
    public String pageCompanyImport() {
        /*
        导入车企要求

        长度要求
        手机号不超过20


        不能为空项
        1.企业名称
        2.市
        3.所属县区
        4.车队长姓名
        5.车队长手机号（作为管理账号使用，只写一个手机号） 不能有两个车企的车队长是同一个人
        6.车队长身份证


         */
        return "2/hr/organization/import_company";
    }

    @RequestMapping(value = "/import/company/ana", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<List<OrganizationExcelDto>> ana(
            @RequestParam(value = "path", required = true) String path, HttpServletRequest request,
            SessionInfo sessionInfo) {

        path = UploadPathUtil.getWebRoot(request) + "/" + path;

        List<OrganizationExcelDto> organizationExcelDtos = null;
        String error = null;
        try {
            organizationExcelDtos = organizationService.excelImportDataRead(path);
            List<Integer> companyIds = organizationService.excelImport(organizationExcelDtos);
            List<Integer> driverAccountIds = memberService.addMasterDriverAccountToMember(
                    organizationExcelDtos, sessionInfo.getUserId());

            logger.debug(JSON.toJSONString(driverAccountIds));
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        return new JsonResult<>(organizationExcelDtos != null, organizationExcelDtos, error);
    }

    @RequestMapping(value = "/import/company", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<List<Integer>> ana(@RequestBody OrganizationImportListDto list) {
        List<Integer> ids = organizationService.excelImport(list.getOrgs());
        return new JsonResult<>(true, ids);
    }

    @RequestMapping(value = "/{orgId}/companys", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<List<OrganizationListItemDto>> findCompany(@PathVariable Integer orgId) {

        List<OrganizationListItemDto> organizationListItemDtos = organizationService
                .findOrganizationListItemDtos(
                        orgId);
        return new JsonResult<>(true, organizationListItemDtos);
    }

    @RequestMapping(value = "treetest", method = RequestMethod.GET)
    public String pageTreetest(@RequestParam(value = "tid", required = false) Integer orgId,
            Model model, SessionInfo sessionInfo) {
        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        model.addAttribute("ztree", JSON.toJSONString(organizationTree));
        model.addAttribute("orgId", orgId);

        return "2/hr/organization/treetest";
    }

    @RequestMapping(value = "/getCompany", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getCompany(
            @RequestParam(name = "orgId", required = true) Integer orgId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            orgId = organizationService.findTheNearestCompany(orgId, orgId);
            jsonResult.setData(orgId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "获取车企失败");
        }
        return jsonResult;
    }

    /**
     * 更新散装办信息
     * <p>
     * <br>
     * 导入车企的时候是自动生成的无信息散装办，此处用excel更新散装办
     * <p>
     * <p>
     * 表格要求
     * 1.市必须输入 并且名称必须符合规范 如 杭州市
     * 2.区必须输入
     * 3.全称必须输入
     * 4.简称可以不输入，如果要输入 请确保字数不超20
     * 4.负责人姓名必须输入
     */
    @RequestMapping(value = "/updateinfo", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Integer> updateInfo() {

        boolean success = false;
        int c = 0;
        String error = null;

        try {
            c = organizationService.updateOrgInfo("C:/x.xlsx");
            success = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            error = e.getMessage();
        }

        return new JsonResult<>(success, c, error);
    }
}
