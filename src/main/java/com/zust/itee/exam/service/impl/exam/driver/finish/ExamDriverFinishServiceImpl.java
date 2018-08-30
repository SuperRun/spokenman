package com.zust.itee.exam.service.impl.exam.driver.finish;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.exam.driver.ExamFinishDriverInfo;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.service.exam.driver.finish.ExamDriverFinishService;

@Service
@Transactional
public class ExamDriverFinishServiceImpl implements ExamDriverFinishService {

    @Autowired
    BaseDao<TexamSignup> examSignupDao;

    @Autowired
    BaseDao<Tdriver> driverDao;

    @Override
    public ExamFinishDriverInfo getExamFinishInfo(Integer driverId,
            Integer examId) throws RuntimeException {
        try {
            String hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            params.put("driverId", driverId);
            TexamSignup texamSignup = examSignupDao.get(hql, params);
            ExamFinishDriverInfo examFinishDriverInfo = transTexamSignupToDriverInfo(texamSignup);
            return examFinishDriverInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ExamFinishDriverInfo transTexamSignupToDriverInfo(
            TexamSignup texamSignup) throws RuntimeException {
        ExamFinishDriverInfo examFinishDriverInfo = new ExamFinishDriverInfo();
        try {
            Tdriver driver = texamSignup.getTdriver();
            Texam exam = texamSignup.getTexam();
            examFinishDriverInfo.setCertificateNo(texamSignup
                    .getCertificateNo());
            examFinishDriverInfo.setCertificatePhoto(texamSignup
                    .getCertificatePhoto());
            examFinishDriverInfo.setDriverId(driver.getId());
            examFinishDriverInfo.setDriverName(driver.getName());
            examFinishDriverInfo.setExamName(exam.getName());
            examFinishDriverInfo.setFinalNum(texamSignup.getFinalNum());
            examFinishDriverInfo.setFinalScore(texamSignup.getFinalScore());
            examFinishDriverInfo.setOrgName(exam.getTorganization().getName());
            examFinishDriverInfo.setPassNum(exam.getTpaper().getPassNum());
            examFinishDriverInfo.setPassScore(exam.getTpaper().getPassScore());
            return examFinishDriverInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
