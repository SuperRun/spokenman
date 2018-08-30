package com.zust.itee.exam.controller.hr;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.controller.UploadController;
import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.dto.DriverDto;
import com.zust.itee.exam.dto.DriverExcelDto;
import com.zust.itee.exam.dto.DriverIndex;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.enums.LoginStateEnum;
import com.zust.itee.exam.service.AnnouncementService;
import com.zust.itee.exam.service.DataDictionaryService;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;

@Controller
@RequestMapping("/driver")
public class DriverController {

    /**
     * 限制文件大小
     */
    private static int LIMITSIZE = 10 * 1024 * 1000;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private MemberService memberService;

    //================================驾驶员端=====================================================

    /**
     * 获取驾驶员登录的页面
     *
     * @author dxb
     * @why 返回驾驶员登录页面用
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "driver/login";
    }

    /**
     * 驾驶员登录验证
     *
     * @author dxb
     * @why 驾驶员登录验证的ajax请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<LoginStateEnum> doLoginAjax(
            SessionInfo sessionInfo,
            @RequestParam("username") String username,
            @RequestParam("password") String password, HttpSession session,
            HttpServletRequest request) {

        // 验证账号密码
        LoginStateEnum loginStateEnum = driverService.verifyAccount(username,
                password, session, request.getRemoteAddr());

        // 登录成功
        if (loginStateEnum.getStatus() > 0) {
            //sessionInfo.setUserId(driverService.getDriverByMpbile(username).getId());
            return new JsonResult<>(true, loginStateEnum);
        }

        // 登录失败
        return new JsonResult<>(false, loginStateEnum.getInfo());
    }

    /**
     * 驾驶员首页
     */
    @RequestMapping("/index")
    public ModelAndView getDriverIndexPage(SessionInfo sessionInfo) {
        ModelAndView mv = new ModelAndView("2/driver/index");
        Tdriver driver = driverService.getById(sessionInfo.getUserId());

        DriverIndex driverIndex = driverService.transToModel(driver);

        List<AnnouncementDto> anntrain = announcementService.
                getAnnouncementByType(AnnouncementTypeEnum.TRAINNOTICE.getType());
        List<AnnouncementDto> annexam = announcementService.
                getAnnouncementByType(AnnouncementTypeEnum.TRAINNOTICE.getType());

        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        mv.addObject("ztree", JSON.toJSONString(organizationTree));

        mv.addObject("driver", driverIndex);
        mv.addObject("anntrain", anntrain);
        mv.addObject("annexam", annexam);
        return mv;
    }

    /**
     * 驾驶员登出
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    //=================================管理端=====================================================

    /**
     * 添加驾驶员页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createDriver(
            HttpServletRequest request,
            @RequestParam(value = "orgId", required = false) Integer orgId,
            SessionInfo sessionInfo) {
        ModelAndView mv = new ModelAndView("2/hr/driver/creation");
        List<OrganizationDto> organizationDtos = driverService.getAllCarPrices();
        mv.addObject("orgs", organizationDtos);

        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        mv.addObject("ztree", JSON.toJSONString(organizationTree));
        mv.addObject("orgId", orgId);
        return mv;
    }

    /**
     * 添加驾驶员验证身份证是否已存在
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/check/sfz/{sfzNo}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkSfz(
            @PathVariable("sfzNo") String sfzNo) {
        return driverService.checkSfzExist(sfzNo);
    }

    /**
     * 添加驾驶员验证身份证是否已存在
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/check/mob/{mobile}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkMobile(
            @PathVariable("mobile") String mobile) {
        return driverService.checkMobileExist(mobile);
    }

    /**
     * Excel上傳
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/excel")
    public @ResponseBody
    Map<String, Object> getDriverExcel(HttpServletRequest request) {
        return UploadController.upload(request, "hr/driver/excel", LIMITSIZE);
    }

    /**
     * 添加驾驶员照片
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/pic")
    public @ResponseBody
    Map<String, Object> driverPhoto(HttpServletRequest request) {
        return UploadController.upload(request, "hr/driver/driver_photo", LIMITSIZE);
    }

    /**
     * 添加驾驶证照片
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/lincencepic")
    public @ResponseBody
    Map<String, Object> driverlincencepic(HttpServletRequest request) {
        return UploadController.upload(request, "hr/driver/drive_lincence_photo", LIMITSIZE);
    }

    /**
     * 添加安全证书照片
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/centificatepic")
    public @ResponseBody
    Map<String, Object> drivercentificate(HttpServletRequest request) {
        return UploadController.upload(request, "hr/driver/safe_centificate_photo", LIMITSIZE);
    }

    /**
     * 创建一个驾驶员
     *
     * @throws ParseException
     * @author ly
     * @why
     */
    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ResponseBody
    public boolean createDrivers(DriverDto driver,
            SessionInfo sessionInfo,
            @RequestParam(value = "org_id", required = false) Integer orgId,
            @RequestParam(value = "driveLicenceStartTimeStr", required = false)
                    String driveLicenceStartTimeStr,
            @RequestParam(value = "driveLicenceEndTimeStr", required = false)
                    String driveLicenceEndTimeStr,
            @RequestParam(value = "safeCentificateStartTimeStr", required = false)
                    String safeCentificateStartTimeStr,
            @RequestParam(value = "safeCentificateEndTimeStr", required = false)
                    String safeCentificateEndTimeStr
    ) throws ParseException {
        Torganization organization = organizationService.getById(orgId);
        String sfzNo = driver.getSfzNo();
        //设置初始密码 身份证后六位
        String pwd = sfzNo.substring(12, 18);
        driver.setPassword(pwd);
        //根据身份证获取驾驶员生日
        String birthStr = sfzNo.substring(6, 10)
                + "-" + sfzNo.substring(10, 12)
                + "-" + sfzNo.substring(12, 14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date birth = sdf.parse(birthStr);
        driver.setBirth(birth);
        //设置String -> Date
        if (driveLicenceStartTimeStr != null && driveLicenceStartTimeStr.length() > 0) {
            driver.setDriveLicenceStartTime(sdf.parse(driveLicenceStartTimeStr));
        } else {
            driver.setDriveLicenceStartTime(null);
        }
        if (driveLicenceEndTimeStr != null && driveLicenceEndTimeStr.length() > 0) {
            driver.setDriveLicenceEndTime(sdf.parse(driveLicenceEndTimeStr));
        } else {
            driver.setDriveLicenceEndTime(null);
        }
        if (safeCentificateStartTimeStr != null && safeCentificateStartTimeStr.length() > 0) {
            driver.setSafeCentificateStartTime(sdf.parse(safeCentificateStartTimeStr));
        } else {
            driver.setSafeCentificateStartTime(null);
        }
        if (safeCentificateEndTimeStr != null && safeCentificateEndTimeStr.length() > 0) {
            driver.setSafeCentificateEndTime(sdf.parse(safeCentificateEndTimeStr));
        } else {
            driver.setSafeCentificateEndTime(null);
        }

        driver.setStatus((short) 2);
        driver.setTorganization(organization);
        return driverService.createDriver(driver);
    }

    /**
     * 驾驶员展示列表
     *
     * @author ly
     * @why
     */
    //	@RequestMapping("")
    //	public ModelAndView index(HttpServletRequest request,
    //							  SessionInfo sessionInfo,
    //			@RequestParam(value = "orgId", required = false) Integer orgId,
    //			@RequestParam(value = "rows", required = false) Integer rows,
    //			@RequestParam(value = "page", required = false) Integer page,
    //			@RequestParam(value = "keywd", required = false) String keywd,
    //			@RequestParam(value = "sort", required = false) String sort){
    //		ModelAndView mv = new ModelAndView("2/hr/driver/index");
    //		if (orgId == null || orgId <= 0) orgId = sessionInfo.getOrganizationId();
    //		// 设置默认每页条数为20
    //		if (rows == null || rows == 0) 	rows = 20;
    //		// 设置默认为第一页
    //		if (page == null || page == 0) 	page = 1;
    //		List<DriverIndex> drivers = driverService.getDriverByStatus((short) 1, page, rows,
    // keywd, sort, orgId);
    //		mv.addObject("drivers", drivers);
    //		mv.addObject("keywdNow", keywd);
    //		mv.addObject("sortNow", sort);
    //		mv.addObject("rowsNow", rows);
    //		mv.addObject("pageNow", page);
    //		mv.addObject("pageNum", driverService.getPageNum((short)1, page, rows, keywd, sort,
    // orgId));
    //		mv.addObject("driverNum", driverService.getDriverNum((short) 1, keywd, orgId));
    //
    //		OrganizationTree organizationTree = organizationService
    // .getOrganizationTreeWithoutCompany(sessionInfo.getOrganizationId());
    //		mv.addObject("ztree", JSON.toJSONString(organizationTree));
    //		mv.addObject("orgId", orgId);
    //
    ////		OrganizationTree organizationTree = organizationService
    // .getOrganizationTreeWithCompany(sessionInfo.getOrganizationId());
    ////		mv.addObject("ztree", JSON.toJSONString(organizationTree));
    //		return mv;
    //	}
    @RequestMapping("/list")
    public ModelAndView index(HttpServletRequest request,
            SessionInfo sessionInfo,
            @RequestParam(value = "orgId", required = false) Integer orgId,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "keywd", required = false) String keywd,
            @RequestParam(value = "sort", required = false) String sort) {
        ModelAndView mv = new ModelAndView("2/hr/driver/index_list");
        if (orgId == null || orgId <= 0) {
            orgId = sessionInfo.getOrganizationId();
        }
        // 设置默认每页条数为20
        if (rows == null || rows == 0) {
            rows = 20;
        }
        // 设置默认为第一页
        if (page == null || page == 0) {
            page = 1;
        }
        List<DriverIndex> drivers = driverService.getDriverByStatus(
                DriverStatusEnum.NEEDTTRAINED.getStatus(), page, rows, keywd, sort, orgId);
        mv.addObject("drivers", drivers);
        mv.addObject("keywdNow", keywd);
        mv.addObject("sortNow", sort);
        mv.addObject("rowsNow", rows);
        mv.addObject("pageNow", page);
        mv.addObject("pageNum",
                driverService.getPageNum(DriverStatusEnum.NEEDTTRAINED.getStatus(), page, rows,
                        keywd, sort, orgId));
        mv.addObject("driverNum",
                driverService.getDriverNum(DriverStatusEnum.NEEDTTRAINED.getStatus(), keywd,
                        orgId));

        mv.addObject("orgId", orgId);
        mv.addObject("orgName", driverService.getOrgNameByOrgId(orgId));
        return mv;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index_(@RequestParam(value = "orgId", required = false) Integer orgId,
            Model model, SessionInfo sessionInfo) {
        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        model.addAttribute("ztree", JSON.toJSONString(organizationTree));
        model.addAttribute("orgId", orgId);
        return "2/hr/driver/index_";
    }

    /**
     * 删除某个驾驶员
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{driverId}/delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteDriver(@PathVariable Integer driverId) {
        try {
            driverService.deleteDriver(driverId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 更新驾驶员准备页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{driverId}/update", method = RequestMethod.GET)
    public ModelAndView updateDriver(
            SessionInfo sessionInfo,
            @RequestParam(value = "orgId", required = false) Integer orgId,
            @PathVariable Integer driverId) {
        ModelAndView mv = new ModelAndView("2/hr/driver/driver_update");
        List<OrganizationDto> organizationDtos = driverService.getAllCarPrices();
        mv.addObject("orgs", organizationDtos);
        Tdriver driver = driverService.getById(driverId);
        Torganization org = driverService.getTorganization(driverId);
        List<Torganization> orgs = organizationService.findOrganizationsByType(org.getType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (driver.getDriveLicenceStartTime() != null) {
            mv.addObject("driveLicenceStartTimeStr", sdf.format(driver.getDriveLicenceStartTime()));
        } else {
            mv.addObject("driveLicenceStartTimeStr", "");
        }
        if (driver.getDriveLicenceEndTime() != null) {
            mv.addObject("driveLicenceEndTimeStr", sdf.format(driver.getDriveLicenceEndTime()));
        } else {
            mv.addObject("driveLicenceEndTimeStr", "");
        }
        if (driver.getSafeCentificateStartTime() != null) {
            mv.addObject("safeCentificateStartTimeStr",
                    sdf.format(driver.getSafeCentificateStartTime()));
        } else {
            mv.addObject("safeCentificateStartTimeStr", "");
        }
        if (driver.getSafeCentificateEndTime() != null) {
            mv.addObject("safeCentificateEndTimeStr",
                    sdf.format(driver.getSafeCentificateEndTime()));
        } else {
            mv.addObject("safeCentificateEndTimeStr", "");
        }

        mv.addObject("driver", driver);
        mv.addObject("orgs", orgs);
        mv.addObject("org", org);
        short orgType = org.getType();
        mv.addObject("driver", driver);
        mv.addObject("orgType", orgType);
        mv.addObject("orgId", org.getId());
        //该type下的组织
        mv.addObject("orgs", orgs);

        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        mv.addObject("ztree", JSON.toJSONString(organizationTree));
        mv.addObject("orgId", orgId);
        mv.addObject("orgName", driverService.getOrgNameByOrgId(orgId));

        return mv;
    }

    /**
     * 创建一个驾驶员
     *
     * @throws ParseException
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{driverId}/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateDrivers(DriverDto driver,
            @PathVariable Integer driverId,
            @RequestParam(value = "org_id", required = false) Integer orgId,
            @RequestParam(value = "driveLicenceStartTimeStr", required = false)
                    String driveLicenceStartTimeStr,
            @RequestParam(value = "driveLicenceEndTimeStr", required = false)
                    String driveLicenceEndTimeStr,
            @RequestParam(value = "safeCentificateStartTimeStr", required = false)
                    String safeCentificateStartTimeStr,
            @RequestParam(value = "safeCentificateEndTimeStr", required = false)
                    String safeCentificateEndTimeStr) throws ParseException {
        driver.setId(driverId);
        Torganization organization = organizationService.getById(orgId);
        driver.setTorganization(organization);
        String sfzNo = driver.getSfzNo();
        //设置初始密码 身份证后六位
        String pwd = sfzNo.substring(12, 18);
        driver.setPassword(pwd);
        //根据身份证获取驾驶员生日
        String birthStr = sfzNo.substring(6, 10)
                + "-" + sfzNo.substring(10, 12)
                + "-" + sfzNo.substring(12, 14);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date birth = sdf.parse(birthStr);
        driver.setBirth(birth);
        //设置String -> Date
        if (driveLicenceStartTimeStr != null && driveLicenceStartTimeStr.length() > 0) {
            driver.setDriveLicenceStartTime(sdf.parse(driveLicenceStartTimeStr));
        } else {
            driver.setDriveLicenceStartTime(null);
        }
        if (driveLicenceEndTimeStr != null && driveLicenceEndTimeStr.length() > 0) {
            driver.setDriveLicenceEndTime(sdf.parse(driveLicenceEndTimeStr));
        } else {
            driver.setDriveLicenceEndTime(null);
        }
        if (safeCentificateStartTimeStr != null && safeCentificateStartTimeStr.length() > 0) {
            driver.setSafeCentificateStartTime(sdf.parse(safeCentificateStartTimeStr));
        } else {
            driver.setSafeCentificateStartTime(null);
        }
        if (safeCentificateEndTimeStr != null && safeCentificateEndTimeStr.length() > 0) {
            driver.setSafeCentificateEndTime(sdf.parse(safeCentificateEndTimeStr));
        } else {
            driver.setSafeCentificateEndTime(null);
        }
        driverService.update(driver);
        return true;
    }

    /**
     * 审查新某个驾驶员
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{driverId}/check", method = RequestMethod.GET)
    public ModelAndView getUnPassDriver(
            @PathVariable Integer driverId) {
        ModelAndView mv = new ModelAndView("hr/driver/check_driver");

        Tdriver driver = driverService.getById(driverId);
        Torganization organization = driverService.getTorganization(driverId);
        mv.addObject("driver", driver);
        mv.addObject("organization", organization);
        return mv;
    }

    /**
     * 审核所以驾驶员页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/checks", method = RequestMethod.GET)
    public ModelAndView checkDrivers(HttpServletRequest request,
            SessionInfo sessionInfo,
            @RequestParam(value = "orgId", required = false) Integer orgId,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "keywd", required = false) String keywd,
            @RequestParam(value = "sort", required = false) String sort) {
        ModelAndView mv = new ModelAndView("hr/driver/check_drivers");
        if (orgId == null || orgId <= 0) {
            orgId = sessionInfo.getOrganizationId();
        }
        // 设置默认每页条数为20
        if (rows == null || rows == 0) {
            rows = 20;
        }
        // 设置默认为第一页
        if (page == null || page == 0) {
            page = 1;
        }
        List<DriverIndex> dirverUnPass = driverService.getDriverByStatus((short) 2, page, rows,
                keywd, sort, orgId);
        mv.addObject("driverUnPass", dirverUnPass);
        mv.addObject("keywdNow", keywd);
        mv.addObject("sortNow", sort);
        mv.addObject("rowsNow", rows);
        mv.addObject("pageNow", page);
        mv.addObject("pageNum",
                driverService.getPageNum((short) 0, page, rows, keywd, sort, orgId));
        mv.addObject("driverNum", driverService.getDriverNum((short) 0, keywd, orgId));
        return mv;
    }

    /**
     * 驾驶员审核通过
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "{driverId}/pass", method = RequestMethod.POST)
    @ResponseBody
    public boolean pass(@PathVariable Integer driverId) {
        Tdriver driver = driverService.getById(driverId);
        driver.setStatus((short) 1);
        driverService.saveOrupdate(driver);
        return true;
    }

    /**
     * 驾驶员审核不通过
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "{driverId}/pass", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean unpass(@PathVariable Integer driverId) {
        Tdriver driver = driverService.getById(driverId);
        driver.setStatus((short) -2);
        driverService.saveOrupdate(driver);
        return true;
    }

    /**
     * @return 驾驶员入职页面
     */
    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public String entry() {
        return "2/hr/driver/entry";
    }

    /**
     * 添加驾驶员验证身份证是否已存在
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/entry/{sfzNo}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkEntrySfz(
            @PathVariable("sfzNo") String sfzNo) {
        return driverService.checkEntrySfzExist(sfzNo);
    }

    @RequestMapping(value = "/doEntry/{sfzNo}", method = RequestMethod.GET)
    public String entryDriver(
            SessionInfo sessionInfo,
            @PathVariable("sfzNo") String sfzNo) {
        Integer orgId = sessionInfo.getOrganizationId();
        if (driverService.entryDriver(sfzNo, orgId)) {
            return "redirect:/driver/";
        }
        return "";
    }

    /**
     * 根据身份证查看驾驶员资料<br>
     * 车企需要查看未入职的驾驶员历史档案
     * <p/>
     * -dxb
     *
     * @param sfzNo 驾驶员身份证号
     * @return 若果驾驶员存在 重定向到驾驶员详情页，<br> 否则返回不存在页面
     */
    @RequestMapping(value = "/sfz/{sfzNo}", method = RequestMethod.GET)
    public String getDriverDetailBySfzNo(@PathVariable String sfzNo, Model model) {
        Tdriver tdriver = driverService.getDriverBySfzNo(sfzNo);
        if (tdriver == null) {
            model.addAttribute("sfz", sfzNo);
            return "2/hr/driver/failSearch";
        } else {
            return "redirect:/driver/" + tdriver.getId();
        }
    }

    /**
     * -dxb
     *
     * @return 根据身份证搜索驾驶员的页面
     */
    @RequestMapping(value = "/sfz", method = RequestMethod.GET)
    public String pageSearchBySfz() {
        return "2/hr/driver/searchSfz";
    }

    /**
     * 通过Excel上传driver页面
     */
    @RequestMapping(value = "/loadExcel", method = RequestMethod.GET)
    public String uploadDriverExcel() {
        return "2/hr/driver/upload_driver_excel";
    }

    /**
     * 通过Excel上传driver
     */
    @RequestMapping(value = "/loadExcel", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadDriverExcels(
            @RequestParam("excelPath") String path,
            SessionInfo sessionInfo,
            ServletRequest request,
            @RequestParam(value = "excelStart", required = false) Integer start,
            @RequestParam(value = "excelEnd", required = false) Integer end) {
        String paths = request.getServletContext().getRealPath("/");
        //		String fileName = path.substring(path.length()-19);
        String[] fileContext = path.split("/");
        String fileName = fileContext[fileContext.length - 1];

        paths = paths.replaceAll("\\\\", "/");
        paths += "uploadFile/hr/driver/excel/" + fileName;

        Map<String, Object> map = new HashMap<String, Object>();
        if (start == null || start == 0) {
            start = 1;
        }
        if (end == null || end == 0) {
            end = 2147483647;
        }

        try {
            List<DriverExcelDto> driverExcelDto = driverService.readDriverByExcelDto(paths, start,
                    end);
            Integer orgId = sessionInfo.getOrganizationId();
            List<Tdriver> tdrivers = driverService.excelsDtoTransToTdrivers(driverExcelDto);
            for (Tdriver tdriver : tdrivers) {
                //driverService.addOrganization(tdriver,orgId);
                tdriver.setGender((short) 1);
                tdriver.setStatus(DriverStatusEnum.NEEDTTRAINED.getStatus());
            }
            driverService.createTdrivers(tdrivers);
            map.put("success", true);
            File fileExcel = new File(paths);
            if (fileExcel.isFile() && fileExcel.exists()) {
                fileExcel.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errorInfo", "Excel存在格式不正确的驾驶员信息");
            logger.info("---the Exception is--" + e.toString() + "------");
        }
        return map;
    }
}
