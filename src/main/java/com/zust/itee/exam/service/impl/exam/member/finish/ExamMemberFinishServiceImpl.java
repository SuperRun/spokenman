package com.zust.itee.exam.service.impl.exam.member.finish;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.ExamFinishInfo;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.exam.member.finish.ExamMemberFinishService;
import com.zust.itee.exam.util.MyDateUtil;

@Service
@Transactional
public class ExamMemberFinishServiceImpl implements ExamMemberFinishService {

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Override
    public ExamFinishInfo getFinishExamInfo(Integer examId) {
        try {
            ExamFinishInfo examFinishInfo = new ExamFinishInfo();
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            if (exam.getStatus() != ExamStatusEnum.FINISHED.getState()) {
                throw new RuntimeException("考试未完成");
            }
            Set<TexamSignup> examSignups = exam.getTexamSignups();
            List<DriverInfoInExamManagement> signupDrivers = new ArrayList<>();
            List<DriverInfoInExamManagement> presentDrivers = new
                    ArrayList<DriverInfoInExamManagement>();
            List<DriverInfoInExamManagement> passDrivers = new
					ArrayList<DriverInfoInExamManagement>();
            for (TexamSignup texamSignup : examSignups) {
				/*
				 * 获取参加考试人员
				 */
                DriverInfoInExamManagement signupDriver = transTdriverToDriverInfoInFinishExam(
                        texamSignup);
                signupDrivers.add(signupDriver);
                if (texamSignup.getTakeExamTime() != null) {
                    // 参加过考试
                    DriverInfoInExamManagement driverInfoInExamManagement =
							transTdriverToDriverInfoInFinishExam(
                            texamSignup);
                    presentDrivers.add(driverInfoInExamManagement);
					/*
					 * 通过人员
					 */
                    if ((texamSignup.getTexam().getTpaper().getPassNum() == null && texamSignup
                            .getFinalScore() >= texamSignup.getTexam()
                            .getTpaper().getPassScore())
                            || (texamSignup.getTexam().getTpaper()
                            .getPassScore() == null && texamSignup
                            .getFinalNum() >= texamSignup.getTexam()
                            .getTpaper().getPassNum())) {
                        DriverInfoInExamManagement driverInfoInExamManagement2 =
								transTdriverToDriverInfoInFinishExam(
                                texamSignup);
                        passDrivers.add(driverInfoInExamManagement2);
                    }
                }
            }

            examFinishInfo.setPresentDrivers(presentDrivers);
            examFinishInfo.setPassDrivers(passDrivers);
            examFinishInfo.setCreateTime(MyDateUtil.date2strMD(exam
                    .getCreateTime()));
            examFinishInfo.setExamId(examId);
            examFinishInfo.setExamName(exam.getName());
            examFinishInfo.setOrganization(exam.getTorganization().getName());
            examFinishInfo.setPassNum(passDrivers.size());
            examFinishInfo.setPresentNum(presentDrivers.size());
            examFinishInfo.setSignupNum(examSignups.size());
            examFinishInfo.setSignupDrivers(signupDrivers);
            return examFinishInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DriverInfoInExamManagement transTdriverToDriverInfoInFinishExam(
            TexamSignup texamSignup) {
        DriverInfoInExamManagement driverInfoInExamManagement = new DriverInfoInExamManagement();
        Tdriver tdriver = texamSignup.getTdriver();
        driverInfoInExamManagement.setName(tdriver.getName());
        driverInfoInExamManagement.setTel(tdriver.getMobile());
        driverInfoInExamManagement.setDriverId(tdriver.getId());
        driverInfoInExamManagement.setOrgName(tdriver.getTorganization()
                .getName());
        driverInfoInExamManagement.setPresentTime(MyDateUtil
                .dateToAjaxString(texamSignup.getTakeExamTime()));
        // 报名时间
        driverInfoInExamManagement.setSignupTime(MyDateUtil
                .dateToAjaxString(texamSignup.getSignupTime()));
        if (texamSignup.getTakeExamTime() != null) {
            // 分数
            driverInfoInExamManagement.setScore(texamSignup.getFinalScore());
        }
        if (texamSignup.getCertificateNo() != null) {
            // 证书号
            driverInfoInExamManagement.setCertificateNo(texamSignup
                    .getCertificateNo());
        }
        return driverInfoInExamManagement;
    }
}
