package com.zust.itee.exam.service.impl.exam.member.signup;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dao.hibernate.BaseDao2;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.PageBaseDtoForSignup;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.SignupDriver;
import com.zust.itee.exam.dto.exam.member.SignupOrganization;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.exam.member.signup.ExamMemberSignupService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;

@Service
@Transactional
public class ExamMemberSignupServiceImpl extends BaseServiceImpl<Texam>
        implements ExamMemberSignupService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<Tmember> memberDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao2<Tdriver> driverDao2;

    @Override
    public List<DriverInfoInExamManagement> getSignupDrivers(Integer examId,
            PageBaseDto pageBaseDto, Integer orgId) throws RuntimeException {
        List<DriverInfoInExamManagement> signupDriverInfos = new
                ArrayList<DriverInfoInExamManagement>();
        try {
            String hql = "from TexamSignup sdy where 1=1 " +
                    // 筛选报名了该考试的驾驶员
                    "and sdy.texam.id=:examId " + "and sdy.status<>:status "
                    // 属于当前车企
                    + "and sdy.tdriver.torganization.id=:orgId " +
					/*
					 * 添加搜索条件
					 */
                    // 驾驶员姓名
                    "and (sdy.tdriver.name like:key " +
                    // 驾驶员联系方式
                    "or sdy.tdriver.mobile like:key " +
                    // 车企名称
                    "or sdy.tdriver.torganization.name like:key " +
                    // 散装办名称
                    "or sdy.tdriver.torganization.torganization.name like:key) ";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("orgId", orgId);
            params.put("examId", examId);
            params.put("key", key);
            // 获取entity
            List<TexamSignup> texamSignups = examSignupDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            // 转换成dto
            signupDriverInfos = transTexamSignupToSignupDriverInfo(texamSignups);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return signupDriverInfos;
    }

    @Override
    public PageBaseDto getSignupDriversPageBaseDto(Integer examId,
            PageBaseDto pageBaseDto, Integer orgId) throws RuntimeException {
        PageBaseDto res = new PageBaseDto();
        try {
            String hql = "select count(*) from TexamSignup sdy where 1=1 " +
                    // 筛选报名了该考试的驾驶员
                    "and sdy.texam.id=:examId " + "and sdy.status<>:status "
                    // 属于当前车企
                    + "and sdy.tdriver.torganization.id=:orgId " +
					/*
					 * 添加搜索条件
					 */
                    // 驾驶员姓名
                    "and (sdy.tdriver.name like:key " +
                    // 驾驶员联系方式
                    "or sdy.tdriver.mobile like:key " +
                    // 车企名称
                    "or sdy.tdriver.torganization.name like:key " +
                    // 散装办名称
                    "or sdy.tdriver.torganization.torganization.name like:key) ";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("orgId", orgId);
            params.put("examId", examId);
            params.put("key", key);

            long sum = examSignupDao.count(hql, params);
            int pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                    / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);

            res.setPage(pageBaseDto.getPage());
            res.setPageNum(pageNum);
            res.setRows(pageBaseDto.getRows());
            res.setSearchKey(pageBaseDto.getSearchKey());
            res.setStatus(ExamStatusEnum.SIGNUP.getState());
            res.setSum(sum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return res;
    }

    @Override
    public List<SignupOrganization> getSignupInfo(Integer memberId,
            Integer examId) {
        try {
            List<SignupOrganization> resOrganizations = new ArrayList<>();
            // 获取工作人员所在组织
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember member = memberDao.get(hql);
            Torganization root = member.getTorganization();
            List<Torganization> organizationSource = new ArrayList<>();
            if (root.getType() == OrganizationTypeEnum.COMPANY.getType()) {
                // 组织为车企,直接加入返回车企list
                organizationSource.add(root);
            } else {
                // 不为车企，寻找下级所有车企
                hql = "from Torganization sdy where sdy.torganization.id=:rootOrganizationId";
                Map<String, Object> params = new HashMap<>();
                params.put("rootOrganizationId", root.getId());
                List<Torganization> sonTorganizations = organizationDao.find(
                        hql, params);
                int i = 0;
                for (i = 0; i < sonTorganizations.size(); i++) {
                    Torganization torganization = sonTorganizations.get(i);
                    if (torganization.getType() == OrganizationTypeEnum.COMPANY
                            .getType()) {
                        organizationSource.add(torganization);
                    } else {
                        hql = "from Torganization sdy where sdy.torganization.id=:organizationId";
                        params.clear();
                        params.put("organizationId", torganization.getId());
                        List<Torganization> grandSontTorganizations = organizationDao
                                .find(hql, params);
                        sonTorganizations.addAll(grandSontTorganizations);
                    }
                }
            }
            // 遍历车企赋值
            for (Torganization torganization : organizationSource) {
                SignupOrganization signupOrganization = new SignupOrganization();
                signupOrganization.setId(torganization.getId());
                signupOrganization.setName(torganization.getName());
				/*
				 * 获取车企内符合报名条件的学员
				 */
                hql = "from Tdriver sdy where 1=1 "
                        + "and sdy.torganization.id=:organizationId "
                        + "and sdy.status=:status ";
                Map<String, Object> params = new HashMap<>();
                params.put("organizationId", torganization.getId());
                params.put("status", DriverStatusEnum.NEEDTTRAINED.getStatus());
                List<Tdriver> drivers = driverDao.find(hql, params);
                List<SignupDriver> signupDrivers = new ArrayList<>();
                for (Tdriver tdriver : drivers) {
                    hql = "from TexamSignup sdy where 1=1 "
                            + "and sdy.texam.id=:examId "
                            + "and sdy.tdriver.id=:driverId ";
                    params.clear();
                    params.put("examId", examId);
                    params.put("driverId", tdriver.getId());
                    TexamSignup texamSignup = examSignupDao.get(hql, params);
                    if (texamSignup == null
                            || texamSignup.getStatus() == DriverExamEnum.CANCELED
                            .getStatus()) {
                        // 只显示还没有报名的学员
                        SignupDriver signupDriver = new SignupDriver();
                        signupDriver.setId(tdriver.getId());
                        signupDriver.setName(tdriver.getName());
                        signupDrivers.add(signupDriver);
                    }
                }
                signupOrganization.setDrivers(signupDrivers);
                resOrganizations.add(signupOrganization);
            }
            return resOrganizations;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean signupImport(String signupDriversString, Integer examId) {
        try {
            String[] driverIdSource = signupDriversString.split(";");
            // 获取考试
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            Date now = new Date();
            // 遍历驾驶员id
            for (int i = 0; i < driverIdSource.length; i++) {
                try {
                    Integer driverId = Integer.parseInt(driverIdSource[i]);
					/*
					 * 检查是否报名
					 */
                    hql = "from TexamSignup sdy where 1=1 "
                            + "and sdy.texam.id=:examId "
                            + "and sdy.tdriver.id=:driverId ";
                    Map<String, Object> params = new HashMap<>();
                    params.put("examId", examId);
                    params.put("driverId", driverId);
                    TexamSignup texamSignup = examSignupDao.get(hql, params);
                    if (texamSignup == null) {
                        // 没有报名记录插入报名记录
                        hql = "from Tdriver sdy where sdy.id='" + driverId
                                + "'";
                        Tdriver tdriver = driverDao.get(hql);
                        TexamSignup examSignup = new TexamSignup();
                        examSignup.setTexam(texam);
                        examSignup.setTdriver(tdriver);
                        examSignup.setSignupTime(now);
                        examSignup
                                .setStatus(DriverExamEnum.SIGNUPD.getStatus());
                        examSignupDao.saveOrUpdate(examSignup);
                    } else {
                        texamSignup.setStatus(DriverExamEnum.SIGNUPD
                                .getStatus());
                        examSignupDao.saveOrUpdate(texamSignup);
                    }
                } catch (Exception e) {
                    // 插入记录失败，继续下一条
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<DriverInfoInExamManagement> transTexamSignupToSignupDriverInfo(
            List<TexamSignup> texamSignups) {
        List<DriverInfoInExamManagement> signupDriverInfos = new
				ArrayList<DriverInfoInExamManagement>();
        for (TexamSignup texamSignup : texamSignups) {
            DriverInfoInExamManagement signupDriverInfo = new DriverInfoInExamManagement();
            signupDriverInfo.setDriverId(texamSignup.getTdriver().getId());
            signupDriverInfo.setName(texamSignup.getTdriver().getName());
            signupDriverInfo.setOrgName(texamSignup.getTdriver()
                    .getTorganization().getName());
            signupDriverInfo.setOrgParentName(texamSignup.getTdriver()
                    .getTorganization().getTorganization().getName());
            signupDriverInfo.setTel(texamSignup.getTdriver().getMobile());
            signupDriverInfos.add(signupDriverInfo);
            if (texamSignup.getFinalScore() != null
                    && texamSignup.getFinalScore() != 0.0) {
                signupDriverInfo.setScore(texamSignup.getFinalScore());
            }
        }
        return signupDriverInfos;
    }

    @Override
    public List<DriverInfoInExamManagement> getHavenotSignedupDrivers(
            Integer examId, PageBaseDtoForSignup pageBaseDtoForSignup,
            Integer orgId, String signupStatus) throws RuntimeException {
        List<DriverInfoInExamManagement> driverInfoInExamManagements = new ArrayList<>();
        try {
            // 是否筛选一般驾驶员
            Integer AVAILABLE = 0;
            // 是否筛选首次培训驾驶员
            Integer NEEDTTRAINED = 0;
            // 是否筛选重新培训驾驶员
            Integer RENEEDTTRAINED = 0;

			/*
			 * 状态判断
			 */
            if (signupStatus == null) {
                NEEDTTRAINED = 1;
            } else {
                String[] driverStatus = signupStatus.split(";");
                for (int i = 0; i < driverStatus.length; i++) {
                    if (driverStatus[i].equals("-1")) {
                        // 筛选全部
                        AVAILABLE = 1;
                        NEEDTTRAINED = 1;
                        RENEEDTTRAINED = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.AVAILABLE.getStatus()
                                    .toString())) {
                        AVAILABLE = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.NEEDTTRAINED.getStatus()
                                    .toString())) {
                        NEEDTTRAINED = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.RENEEDTTRAINED.getStatus()
                                    .toString())) {
                        RENEEDTTRAINED = 1;
                    }
                }
            }

			/*
			 * 筛选本车企未报名的驾驶员
			 */
            String sql = "select * from tdriver sdy where 1=1 "
                    + "and sdy.id not in "
                    // 排除已经报名了该考试的驾驶员
                    + "(select driver_id from texam_signup where status<>:cancel and "
					+ "exam_id=:examId ) "
                    // 搜索
                    + "and (sdy.name like:key or sdy.sfz_no like:key or sdy.mobile like:key ) "
                    // 驾驶员状态不为删除
                    + "and sdy.status<>:delete "
                    // 属于该车企
                    + "and sdy.org_id=:orgId ";
            Map<String, Object> params = new HashMap<>();
            params.put("cancel", DriverExamEnum.CANCELED.getStatus());
            params.put("examId", examId);
            String key = "%" + pageBaseDtoForSignup.getSearchKeyForSignup()
                    + "%";
            params.put("key", key);
            params.put("delete", DriverStatusEnum.DELETED.getStatus());
            params.put("orgId", orgId);
			/*
			 * 添加驾驶员状态筛选信息
			 */
            sql += "and ( 1=0 ";
            if (AVAILABLE == 1) {
                sql += "or sdy.status=:avalable ";
                params.put("avalable", DriverStatusEnum.AVAILABLE.getStatus());
            }
            if (NEEDTTRAINED == 1) {
                sql += "or sdy.status=:needTrained ";
                params.put("needTrained",
                        DriverStatusEnum.NEEDTTRAINED.getStatus());
            }
            if (RENEEDTTRAINED == 1) {
                sql += "or sdy.status=:reneedTrained ";
                params.put("reneedTrained",
                        DriverStatusEnum.RENEEDTTRAINED.getStatus());
            }
            sql += ")";

            // 获取实体类
            List<Tdriver> tdrivers = driverDao2.findBySqlGetEntity(sql,
                    Tdriver.class, params,
                    pageBaseDtoForSignup.getPageForSignup(),
                    pageBaseDtoForSignup.getRowsForSignup());

			/*
			 * 转化成实体类
			 */
            for (Tdriver tdriver : tdrivers) {
                DriverInfoInExamManagement driverInfoInExamManagement = new
						DriverInfoInExamManagement();
                driverInfoInExamManagement.setDriverId(tdriver.getId());
                driverInfoInExamManagement.setTel(tdriver.getMobile());
                driverInfoInExamManagement.setSfzNo(tdriver.getSfzNo());
                driverInfoInExamManagement.setName(tdriver.getName());

                driverInfoInExamManagements.add(driverInfoInExamManagement);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return driverInfoInExamManagements;
    }

    @Override
    public PageBaseDtoForSignup getHavenotSignupDriversPageBaseDto(
            Integer examId, PageBaseDtoForSignup pageBaseDtoForSignup,
            Integer orgId, String signupStatus) throws RuntimeException {
        try {
            // 是否筛选一般驾驶员
            Integer AVAILABLE = 0;
            // 是否筛选首次培训驾驶员
            Integer NEEDTTRAINED = 0;
            // 是否筛选重新培训驾驶员
            Integer RENEEDTTRAINED = 0;

			/*
			 * 状态判断
			 */
            if (signupStatus == null) {
                NEEDTTRAINED = 1;
            } else {
                String[] driverStatus = signupStatus.split(";");
                for (int i = 0; i < driverStatus.length; i++) {
                    if (driverStatus[i].equals("-1")) {
                        // 筛选全部
                        AVAILABLE = 1;
                        NEEDTTRAINED = 1;
                        RENEEDTTRAINED = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.AVAILABLE.getStatus()
                                    .toString())) {
                        AVAILABLE = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.NEEDTTRAINED.getStatus()
                                    .toString())) {
                        NEEDTTRAINED = 1;
                    } else if (driverStatus[i]
                            .equals(DriverStatusEnum.RENEEDTTRAINED.getStatus()
                                    .toString())) {
                        RENEEDTTRAINED = 1;
                    }
                }
            }

			/*
			 * 筛选本车企未报名的驾驶员
			 */
            String sql = "select count(*) from tdriver sdy where 1=1 "
                    + "and sdy.id not in "
                    // 排除已经报名了该考试的驾驶员
                    + "(select driver_id from texam_signup where status<>:cancel and exam_id=:examId ) "
                    // 搜索
                    + "and (sdy.name like:key or sdy.sfz_no like:key or sdy.mobile like:key ) "
                    // 驾驶员状态不为删除
                    + "and sdy.status<>:delete "
                    // 属于该车企
                    + "and sdy.org_id=:orgId ";
            Map<String, Object> params = new HashMap<>();
            params.put("cancel", DriverExamEnum.CANCELED.getStatus());
            params.put("examId", examId);
            String key = "%" + pageBaseDtoForSignup.getSearchKeyForSignup()
                    + "%";
            params.put("key", key);
            params.put("delete", DriverStatusEnum.DELETED.getStatus());
            params.put("orgId", orgId);
			/*
			 * 添加驾驶员状态筛选信息
			 */
            sql += "and ( 1=0 ";
            if (AVAILABLE == 1) {
                sql += "or sdy.status=:avalable ";
                params.put("avalable", DriverStatusEnum.AVAILABLE.getStatus());
            }
            if (NEEDTTRAINED == 1) {
                sql += "or sdy.status=:needTrained ";
                params.put("needTrained",
                        DriverStatusEnum.NEEDTTRAINED.getStatus());
            }
            if (RENEEDTTRAINED == 1) {
                sql += "or sdy.status=:reneedTrained ";
                params.put("reneedTrained",
                        DriverStatusEnum.RENEEDTTRAINED.getStatus());
            }
            sql += ")";

            BigInteger sumBigInt = driverDao2.countBySQL(sql, params);
            String sumStr = sumBigInt.toString();
            Long sum = Long.parseLong(sumStr);
            pageBaseDtoForSignup.setSumForSignup(sum);
            pageBaseDtoForSignup.setPageNum(
                    pageBaseDtoForSignup.getRowsForSignup(), sum);

            return pageBaseDtoForSignup;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getCompanyName(Integer orgId) throws RuntimeException {
        try {
            String hql = "from Torganization sdy where sdy.id='" + orgId + "'";
            Torganization torganization = organizationDao.get(hql);
            if (torganization == null
                    || torganization.getType() != OrganizationTypeEnum.COMPANY
                    .getType()) {
                throw new RuntimeException("不为车企");
            }
            return torganization.getName();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
