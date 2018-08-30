package com.zust.itee.exam.service.impl.companyQuery.exam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dao.hibernate.BaseDao2;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverExamDetailInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.DriverListInfoInCompanyQuery;
import com.zust.itee.exam.dto.exam.member.companyQuery.ExamDetailInCompanyQuery;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.entity.Tpaper;
import com.zust.itee.exam.entity.TpaperDesign;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamPartEntityStatusEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.enums.exam.ExamTypeEnum;
import com.zust.itee.exam.service.companyQuery.exam.CompanyExamQueryService;
import com.zust.itee.exam.util.MyDateUtil;

@Service
@Transactional
public class CompanyExamQueryServiceImpl implements CompanyExamQueryService {

    @Autowired
    BaseDao<Texam> examDao;

    @Autowired
    BaseDao2<Texam> examDao2;

    @Autowired
    BaseDao<Tmember> memberDao;

    @Autowired
    BaseDao<Torganization> organizationDao;

    @Autowired
    BaseDao<Tquestion> questionDao;

    @Autowired
    BaseDao<DataDictionary> dataDictionaryDao;

    @Autowired
    BaseDao<TexamSignup> examSignupDao;

    @Override
    public List<ExamExposer> getAllExams(Integer memberId,
            PageBaseDto pageBaseDto) {
        List<ExamExposer> examExposers = new ArrayList<>();
        try {
            // 获取工作人员
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember tmember = memberDao.get(hql);

            Torganization torganization = tmember.getTorganization();
            if (torganization.getType() != OrganizationTypeEnum.COMPANY
                    .getType()) {
                throw new RuntimeException("组织不为车企！");
            }
            List<Torganization> parentOrganizations = new ArrayList<>();
            parentOrganizations.add(torganization.getTorganization());
            /*
             * 递归查找父级组织
			 */
            // 当前下标
            int i = 0;
            // 当前父级组织个数
            int sum = 1;
            for (i = 0; i < sum; i++) {
                Torganization orgNow = parentOrganizations.get(i);
                if (orgNow.getTorganization() != null) {
                    parentOrganizations.add(orgNow.getTorganization());
                    sum++;
                }
            }

			/*
			 * 获取所有父级组织发布的考试
			 */
            String inSQL = "(";
            for (i = 0; i < parentOrganizations.size(); i++) {
                Torganization org = parentOrganizations.get(i);
                if (i == 0) {
                    inSQL += "'" + org.getId() + "'";
                } else {
                    inSQL += ",'" + org.getId() + "'";
                }
            }
            inSQL += ")";

            Date now = new Date();
            java.sql.Date nowSQL = new java.sql.Date(now.getTime());
            String sql = "select * from texam sdy where 1=1 "
                    // 状态不为删除
                    + "and sdy.status<>:cancel "
                    // 类型不为模拟考试
                    + "and sdy.type<>:type "
                    // 搜索
                    + "and sdy.name like:key "
                    // 由父级组织发起
                    + "and sdy.org_id in " + inSQL + " ";
            Map<String, Object> params = new HashMap<>();
            params.put("cancel", ExamStatusEnum.CANCELED.getState());
            params.put("type", ExamTypeEnum.PRACTICE.getId());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);
            // params.put("inSQL", inSQL);

            if (pageBaseDto.getStatus() == null
                    || pageBaseDto.getStatus() == -1) {
                // 筛选全部
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.RELEASE
                    .getState()) {
                // 已发布的考试
                // (当前时间小于报名开始时间)
                sql += "and sdy.signup_start_time>=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.SIGNUP
                    .getState()) {
                // 报名中考试
                // （当前时间在报名时间之间）
                sql += "and sdy.signup_start_time<:nowSQL and sdy.signup_end_time >=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.WAITINGEXAM
                    .getState()) {
                // 待考试
                sql += "and sdy.exam_start_time >:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.EXAMING
                    .getState()) {
                // 考试中
                sql += "and sdy.exam_start_time<=:nowSQL and sdy.exam_end_time >=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.MARKING
                    .getState()) {
                // 阅卷中
                // 类型是线下考试，状态不为证书录入以及已完成
                sql += "and sdy.type=:offline and sdy.exam_end_time <:nowSQL "
                        + "and sdy.status<>:certificate and sdy.status<>:finish ";
                params.put("offline", ExamTypeEnum.OFFICIALOFFLINE.getId());
                params.put("nowSQL", nowSQL);
                params.put("certificate",
                        ExamStatusEnum.CERTIFICATING.getState());
                params.put("finish", ExamStatusEnum.FINISHED.getState());
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.CERTIFICATING
                    .getState()) {
                // 证书录入中
                // (线下考试之后)
                sql += "and (sdy.status=:status "
                        + "or (sdy.type=:onlineType and sdy.exam_end_time<=:now and sdy"
                        + ".status<>:finishStatus) ) ";
                params.put("status", ExamStatusEnum.CERTIFICATING.getState());
                params.put("onlineType", ExamTypeEnum.OFFICIALONLINE.getId());
                params.put("finishStatus", ExamStatusEnum.FINISHED.getState());
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.FINISHED
                    .getState()) {
                // 已完成
                sql += "and sdy.status=:status ";
                params.put("status", ExamStatusEnum.FINISHED.getState());
            }

            // 获取实体类
            List<Texam> texams = examDao2.findBySqlGetEntity(sql, Texam.class,
                    params, pageBaseDto.getPage(), pageBaseDto.getRows());

			/*
			 * 转化为dto
			 */
            for (Texam texam : texams) {
                ExamExposer examExposer = new ExamExposer();
                if (now.before(texam.getSignupStartTime())) {
                    // 报名前
                    examExposer = new ExamExposer(
                            ExamStatusEnum.RELEASE.getState(),
                            ExamStatusEnum.RELEASE.getStateInfo(), texam,
                            texam.getId());
                } else if (now.before(texam.getSignupEndTime())) {
                    // 报名中
                    examExposer = new ExamExposer(
                            ExamStatusEnum.SIGNUP.getState(),
                            ExamStatusEnum.SIGNUP.getStateInfo(), texam,
                            texam.getId());
                } else if (now.before(texam.getExamStartTime())) {
                    // 待考试
                    examExposer = new ExamExposer(
                            ExamStatusEnum.WAITINGEXAM.getState(),
                            ExamStatusEnum.WAITINGEXAM.getStateInfo(), texam,
                            texam.getId());
                } else if (now.before(texam.getExamEndTime())) {
                    // 考试中
                    examExposer = new ExamExposer(
                            ExamStatusEnum.EXAMING.getState(),
                            ExamStatusEnum.EXAMING.getStateInfo(), texam,
                            texam.getId());
                } else if (texam.getStatus() == ExamStatusEnum.MARKING
                        .getState()
                        || (texam.getType() == ExamTypeEnum.OFFICIALOFFLINE
                        .getId()
                        && now.after(texam.getExamEndTime())
                        && texam.getStatus() != ExamStatusEnum.CERTIFICATING
                        .getState() && texam.getStatus() != ExamStatusEnum.FINISHED
                        .getState())) {
                    // 阅卷中(线下考试中考试结束的)
                    examExposer = new ExamExposer(
                            ExamStatusEnum.MARKING.getState(),
                            ExamStatusEnum.MARKING.getStateInfo(), texam,
                            texam.getId());
                } else if (texam.getStatus() == ExamStatusEnum.CERTIFICATING
                        .getState()
                        || (texam.getType() == ExamTypeEnum.OFFICIALONLINE
                        .getId() && now.after(texam.getExamEndTime()) && texam
                        .getStatus() != ExamStatusEnum.FINISHED
                        .getState())) {
                    // 证书录入中
                    examExposer = new ExamExposer(
                            ExamStatusEnum.CERTIFICATING.getState(),
                            ExamStatusEnum.CERTIFICATING.getStateInfo(), texam,
                            texam.getId());
                } else {
                    examExposer = new ExamExposer(
                            ExamStatusEnum.FINISHED.getState(),
                            ExamStatusEnum.FINISHED.getStateInfo(), texam,
                            texam.getId());
                }
                examExposer.setExamId(texam.getId());
                examExposers.add(examExposer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return examExposers;
    }

    @Override
    public PageBaseDto getAllExamsPageBaseDto(Integer memberId,
            PageBaseDto pageBaseDto) {
        try {
            // 获取工作人员
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember tmember = memberDao.get(hql);

            Torganization torganization = tmember.getTorganization();
            if (torganization.getType() != OrganizationTypeEnum.COMPANY
                    .getType()) {
                throw new RuntimeException("组织不为车企！");
            }
            List<Torganization> parentOrganizations = new ArrayList<>();
            parentOrganizations.add(torganization.getTorganization());
			/*
			 * 递归查找父级组织
			 */
            // 当前下标
            int i = 0;
            // 当前父级组织个数
            int sum = 1;
            for (i = 0; i < sum; i++) {
                Torganization orgNow = parentOrganizations.get(i);
                if (orgNow.getTorganization() != null) {
                    parentOrganizations.add(orgNow.getTorganization());
                    sum++;
                }
            }

			/*
			 * 获取所有父级组织发布的考试
			 */
            String inSQL = "(";
            for (i = 0; i < parentOrganizations.size(); i++) {
                Torganization org = parentOrganizations.get(i);
                if (i == 0) {
                    inSQL += "'" + org.getId() + "'";
                } else {
                    inSQL += ",'" + org.getId() + "'";
                }
            }
            inSQL += ")";

            Date now = new Date();
            java.sql.Date nowSQL = new java.sql.Date(now.getTime());
            String sql = "select count(*) from texam sdy where 1=1 "
                    // 状态不为删除
                    + "and sdy.status<>:status "
                    // 类型不为模拟考试
                    + "and sdy.type<>:type "
                    // 搜索
                    + "and sdy.name like:key "
                    // 由父级组织发起
                    + "and sdy.org_id in " + inSQL + " ";
            Map<String, Object> params = new HashMap<>();
            params.put("status", ExamStatusEnum.CANCELED.getState());
            params.put("type", ExamTypeEnum.PRACTICE.getId());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);
            // params.put("inSQL", inSQL);

            if (pageBaseDto.getStatus() == null
                    || pageBaseDto.getStatus() == -1) {
                // 筛选全部
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.RELEASE
                    .getState()) {
                // 已发布的考试
                // (当前时间小于报名开始时间)
                sql += "and sdy.signup_start_time>=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.SIGNUP
                    .getState()) {
                // 报名中考试
                // （当前时间在报名时间之间）
                sql += "and sdy.signup_start_time<:nowSQL and sdy.signup_end_time >=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.WAITINGEXAM
                    .getState()) {
                // 待考试
                sql += "and sdy.exam_start_time >:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.EXAMING
                    .getState()) {
                // 考试中
                sql += "and sdy.exam_start_time<=:nowSQL and sdy.exam_end_time >=:nowSQL ";
                params.put("nowSQL", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.MARKING
                    .getState()) {
                // 阅卷中
                // 类型是线下考试，状态不为证书录入以及已完成
                sql += "and sdy.type=:offline and sdy.exam_end_time <:nowSQL "
                        + "and sdy.status<>:certificate and sdy.status<>:finish ";
                params.put("offline", ExamTypeEnum.OFFICIALOFFLINE.getId());
                params.put("nowSQL", nowSQL);
                params.put("certificate",
                        ExamStatusEnum.CERTIFICATING.getState());
                params.put("finish", ExamStatusEnum.FINISHED.getState());
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.CERTIFICATING
                    .getState()) {
                // 证书录入中
                // (线下考试之后)
                sql += "and (sdy.status=:status "
                        + "or (sdy.type=:onlineType and sdy.exam_end_time<=:now and sdy"
                        + ".status<>:finishStatus) ) ";
                params.put("status", ExamStatusEnum.CERTIFICATING.getState());
                params.put("onlineType", ExamTypeEnum.OFFICIALONLINE.getId());
                params.put("finishStatus", ExamStatusEnum.FINISHED.getState());
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.FINISHED
                    .getState()) {
                // 已完成
                sql += "and sdy.status=:status ";
                params.put("status", ExamStatusEnum.FINISHED.getState());
            }

            BigInteger resultSumBigInt = examDao2.countBySQL(sql, params);
            String resultSumString = resultSumBigInt.toString();
            Long resultSum = Long.parseLong(resultSumString);
            pageBaseDto.setSum(resultSum);
            pageBaseDto.setPageNum(pageBaseDto.getRows(), resultSum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ExamDetailInCompanyQuery getExamDetail(Integer examId) {
        ExamDetailInCompanyQuery examDetailInCompanyQuery = new ExamDetailInCompanyQuery();
        try {
            // 获取考试、试卷
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            Tpaper tpaper = texam.getTpaper();
            Set<TpaperDesign> tpaperDesigns = tpaper.getTpaperDesigns();
            Double score = 0.0;
            Integer sum = 0;
            // 遍历组卷信息，获取总分、总题目数
            for (TpaperDesign tpaperDesign : tpaperDesigns) {
                hql = "from Tquestion sdy where 1=1 "
                        + "and sdy.subjectId=:subjectId "
                        + "and sdy.typeId=:typeId "
                        + "and sdy.status<>:status ";
                Map<String, Object> params = new HashMap<>();
                params.put("subjectId", tpaperDesign.getQuestionSubjectId());
                params.put("typeId", tpaperDesign.getQuestionTypeId());
                params.put("status",
                        ExamPartEntityStatusEnum.CANCELED.getState());
                List<Tquestion> tquestions = questionDao
                        .find(hql, params, 1, 1);
                Tquestion tquestion = tquestions.get(0);
                score += (tquestion.getMarks() * tpaperDesign.getNum());
                sum += tpaperDesign.getNum();
            }

			/*
			 * DTO赋值
			 */
            examDetailInCompanyQuery.setDescription(texam.getDescription());
            examDetailInCompanyQuery.setEndTime(MyDateUtil
                    .dateToAjaxString(texam.getExamEndTime()));
            examDetailInCompanyQuery.setId(texam.getId());
            examDetailInCompanyQuery.setName(texam.getName());
            examDetailInCompanyQuery.setOrg(texam.getTorganization().getName());
            examDetailInCompanyQuery.setPaperName(tpaper.getName());
            examDetailInCompanyQuery.setPassNum(tpaper.getPassNum());
            examDetailInCompanyQuery.setPassScore(tpaper.getPassScore());
            examDetailInCompanyQuery.setScore(score);
            examDetailInCompanyQuery.setSignupEndTime(MyDateUtil
                    .dateToAjaxString(texam.getSignupEndTime()));
            examDetailInCompanyQuery.setSignupStartTime(MyDateUtil
                    .dateToAjaxString(texam.getSignupStartTime()));
            examDetailInCompanyQuery.setStartTime(MyDateUtil
                    .dateToAjaxString(texam.getExamStartTime()));
            examDetailInCompanyQuery.setSum(sum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return examDetailInCompanyQuery;
    }

    @Override
    public List<DriverListInfoInCompanyQuery> getAllDrivers(Integer examId,
            Integer memberId, PageBaseDto pageBaseDto) throws RuntimeException {
        List<DriverListInfoInCompanyQuery> drivers = new ArrayList<>();
        try {
            // 获取该车企
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember member = memberDao.get(hql);
            Torganization torganization = member.getTorganization();
            if (torganization.getType() != OrganizationTypeEnum.COMPANY
                    .getType()) {
                throw new RuntimeException("组织不为车企");
            }
			/*
			 * 获取报名了该考试的驾驶员
			 */
            hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.status<>:status "
                    + "and sdy.tdriver.torganization.id=:orgId "
                    + "and (sdy.tdriver.name like:key or sdy.tdriver.sfzNo like:key or sdy"
                    + ".tdriver.mobile like:key )";
            Map<String, Object> params = new HashMap<>();
            params.put("examId", examId);
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("orgId", torganization.getId());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);
            List<TexamSignup> texamSignups = examSignupDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());

			/*
			 * 赋值dto
			 */
            for (TexamSignup texamSignup : texamSignups) {
                DriverListInfoInCompanyQuery driverListInfoInCompanyQuery = new
                        DriverListInfoInCompanyQuery();
                Tdriver tdriver = texamSignup.getTdriver();
                driverListInfoInCompanyQuery.setId(tdriver.getId());
                driverListInfoInCompanyQuery.setName(tdriver.getName());
                driverListInfoInCompanyQuery.setSfzNo(tdriver.getSfzNo());
                driverListInfoInCompanyQuery.setTel(tdriver.getMobile());
                drivers.add(driverListInfoInCompanyQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return drivers;
    }

    @Override
    public PageBaseDto getAllDriversPageBaseDto(Integer examId,
            Integer memberId, PageBaseDto pageBaseDto) throws RuntimeException {
        try {
            // 获取该车企
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember member = memberDao.get(hql);
            Torganization torganization = member.getTorganization();
            if (torganization.getType() != OrganizationTypeEnum.COMPANY
                    .getType()) {
                throw new RuntimeException("组织不为车企");
            }
			/*
			 * 获取报名了该考试的驾驶员
			 */
            hql = "select count(*) from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.status<>:status "
                    + "and sdy.tdriver.torganization.id=:orgId "
                    + "and (sdy.tdriver.name like:key or sdy.tdriver.sfzNo like:key or sdy"
                    + ".tdriver.mobile like:key )";
            Map<String, Object> params = new HashMap<>();
            params.put("examId", examId);
            params.put("status", DriverExamEnum.CANCELED.getStatus());
            params.put("orgId", torganization.getId());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);

            Long sum = examSignupDao.count(hql, params);
            pageBaseDto.setSum(sum);
            pageBaseDto.setPageNum(pageBaseDto.getRows(), sum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DriverExamDetailInfoInCompanyQuery getDriverExamDetail(
            Integer examId, Integer driverId) {
        DriverExamDetailInfoInCompanyQuery driverExamDetailInfoInCompanyQuery = new
                DriverExamDetailInfoInCompanyQuery();
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId ";
            Map<String, Object> params = new HashMap<>();
            params.put("examId", examId);
            params.put("driverId", driverId);
            TexamSignup texamSignup = examSignupDao.get(hql, params);
            if (texamSignup == null
                    || texamSignup.getStatus() == DriverExamEnum.CANCELED
                    .getStatus()) {
                throw new RuntimeException("无报名信息");
            }
            Tpaper tpaper = texamSignup.getTexam().getTpaper();
            if (tpaper == null) {
                throw new RuntimeException("试卷不存在");
            }
            Boolean ifPass = false;
            if (texamSignup.getFinalScore() != null
                    && tpaper.getPassScore() != null
                    && texamSignup.getFinalScore() >= tpaper.getPassScore()) {
                ifPass = true;
            }
            if (texamSignup.getFinalNum() != null
                    && tpaper.getPassNum() != null
                    && texamSignup.getFinalNum() >= tpaper.getPassNum()) {
                ifPass = true;
            }
            String takeExamStatus = "未参加";
            if (texamSignup.getTakeExamTime() != null) {
                takeExamStatus = "已参加";
            }
            driverExamDetailInfoInCompanyQuery.setCertificate(texamSignup
                    .getCertificatePhoto());
            driverExamDetailInfoInCompanyQuery.setCertificateNo(texamSignup
                    .getCertificateNo());
            driverExamDetailInfoInCompanyQuery.setId(texamSignup.getTdriver()
                    .getId());
            driverExamDetailInfoInCompanyQuery.setIfPass(ifPass);
            driverExamDetailInfoInCompanyQuery
                    .setNum(texamSignup.getFinalNum());
            driverExamDetailInfoInCompanyQuery.setScore(texamSignup
                    .getFinalScore());
            driverExamDetailInfoInCompanyQuery.setSignupInfo("已报名");
            driverExamDetailInfoInCompanyQuery.setSignupTime(MyDateUtil
                    .date2strMDHM(texamSignup.getSignupTime()));
            driverExamDetailInfoInCompanyQuery
                    .setTakeExamStatus(takeExamStatus);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return driverExamDetailInfoInCompanyQuery;
    }
}
