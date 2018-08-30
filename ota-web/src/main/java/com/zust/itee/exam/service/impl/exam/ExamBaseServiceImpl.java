package com.zust.itee.exam.service.impl.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.exam.DriverDetailInfo;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.enums.exam.ExamExposerEnum;
import com.zust.itee.exam.service.ExamBaseService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;
import com.zust.itee.exam.util.MyDateUtil;

@Service
@Transactional
public class ExamBaseServiceImpl extends BaseServiceImpl<Texam> implements
        ExamBaseService {

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<Tquestion> questionDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<DataDictionary> dataDictionaryDao;

    @Override
    public ExamExposer getexamDetail(Integer examId) {
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        Texam texam = examDao.get(hql);
        ExamExposer examExposer = new ExamExposer(
                ExamExposerEnum.INSIGNUP.getState(),
                ExamExposerEnum.INSIGNUP.getStateInfo(), texam, examId);
        return examExposer;
    }

    @Override
    public DriverDetailInfo getDriverDetailInfo(Integer driverId)
            throws RuntimeException {
        DriverDetailInfo detailInfo = new DriverDetailInfo();
        try {
            String hql = "from Tdriver sdy where sdy.id='" + driverId + "'";
            Tdriver tdriver = driverDao.get(hql);
            detailInfo = transTdriverToDriverDetailInfo(tdriver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return detailInfo;
    }

    @Override
    public DriverDetailInfo transTdriverToDriverDetailInfo(Tdriver tdriver) {
        DriverDetailInfo detailInfo = new DriverDetailInfo();
        int age;
        if (tdriver.getBirth() == null) {
            age = 0;
        } else {
            age = MyDateUtil.countAge(tdriver.getBirth());
        }
        String sex = "女";
        if (tdriver.getGender() == 1) {
            sex = "男";
        }
        detailInfo.setAddress(tdriver.getAddress());
        detailInfo.setAge(age);
        detailInfo.setDriverId(tdriver.getId());
        detailInfo.setName(tdriver.getName());
        detailInfo.setSex(sex);
        detailInfo.setTel(tdriver.getMobile());
        return detailInfo;
    }
}
