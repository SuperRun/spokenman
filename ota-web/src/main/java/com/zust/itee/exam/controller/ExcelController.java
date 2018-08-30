package com.zust.itee.exam.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.exam.service.DataDictionaryService;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.OrganizationService;

/**
 * Created by liy on 2016/12/15.
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    private static int LIMITSIZE = 10 * 1024 * 1000;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * Excel上傳
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/excelHis")
    public @ResponseBody
    Map<String, Object> getDriverExcel(HttpServletRequest request) {
        return UploadController.upload(request, "hr/excel_upload/driver_work_history", LIMITSIZE);
    }

    @RequestMapping(value = "/excelIns")
    public @ResponseBody
    Map<String, Object> getInsuranceExcel(HttpServletRequest request) {
        return UploadController.upload(request, "hr/excel_upload/driver_insurance", LIMITSIZE);
    }

    @RequestMapping(value = "/excelVeh")
    public @ResponseBody
    Map<String, Object> getVehicleInsuranceExcel(HttpServletRequest request) {
        return UploadController.upload(request, "hr/excel_upload/vehicle_insurance", LIMITSIZE);
    }
}
