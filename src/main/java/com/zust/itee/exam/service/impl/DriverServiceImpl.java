package com.zust.itee.exam.service.impl;

import static com.zust.itee.exam.util.MyExcelUtil.getValue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.entity.user.User;
import com.zust.itee.enums.NormalStatusType;
import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.DriverDto;
import com.zust.itee.exam.dto.DriverExcelDto;
import com.zust.itee.exam.dto.DriverIndex;
import com.zust.itee.exam.dto.DriverVue;
import com.zust.itee.exam.dto.OrganizationDto;
import com.zust.itee.exam.dto.driverRecord.ExamIndexDto;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.enums.LoginStateEnum;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.DriverService;
import com.zust.itee.exam.service.OrganizationService;
import com.zust.itee.exam.util.MyEncryptUtil;
import com.zust.itee.exam.util.MyExcelUtil;
import com.zust.itee.exam.util.MyStrUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DriverServiceImpl extends BaseServiceImpl<Tdriver> implements
        DriverService {

    public static boolean encodePasswd = false;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<DataDictionary> dataDictionaryDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Resource
    private OrganizationService organizationService;

    /**
     * 把登录信息放到session中
     *
     * @why
     */
    private void addToSession(HttpSession session, Tdriver tdriver, String ip) {
        SessionInfo sessionInfo = new SessionInfo(tdriver, ip);
        session.setAttribute("sessionInfo", sessionInfo);
    }

    @Override
    public List<DriverIndex> getDriverByStatus(short status, Integer page,
            Integer rows, String keywd, String sort, Integer orgId) {
        String hql = " from Tdriver d where d.status =" + status;
        hql += " and (" +
                " (d.torganization.id = " + orgId + ")" +
                " or (d.torganization.torganization.id = " + orgId + ")" +
                ")";

        // 搜索
        if (!MyStrUtil.hasEmpty(keywd)) {
            String key = "'%" + keywd + "%'";
            hql += " and ( d.name like " + key;
            hql += " or d.torganization.name like " + key;
            // hql += " or cast(d.sfzNo as string) like " + key;
            hql += " or d.sfzNo like " + key;
            hql += " or d.mobile like " + key;
            hql += " or d.birthPlace like " + key;
            hql += " ) ";
        }

        // 排序
        String sortHql = " order by d.id desc";
        if (!MyStrUtil.hasEmpty(sort)) {
            sortHql = " order by d." + sort;
        }
        hql += sortHql;

        List<Tdriver> drivers = driverDao.find(hql, page, rows);
        List<DriverIndex> driverIndex = new ArrayList<>();
        for (Tdriver driver : drivers) {
            Torganization organization = driver.getTorganization();
            String gen = driver.getGender() == 0 ? "女" : "男";
            // BeanUtils.copyProperties(driver, driverIn);
            // error : Target must not be null
            DriverIndex driverIn = new DriverIndex(driver.getId(),
                    organization.getName(), driver.getName(),
                    driver.getBirthPlace(), gen, driver.getSfzNo(),
                    driver.getMobile(), driver.getDriveLicenceNo());
            driverIndex.add(driverIn);
        }
        return driverIndex;
    }

    @Override
    public Tdriver getByOtaUserId(Integer userId) {
        String hql = "from Tdriver where otaUserId=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return driverDao.get(hql, params);
    }

    @Override
    public void saveByOtaUser(User user) {
        log.info("==创建考试系统驾驶员== user:{}", user);
        Tdriver driver = new Tdriver();
        driver.setOtaUserId(user.getId());
        driver.setTorganization(organizationService.getByOtaOrgId(user.getOrgId()));
        driver.setName(user.getName());
        driver.setPassword(user.getPassword());
        driver.setMobile(user.getPhone());
        driver.setEmail(user.getEmail());
        driver.setSfzNo(user.getIdCard());
        driver.setStatus(NormalStatusType.NORMAL.getStatus());
        log.info("==创建考试系统驾驶员，对应驾驶员== driver:{}", JSONObject.toJSONString(driver));
        save(driver);
    }

    @Override
    public void updateWhenOtaUserApproval(User user) {
        Tdriver driver = getByOtaUserId(user.getId());
        if (driver != null) {
            driver.setSfzNo(user.getIdCard());
            saveOrupdate(driver);
        }
    }

    @Override
    public void updateDriverBaseInfoByOta(User user) {
        log.info("==更新考试系统用户信息，对应驾驶员== user:{}", user);
        Tdriver driver = getByOtaUserId(user.getId());
        if (driver != null) {
            driver.setName(user.getName());
            driver.setMobile(user.getPhone());
            log.info("==更新考试系统用户信息，驾驶员信息更改== driver:{}", driver);
            saveOrupdate(driver);
        }
    }

    @Override
    public DriverIndex transToModel(Tdriver driver) {

        Torganization organization = getTorganization(driver.getId());
        String gen = driver.getGender() == 0 ? "女" : "男";
        DriverIndex driverIn = new DriverIndex(driver.getId(),
                organization.getName(), driver.getName(),
                driver.getBirthPlace(), gen, driver.getSfzNo(),
                driver.getMobile(), driver.getDriveLicenceNo());
        return driverIn;
    }

    @Override
    public Tdriver dtotransToTdriver(DriverDto driverDto) {
        //Torganization torganization = getTorganization(driverDto.getId());
        //logger.info(torganization.getName());

        Tdriver tdriver = new Tdriver(driverDto.getId(), driverDto.getTorganization(),
                driverDto.getName(), driverDto.getBirthPlace(), driverDto.getBirth(),
                driverDto.getGender(), driverDto.getSfzNo(), driverDto.getPhoto(),
                driverDto.getAddress(),
                driverDto.getPostcode(), driverDto.getMobile(), driverDto.getPassword(),
                driverDto.getEmail(),
                driverDto.getEmergencyContact(), driverDto.getEmergencyContactMobile(),
                driverDto.getDriveLicenceNo(), driverDto.getDriveLincencePhoto(),
                driverDto.getDriveLicenceStartTime(), driverDto.getDriveLicenceEndTime(),
                driverDto.getSafeCentificateNo(), driverDto.getSafeCentificatePhoto(),
                driverDto.getSafeCentificateStartTime(), driverDto.getSafeCentificateEndTime(),
                driverDto.getStatus());

        return tdriver;
    }

    @Override
    public List<DriverIndex> transToModel(List<Tdriver> drivers) {
        List<DriverIndex> driverIndexs = new ArrayList<>();
        for (Tdriver driver : drivers) {
            driverIndexs.add(transToModel(driver));
        }
        return driverIndexs;
    }

    @Override
    public Tdriver excelDtoTransToTdriver(DriverExcelDto det) {
        Tdriver driver = new Tdriver();
        BeanUtils.copyProperties(det, driver);
        driver.setDriveLicenceNo(det.getSfzNo());
        driver.setTorganization(organizationDao.get(Torganization.class, det.getOrgId()));
        return driver;
        /*
        return new Tdriver(det.getName(), det.getBirthPlace(), det.getBirth(),
				(short)Math.floor(Double.parseDouble(det.getGender())),
				det.getSfzNo(), det.getAddress(), det.getPostcode(), det.getMobile(), det
				.getPassword(),
				det.getEmail(), det.getEmergencyContact(), det.getEmergencyContactMobile(),
				det.getDriveLicenceNo(), det.getDriveLicenceStartTime(), det
				.getDriveLicenceEndTime(),
				det.getSafeCentificateNo(), det.getSafeCentificateStartTime(), det
				.getSafeCentificateEndTime());
		*/
    }

    @Override
    public List<Tdriver> excelsDtoTransToTdrivers(List<DriverExcelDto> driverEDtos) {
        List<Tdriver> tdrivers = new ArrayList<>();
        for (DriverExcelDto det : driverEDtos) {
            Tdriver tdriver = excelDtoTransToTdriver(det);
            tdrivers.add(tdriver);
        }
        return tdrivers;
    }

    @Override
    public long getPageNum(short status, Integer page, Integer rows,
            String keywd, String sort, Integer orgId) {

        String hql = "select count(*) from Tdriver d where d.status =" + status;
        hql += " and (" +
                " (d.torganization.id = " + orgId + ")" +
                " or (d.torganization.torganization.id = " + orgId + ")" +
                ")";
        // 搜索
        if (!MyStrUtil.hasEmpty(keywd)) {
            String key = "'%" + keywd + "%'";
            hql += " and ( d.name like " + key;
            hql += " or d.torganization.name like " + key;
            // hql += " or cast(d.sfzNo as string) like " + key;
            hql += " or d.sfzNo like " + key;
            hql += " or d.mobile like " + key;
            hql += " or d.birthPlace like " + key;
            hql += " ) ";
        }
        long size = driverDao.count(hql);
        long pageNum = size / rows + (size % rows == 0 ? 0 : 1);
        return pageNum;
    }

    @Override
    public LoginStateEnum verifyAccount(String moble, String password,
            HttpSession session, String ip) {

        String hql = "from Tdriver d where d.mobile = :mobile";
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", moble);

        Tdriver tdriver = driverDao.get(hql, params);

        if (tdriver == null) {
            // 账户不存在
            return LoginStateEnum.ACCOUNT_NOT_EXSIT;
        }

        if (encodePasswd) {
            password = MyEncryptUtil.encodePasswd(password);
        }

        if (tdriver.getPassword() == null) {
            // 驾驶员密码为空
            if (tdriver.getSfzNo() == null) {
                // 驾驶员身份证为空
                logger.debug("该驾驶员身份证为空 驾驶员id：" + tdriver.getId());
                return LoginStateEnum.UNKONW;
            } else {
                // 验证身份证后六位为密码
                String defaultPassword = tdriver.getSfzNo().substring(
                        tdriver.getSfzNo().length() - 6,
                        tdriver.getSfzNo().length());
                if (encodePasswd) {
                    defaultPassword = MyEncryptUtil
                            .encodePasswd(defaultPassword);
                }

                if (password.equals(defaultPassword)) {
                    // 验证成功
                    addToSession(session, tdriver, ip);
                    return LoginStateEnum.SUCCESS;
                } else {
                    // 密码错误
                    return LoginStateEnum.ERROR_PASSWORD;
                }
            }
        }

        if (password.equals(tdriver.getPassword())) {
            // 验证成功
            addToSession(session, tdriver, ip);
            return LoginStateEnum.SUCCESS;
        } else {
            return LoginStateEnum.ERROR_PASSWORD;
        }
    }

    /**
     * 验证身份证是否已存在
     * -modify dxb
     *
     * @why
     */
    @Override
    public boolean checkSfzExist(String sfzNo) {
        String hql = "select count(*) from Tdriver d where upper(d.sfzNo) = '"
                + sfzNo.toUpperCase()
                + "' "
                +
                " and d.status != "
                + DriverStatusEnum.QUIT.getStatus();
        return driverDao.count(hql) == 0;
    }

    @Override
    public boolean checkEntrySfzExist(String sfzNo) {
        String hql = "select count(*) from Tdriver d where upper(d.sfzNo) = '"
                + sfzNo.toUpperCase()
                + "' "
                +
                " and d.status = "
                + DriverStatusEnum.QUIT.getStatus();
        return driverDao.count(hql) == 0;
    }

    /**
     * 验证手机号是否存在
     *
     * @author ly -modify dxb
     */
    @Override
    public boolean checkMobileExist(String mobile) {
        String hql = "select count(*) from Tdriver d where d.mobile = '" + mobile + "'";
        return driverDao.count(hql) == 0;
    }

    @Override
    public boolean checkOrgHasUnconfirmedInsurance(Integer orgId) {
        return true;
    }

    @Override
    public Integer getOrgIdByDriverId(Integer driverId) {
        Tdriver tdriver = driverDao.get(Tdriver.class, driverId);
        return tdriver.getTorganization().getId();
    }

    @Override
    public String getOrgNameByOrgId(Integer orgId) {
        if (orgId == null || orgId < 1) {
            return null;
        }
        return organizationDao.get(Torganization.class, orgId).getName();
    }

    @Override
    public Integer getOrgIdByOrgName(String name) {
        String hql = "from Torganization o where o.name = '" + name + "'";
        Torganization org = organizationDao.get(hql);
        if (org != null) {
            return organizationDao.get(hql).getId();
        } else {
            return 1000;
        }
    }

    /**
     * 创建一个驾驶员
     * -modify dxb
     *
     * @why
     */
    @Override
    public boolean createDriver(DriverDto driver) {
        driver.setSfzNo(driver.getSfzNo().toUpperCase());

        driverDao.save(dtotransToTdriver(driver));
        return true;
    }

    @Override
    public void createTdriver(Tdriver tdriver) {
        if (checkSfzExist(tdriver.getSfzNo())) {
            driverDao.save(tdriver);
        } else {
            logger.info("------遇到重复驾驶员------ name is:" + tdriver.getName() +
                    "\t\tsfzNo is:" + tdriver.getSfzNo());
        }
    }

    @Override
    public void createTdrivers(List<Tdriver> tdrivers) {
        for (Tdriver tdriver : tdrivers) {
            createTdriver(tdriver);
        }
    }

    @Override
    public void deleteDriver(Integer driverId) {
        Tdriver tdriver = driverDao.get(Tdriver.class, driverId);
        tdriver.setStatus((short) -1);
        driverDao.update(tdriver);
    }

    @Override
    public boolean entryDriver(String sfzNo, Integer orgId) {
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DataDictionary> getDriverHonourTypes() {
        //		声明codeType
        String codeType = "driver_honour_type";

        //		找根节点
        String getCtrlHql = "from DataDictionary d where d.codeType = '"
                + codeType + "'";
        DataDictionary ctrlDD = dataDictionaryDao.get(getCtrlHql);

        //		如果根节点为空 返回一个长度为0的数组
        if (ctrlDD == null) {
            return new ArrayList<>();
        }

        //		找子集节点
        Long ctrlId = ctrlDD.getId();
        String findChilds = "from DataDictionary d where d.dataDictionaryByCtrlId = '"
                + ctrlId + "'";
        List<DataDictionary> list = dataDictionaryDao.find(findChilds);
        return list;
    }

    @Override
    public Torganization getTorganization(Integer driverId) {
        Integer orgId = driverDao.get(Tdriver.class, driverId)
                .getTorganization().getId();
        return organizationDao.get(Torganization.class, orgId);
    }

    @Override
    public List<OrganizationDto> getAllCarPrices() {
        String hql = " from Torganization o where o.type = "
                + OrganizationTypeEnum.COMPANY.getType();
        List<Torganization> torganizations = organizationDao.find(hql);
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for (Torganization o : torganizations) {
            OrganizationDto org = new OrganizationDto();
            org.setName(o.getName());
            org.setId(o.getId());
            organizationDtos.add(org);
        }
        return organizationDtos;
    }

    @Override
    public long getDriverNum(short status, String keywd, Integer orgId) {
        String hql = "select count(*) from Tdriver d where d.status =" + status;
        hql += " and (" +
                " (d.torganization.id = " + orgId + ")" +
                " or (d.torganization.torganization.id = " + orgId + ")" +
                ")";
        // 搜索
        if (!MyStrUtil.hasEmpty(keywd)) {
            String key = "'%" + keywd + "%'";
            hql += " and ( d.name like " + key;
            hql += " or d.torganization.name like " + key;
            // hql += " or cast(d.sfzNo as string) like " + key;
            hql += " or d.sfzNo like " + key;
            hql += " or d.mobile like " + key;
            hql += " or d.birthPlace like " + key;
            hql += " ) ";
        }
        return driverDao.count(hql);
    }

    @Override
    public Tdriver getDriverBySfzNo(String sfzNo) {
        String hql = "from Tdriver d where upper(d.sfzNo) = :sfz";
        Map<String, Object> params = new HashMap<>();
        params.put("sfz", sfzNo.toUpperCase());

        return driverDao.get(hql, params);
    }

    @Override
    public Tdriver getDriverByMpbile(String mobile) {
        String hql = "from Tdriver d where d.mobile = " + mobile;
        return driverDao.get(hql);
    }

    @Override
    public Tdriver getDriverByName(String name) {
        String hql = "from Tdriver d where d.name = " + name;
        return driverDao.get(hql);
    }

    @Override
    public void update(DriverDto driverDto) {
        Tdriver driver = dtotransToTdriver(driverDto);
        driverDao.saveOrUpdate(driver);
    }

    @Override
    public boolean quitDriver(Integer driverId) {
        try {
            Tdriver tdriver = driverDao.get(Tdriver.class, driverId);
            tdriver.setStatus(DriverStatusEnum.QUIT.getStatus());
            driverDao.update(tdriver);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Tdriver addOrganization(Tdriver tdriver, Integer orgId) {
        tdriver.setTorganization(organizationDao.get(Torganization.class, orgId));
        return tdriver;
    }

    @Override
    public List<DriverVue> getDriverByOrgId(Integer orgId) {
        String hql = " from Tdriver d where d.torganization.id = " + orgId;
        List<Tdriver> tdrivers = driverDao.find(hql);
        List<DriverVue> driverVues = new ArrayList<>();
        for (Tdriver d : tdrivers) {
            DriverVue dv = new DriverVue(d.getId(), d.getName());
            driverVues.add(dv);
        }
        return driverVues;
    }

    @Override
    public List<DriverExcelDto> readDriverByExcelDto(String path, int start, int end) {
        try {

            if (MyStrUtil.hasEmpty(path)) {
                return null;
            }
            Sheet sheet = null;

            if (MyExcelUtil.isXls(path)) {
                sheet = MyExcelUtil.readXlsToSheet(path);
            } else if (MyExcelUtil.isXlsx(path)) {
                sheet = MyExcelUtil.readXlsxToSheet(path);
            }
            //            else
            //                throw new ExcelUtilException("文件不是EXCEL表格");

            if (sheet == null) {
                //                throw new ExcelUtilException("EXCEL中读取不到SHEET");
            } else {
                List<DriverExcelDto> list = new ArrayList<>();
                for (int i = start; i < end; i++) {
                    Row row = sheet.getRow(i);

                    if (row != null) {

                        Cell nameC = row.getCell(0);
                        Cell orgNameC = row.getCell(1);
                        Cell birthC = row.getCell(2);
                        Cell mobileC = row.getCell(3);
                        Cell sfzNoC = row.getCell(4);
                        Cell train_centificate_noC = row.getCell(5);
                        Cell addressC = row.getCell(6);
                        Cell emergency_contactC = row.getCell(7);
                        Cell emergency_contact_mobileC = row.getCell(8);

                        //简单String录入
                        DriverExcelDto driverDto = new DriverExcelDto(
                                getOrgIdByOrgName(getValue(orgNameC)), getValue(orgNameC),
                                getValue(nameC),
                                getValue(mobileC), getValue(sfzNoC),
                                getValue(train_centificate_noC),
                                getValue(addressC), getValue(emergency_contactC),
                                getValue(emergency_contact_mobileC)
                        );

                        //查找考生办
                        Integer orgId = getOrgIdByOrgName(getValue(orgNameC));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        //通过身份证号录入 生日  密码（身份证后六位）
                        String sfzStr = getValue(sfzNoC);
                        if (!MyStrUtil.hasEmpty(sfzStr)) {
                            //密码（身份证后六位）

                            if (sfzStr.length() == 18) {//18位身份证
                                String birthStr = sfzStr.substring(6, 10)
                                        + "-" + sfzStr.substring(10, 12)
                                        + "-" + sfzStr.substring(12, 14);
                                driverDto.setBirth(sdf.parse(birthStr));
                                driverDto.setPassword(
                                        sfzStr.substring(sfzStr.length() - 6, sfzStr.length()));
                            } else if (sfzStr.length() == 15) {//15位身份证
                                String birthStr = "19" + sfzStr.substring(6, 8)
                                        + "-" + sfzStr.substring(8, 10)
                                        + "-" + sfzStr.substring(10, 12);
                                driverDto.setBirth(sdf.parse(birthStr));
                                driverDto.setPassword(
                                        sfzStr.substring(sfzStr.length() - 6, sfzStr.length()));
                            } else {
                                driverDto.setPassword("0000");
                            }
                            //if (checkSfzExist(sfzStr)) continue;

                        } else {
                            driverDto.setSfzNo("0000");
                            driverDto.setPassword("0000");
                        }

                        //不能为空的人员
                        if (MyStrUtil.hasEmpty(getValue(nameC))) {
                            driverDto.setName("Excel导入冗余项");
                        }
                        //						if (MyStrUtil.hasEmpty(getValue(birth_placeC))) {
                        if (true) {
                            driverDto.setBirthPlace("浙");
                        }

                        //录入四个日期
                        /*
                        String licence_start_timeStr = getDateCellValue(drive_licence_start_time);
						String licence_end_timeStr = getDateCellValue(drive_licence_end_time);
						String safe_centificate_start_timeStr = getDateCellValue
						(safe_centificate_start_time);
						String safe_centificate_end_timeStr = getDateCellValue
						(safe_centificate_end_time);

						if (licence_start_timeStr != null)
							driverDto.setDriveLicenceStartTime(sdf.parse(licence_start_timeStr));
						if (licence_end_timeStr != null)
							driverDto.setDriveLicenceEndTime(sdf.parse(licence_end_timeStr));
						if (safe_centificate_start_timeStr != null)
							driverDto.setSafeCentificateStartTime(sdf.parse
							(safe_centificate_start_timeStr));
						if (safe_centificate_end_timeStr != null)
							driverDto.setSafeCentificateEndTime(sdf.parse
							(safe_centificate_end_timeStr));
						*/

                        list.add(driverDto);
                    } else {
                        //i = end-1;
                        logger.info("-----ok, this is the excel end!!!!-------" + i + "---" + (i
                                - end));
                        break;
                    }
                }
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ExamIndexDto> getDriverIndexExamInfo(Integer driverId) {
        String hql = " from TexamSignup e where e.tdriver.id = " + driverId
                + " and e.status = " + ExamStatusEnum.FINISHED.getState();
        List<TexamSignup> texamSignups = examSignupDao.find(hql);
        List<ExamIndexDto> examIndexDtos = new ArrayList<>();
        if (texamSignups.size() > 0) {
            for (TexamSignup es : texamSignups) {
                Texam texam = examDao.get(Texam.class, es.getTexam().getId());
                examIndexDtos.add(new ExamIndexDto(es.getId(), texam.getId(),
                        texam.getName(), texam.getExamStartTime(),
                        es.getFinalScore(), es.getCertificateNo()));
            }
        }
        return examIndexDtos;
    }
}
