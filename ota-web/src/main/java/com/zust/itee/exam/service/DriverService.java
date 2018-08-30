package com.zust.itee.exam.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zust.itee.entity.user.User;
import com.zust.itee.exam.dto.DriverDto;
import com.zust.itee.exam.dto.DriverExcelDto;
import com.zust.itee.exam.dto.DriverIndex;
import com.zust.itee.exam.dto.DriverVue;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.driverRecord.ExamIndexDto;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.LoginStateEnum;

public interface DriverService extends BaseService<Tdriver> {

    /**
     * 根据新闻发言人平台 userId 获取驾驶员
     */
    Tdriver getByOtaUserId(Integer userId);

    /**
     * 更具新闻发言人平台个人用户创建驾驶员
     */
    void saveByOtaUser(User user);

    /**
     * 新闻发言人平台个人用户提交审核资料时更新驾驶员信息
     */
    void updateWhenOtaUserApproval(User user);

    /**
     * 根据新闻发言人平台个人用户更新驾驶员基本信息
     */
    void updateDriverBaseInfoByOta(User user);

    /**
     * driver加工成Index 的 Model
     *
     * @author ly
     * @why
     */
    DriverIndex transToModel(Tdriver driver);

    /**
     * @author ly
     * @why
     */
    Tdriver dtotransToTdriver(DriverDto driverDto);

    /**
     * 加工成Model
     *
     * @author ly
     * @why
     */
    List<DriverIndex> transToModel(List<Tdriver> driver);

    /**
     * excelDto转换成entity
     */
    Tdriver excelDtoTransToTdriver(DriverExcelDto det);

    List<Tdriver> excelsDtoTransToTdrivers(List<DriverExcelDto> driverEDtos);

    /**
     * 获得不同状态的驾驶员
     *
     * @author ly
     * @why
     */
    List<DriverIndex> getDriverByStatus(short status, Integer page, Integer rows, String keywd,
            String sort, Integer orgId);

    /**
     * 获取驾驶员的组织
     *
     * @author ly
     * @why
     */
    Torganization getTorganization(Integer driverId);

    List<OrganizationDto> getAllCarPrices();

    /**
     * 获得对应总页数
     *
     * @author ly
     * @why
     */
    long getPageNum(short status, Integer page, Integer rows, String keywd, String sort,
            Integer orgId);

    /**
     * 获得驾驶员的总个数
     *
     * @author ly
     * @why
     */
    long getDriverNum(short status, String keywd, Integer orgId);

    /**
     * 验证账户
     *
     * @author dxb
     * @why 驾驶员登录时验证账户
     */
    LoginStateEnum verifyAccount(String moble, String password,
            HttpSession session, String ip);

    /**
     * 验证身份证是否存在
     *
     * @author ly
     * @why
     */
    boolean checkSfzExist(String sfzNo);

    boolean checkEntrySfzExist(String sfzNo);

    /**
     * 验证手机号是否存在
     *
     * @author ly
     * @why
     */
    boolean checkMobileExist(String mobile);

    /**
     * 验证该车企是否存在没有确认责任人的保险事故
     */
    boolean checkOrgHasUnconfirmedInsurance(Integer orgId);

    Integer getOrgIdByDriverId(Integer driverId);

    String getOrgNameByOrgId(Integer orgId);

    Integer getOrgIdByOrgName(String name);

    /**
     * 创建驾驶员
     *
     * @author ly
     * @why
     */
    boolean createDriver(DriverDto driver);

    void createTdriver(Tdriver tdriver);

    void createTdrivers(List<Tdriver> tdrivers);

    void deleteDriver(Integer driverId);

    /**
     * 驾驶员重新入职
     */
    boolean entryDriver(String sfzNo, Integer orgId);

    /**
     * 查找数据字典中的荣誉类型
     *
     * @author ly
     * @why 驾驶员添加荣誉时，需要选择荣誉类型
     */
    List<DataDictionary> getDriverHonourTypes();

    /**
     * 车企可以通过驾驶员的身份证查看驾驶员的历史档案
     *
     * @author dxb
     */
    Tdriver getDriverBySfzNo(String sfzNo);

    Tdriver getDriverByMpbile(String mobile);

    Tdriver getDriverByName(String name);

    /**
     * 更新一个驾驶员
     *
     * @author ly
     */
    void update(DriverDto driverDto);

    boolean quitDriver(Integer driverId);

    Tdriver addOrganization(Tdriver tdriver, Integer orgId);

    List<DriverVue> getDriverByOrgId(Integer orgId);

    /**
     * 通过Excel上传driver
     */
    List<DriverExcelDto> readDriverByExcelDto(String path, int start, int end);

    List<ExamIndexDto> getDriverIndexExamInfo(Integer driverId);
}
