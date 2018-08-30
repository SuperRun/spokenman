package com.zust.itee.exam.controller.hr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.controller.UploadController;
import com.zust.itee.exam.dto.DataDictionaryModel;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.OrganizationTree;
import com.zust.itee.exam.service.DataDictionaryService;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.service.VehicleService;

/**
 * Created by liy on 2016/10/15.
 */
@Controller
@RequestMapping("/hr/vehicle")
public class VehicleController {

    /**
     * 限制文件大小
     */
    private static int LIMITSIZE = 10 * 1024 * 1000;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private SessionInfo sessionPrInfo;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private OrganizationService organizationService;

    //    /**
    //     * 卡车列表
    //     *
    //     * @author ly
    //     *
    //     * @why
    //     *
    //     * @param request
    //     * @param rows
    //     * @param page
    //     * @param keywd
    //     * @param sort
    //     * @return
    //     */
    //    @RequestMapping("")
    //    public ModelAndView index(
    //            HttpServletRequest request,
    //            SessionInfo sessionInfo,
    //            @RequestParam(value = "orgId", required = false) Integer orgId,
    //            @RequestParam(value = "rows", required = false) Integer rows,
    //            @RequestParam(value = "page", required = false) Integer page,
    //            @RequestParam(value = "keywd", required = false) String keywd,
    //            @RequestParam(value = "sort", required = false) String sort){
    //        ModelAndView mv = new ModelAndView("2/hr/vehicle/index");
    //        if (orgId == null || orgId <= 0) orgId = sessionInfo.getOrganizationId();
    //        // 设置默认每页条数为20
    //        if (rows == null || rows == 0) 	rows = 20;
    //        // 设置默认为第一页
    //        if (page == null || page == 0) 	page = 1;
    //        List<VehicleDto> vehicleDtos = vehicleService.getVehicleByStatus((short) 1, page,
    // rows, keywd, sort, orgId);
    //        mv.addObject("vehicles", vehicleDtos);
    //        mv.addObject("keywdNow", keywd);
    //        mv.addObject("sortNow", sort);
    //        mv.addObject("rowsNow", rows);
    //        mv.addObject("pageNow", page);
    //        mv.addObject("pageNum", vehicleService.getPageNum((short)1, page, rows, keywd,
    // sort, orgId));
    //        mv.addObject("vehicleNum", vehicleService.getVehicleNum((short) 1, keywd, orgId));
    //
    //        OrganizationTree organizationTree = organizationService
    // .getOrganizationTreeWithoutCompany(sessionInfo.getOrganizationId());
    //        mv.addObject("ztree", JSON.toJSONString(organizationTree));
    //        mv.addObject("orgId", orgId);
    //        return mv;
    //    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index_(@RequestParam(value = "orgId", required = false) Integer orgId,
            Model model, SessionInfo sessionInfo) {
        sessionPrInfo = sessionInfo;
        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        model.addAttribute("ztree", JSON.toJSONString(organizationTree));
        model.addAttribute("orgId", orgId);
        return "2/hr/vehicle/index_";
    }

    /**
     * 添加页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createTruck(
            @RequestParam(value = "orgId", required = false) Integer orgId,
            SessionInfo sessionInfo) {
        ModelAndView mv = new ModelAndView("2/hr/vehicle/creation");
        List<OrganizationDto> organizationDtos = driverService.getAllCarPrices();
        mv.addObject("orgs", organizationDtos);
        String codeType = "special_vehicle_type";
        List<DataDictionaryModel> data = dataDictionaryService.getSubItemsOfMainItem(codeType);
        mv.addObject("dataDictionary", data);

        OrganizationTree organizationTree = organizationService.getOrganizationTreeWithCompany(
                sessionInfo.getOrganizationId());
        mv.addObject("ztree", JSON.toJSONString(organizationTree));
        mv.addObject("orgId", orgId);
        mv.addObject("orgName", driverService.getOrgNameByOrgId(orgId));
        return mv;
    }

    @RequestMapping(value = "/checkOrgIsCompany")
    @ResponseBody
    public boolean checkOrgIsCompany(
            @RequestParam(value = "orgId", required = false) Integer orgId) {
        return vehicleService.checkOrgIsCompany(orgId);
    }

    /**
     * 添加文件
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/excel")
    public @ResponseBody
    Map<String, Object> getDriverExcel(HttpServletRequest request) {
        return UploadController.upload(request, "hr/vehicle/excel", LIMITSIZE);
    }
}
