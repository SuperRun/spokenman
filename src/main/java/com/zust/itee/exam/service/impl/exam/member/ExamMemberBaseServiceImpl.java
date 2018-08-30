package com.zust.itee.exam.service.impl.exam.member;

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
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.enums.exam.ExamTypeEnum;
import com.zust.itee.exam.service.exam.member.ExamMemberBaseService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;

@Service
@Transactional
public class ExamMemberBaseServiceImpl extends BaseServiceImpl<Texam> implements
        ExamMemberBaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<Tmember> memberDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Override
    public List<ExamExposer> getMemberExamHome(int memberId,
            PageBaseDto pageBaseDto) throws RuntimeException {
        List<ExamExposer> examExposers = new ArrayList<ExamExposer>();
        try {
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember tmember = memberDao.get(hql);
            hql = "from Texam sdy where 1=1 "
                    +
                    // 只筛选正式考试
                    "and sdy.type<>:practiceType "
                    //只筛选自己组织发起的考试
                    + "and sdy.torganization.id=:orgId "
                    // 搜索筛选
                    + "and (sdy.name like:key "
                    + "or sdy.description like:key "
                    + "or sdy.torganization.name like:key ) ";

            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("orgId", tmember.getTorganization().getId());
            params.put("key", key);
            params.put("practiceType", ExamTypeEnum.PRACTICE.getId());

            java.sql.Date nowSQL = new java.sql.Date(new Date().getTime());
            if (pageBaseDto.getStatus() == ExamStatusEnum.RELEASE.getState()) {
                // 报名前
                hql += "and sdy.signupStartTime>:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.SIGNUP
                    .getState()) {
                // 报名中
                hql += "and sdy.signupStartTime<=:now "
                        + "and sdy.signupEndTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.WAITINGEXAM
                    .getState()) {
                // 待考试
                hql += "and sdy.signupEndTime<=:now and sdy.examStartTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.EXAMING
                    .getState()) {
                // 考试中
                hql += "and sdy.examStartTime<=:now "
                        + "and sdy.examEndTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.MARKING
                    .getState()) {
                // 阅卷中(线下考试结束之后)
                hql += "and (sdy .status=:status "
                        + "or (sdy.type=:offlineType "
                        + "and sdy.examEndTime<=:now "
                        + "and sdy.status<>:certificateStatus "
                        + "and sdy.status<>:finnishStatus ) )";
                params.put("status", ExamStatusEnum.MARKING.getState());
                params.put("offlineType", ExamTypeEnum.OFFICIALOFFLINE.getId());
                params.put("now", nowSQL);
                params.put("certificateStatus",
                        ExamStatusEnum.CERTIFICATING.getState());
                params.put("finnishStatus", ExamStatusEnum.FINISHED.getState());
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.CERTIFICATING
                    .getState()) {
                // 证书录入中(线上考试结束之后，以及线下考试成绩录入后)
                hql += "and (sdy.status=:status "
                        + "or (sdy.type=:onlineType and sdy.examEndTime<=:now and sdy"
                        + ".status<>:finishStatus) ) ";
                params.put("status", ExamStatusEnum.CERTIFICATING.getState());
                params.put("onlineType", ExamTypeEnum.OFFICIALONLINE.getId());
                params.put("finishStatus", ExamStatusEnum.FINISHED.getState());
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.FINISHED
                    .getState()) {
                // 已完成
                hql += "and sdy.status=:status ";
                params.put("status", ExamStatusEnum.FINISHED.getState());
            } else {
                // 全部
                hql += "and sdy.status<>-1 ";
            }

            hql += "order by sdy.createTime desc";
			/*
			 * 获取entity
			 */
            List<Texam> texams = examDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            Date now = new Date();
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
                //添加是否设置公告状态
                if (texam.getTannouncement() != null) {
                    examExposer.setAnnouncementId(texam.getTannouncement().getId());
                    examExposer.setHasAnnouncement(true);
                } else {
                    examExposer.setHasAnnouncement(false);
                }
                examExposers.add(examExposer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return examExposers;
    }

    @Override
    public PageBaseDto getMemberExamPageBaseDto(PageBaseDto pageBaseDto, Integer memberId)
            throws RuntimeException {
        PageBaseDto res = new PageBaseDto();
        try {
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember tmember = memberDao.get(hql);
            hql = "select count (*) from Texam sdy where 1=1 "
                    +
                    // 只筛选正式考试
                    "and sdy.type<>:practiceType "
                    //只筛选自己组织发起的考试
                    + "and sdy.torganization.id=:orgId "
                    // 搜索筛选
                    + "and (sdy.name like:key "
                    + "or sdy.description like:key "
                    + "or sdy.torganization.name like:key ) ";

            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("orgId", tmember.getTorganization().getId());
            params.put("key", key);
            params.put("practiceType", ExamTypeEnum.PRACTICE.getId());

            java.sql.Date nowSQL = new java.sql.Date(new Date().getTime());
            if (pageBaseDto.getStatus() == ExamStatusEnum.RELEASE.getState()) {
                // 报名前
                hql += "and sdy.signupStartTime>:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.SIGNUP
                    .getState()) {
                // 报名中
                hql += "and sdy.signupStartTime<=:now "
                        + "and sdy.signupEndTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.WAITINGEXAM
                    .getState()) {
                // 待考试
                hql += "and sdy.signupEndTime<=:now and sdy.examStartTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.EXAMING
                    .getState()) {
                // 考试中
                hql += "and sdy.examStartTime<=:now "
                        + "and sdy.examEndTime>=:now ";
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.MARKING
                    .getState()) {
                // 阅卷中(线下考试结束之后)
                hql += "and (sdy .status=:status "
                        + "or (sdy.type=:offlineType "
                        + "and sdy.examEndTime<=:now "
                        + "and sdy.status<>:certificateStatus "
                        + "and sdy.status<>:finnishStatus ) )";
                params.put("status", ExamStatusEnum.MARKING.getState());
                params.put("offlineType", ExamTypeEnum.OFFICIALOFFLINE.getId());
                params.put("now", nowSQL);
                params.put("certificateStatus",
                        ExamStatusEnum.CERTIFICATING.getState());
                params.put("finnishStatus", ExamStatusEnum.FINISHED.getState());
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.CERTIFICATING
                    .getState()) {
                // 证书录入中(线上考试结束之后，以及线下考试成绩录入后)
                hql += "and (sdy.status=:status "
                        + "or (sdy.type=:onlineType and sdy.examEndTime<=:now and sdy.status<>:finishStatus) ) ";
                params.put("status", ExamStatusEnum.CERTIFICATING.getState());
                params.put("onlineType", ExamTypeEnum.OFFICIALONLINE.getId());
                params.put("finishStatus", ExamStatusEnum.FINISHED.getState());
                params.put("now", nowSQL);
            } else if (pageBaseDto.getStatus() == ExamStatusEnum.FINISHED
                    .getState()) {
                // 已完成
                hql += "and sdy.status=:status ";
                params.put("status", ExamStatusEnum.FINISHED.getState());
            } else {
                // 全部
                hql += "and sdy.status<>-1 ";
            }

            hql += "order by sdy.createTime desc";

            long sum = examDao.count(hql, params);

            int pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                    / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);

            res.setPage(pageBaseDto.getPage());
            res.setPageNum(pageNum);
            res.setRows(pageBaseDto.getRows());
            res.setSearchKey(pageBaseDto.getSearchKey());
            res.setStatus(pageBaseDto.getStatus());
            res.setSum(sum);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return res;
    }
}
