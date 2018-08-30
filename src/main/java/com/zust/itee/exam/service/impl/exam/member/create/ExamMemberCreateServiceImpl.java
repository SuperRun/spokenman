package com.zust.itee.exam.service.impl.exam.member.create;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.exam.member.OrganizeExam;
import com.zust.itee.exam.entity.Tannouncement;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.entity.Tpaper;
import com.zust.itee.exam.enums.exam.ExamPartEntityStatusEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.service.exam.member.create.ExamMemberCreateService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;
import com.zust.itee.exam.util.MyDateUtil;

@Service
@Transactional
public class ExamMemberCreateServiceImpl extends BaseServiceImpl<Texam>
        implements ExamMemberCreateService {

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
    private BaseDao<Tannouncement> announcementDao;

    @Override
    public OrganizeExam getExamInfo(Integer examId) throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            OrganizeExam organizeExam = new OrganizeExam(texam);
            return organizeExam;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int organizeExam(int memberId, OrganizeExam organizeExam)
            throws RuntimeException {
        try {
            /*
			 * 获取管理员所在组织id
			 */

            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember member = memberDao.get(hql);
            Torganization torganization = member.getTorganization();

			/*
			 * 如果已有考试获取试卷
			 */
            Date creatTime = new Date();
            if (organizeExam.getExamId() != null) {
                hql = "from Texam sdy where sdy.id='"
                        + organizeExam.getExamId() + "'";
                Texam texam = examDao.get(hql);
                texam.setDescription(organizeExam.getExamDescription());
                texam.setDuration(organizeExam.getDuration());
                texam.setExamEndTime(MyDateUtil.str2dateAjax(organizeExam
                        .getExamEndTime()));
                texam.setExamStartTime(MyDateUtil.str2dateAjax(organizeExam
                        .getExamStartTime()));
                texam.setName(organizeExam.getExamName());
                texam.setSignupEndTime(MyDateUtil.str2dateAjax(organizeExam
                        .getSignupEndTime()));
                texam.setSignupStartTime(MyDateUtil.str2dateAjax(organizeExam
                        .getSignupStartTime()));
                if (organizeExam.getExamType() == 1) {
                    texam.setStatus(ExamPartEntityStatusEnum.AVAILABLE
                            .getState());
                    saveOrupdate(texam);
                    return texam.getId();
                } else {
                    if (creatTime.before(MyDateUtil.str2dateAjax(organizeExam
                            .getSignupStartTime()))) {
                        // 报名已经可以开始
                        texam.setStatus(ExamStatusEnum.SIGNUP.getState());
                        // 保存考试
                        saveOrupdate(texam);
                        return texam.getId();
                    } else {
                        // 报名未开始
                        texam.setStatus(ExamStatusEnum.RELEASE.getState());
                        // 保存考试
                        saveOrupdate(texam);
                        return texam.getId();
                    }
                }
            }

			/*
			 * 添加考试
			 */
            if (organizeExam.getExamType() == 1) {
                // 模拟考试
                Texam texam = new Texam(torganization, organizeExam, creatTime);
                texam.setStatus(ExamPartEntityStatusEnum.AVAILABLE.getState());
                saveOrupdate(texam);
                return texam.getId();
            } else {
                if (creatTime.before(MyDateUtil.str2dateAjax(organizeExam
                        .getSignupStartTime()))) {
                    // 报名已经可以开始
                    Texam texam = new Texam(torganization, organizeExam,
                            ExamStatusEnum.SIGNUP.getState(), creatTime);
                    // 保存考试
                    saveOrupdate(texam);
                    return texam.getId();
                } else {
                    // 报名未开始
                    Texam texam = new Texam(torganization, organizeExam,
                            ExamStatusEnum.RELEASE.getState(), creatTime);
                    // 保存考试
                    saveOrupdate(texam);
                    return texam.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deleteExam(int memberId, int examId) {
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        try {
            Texam texam = examDao.get(hql);
            if (texam == null) {
                throw new RuntimeException("考试不存在");
            } else {
                hql = "from Tmember sdy where sdy.id='" + memberId + "'";
                Tmember tmember = memberDao.get(hql);
				/*
				 * TODO 权限控制
				 */
                boolean haveRight = true;
                if (!haveRight) {
                    throw new RuntimeException("没有权限！");
                } else {
                    Date now = new Date();
                    if (now.after(texam.getExamStartTime())) {
                        // 暂时设置考试开始之前都可以编辑考试
                        throw new RuntimeException("超过可编辑时限！");
                    } else {
                        // 删除考试或者改变考试状态
                        texam.setStatus(ExamStatusEnum.CANCELED.getState());
                        examDao.saveOrUpdate(texam);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("editExam inner error!");
        }
        return true;
    }

    @Override
    public Integer getPaperId(Integer examId) throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            Tpaper tpaper = texam.getTpaper();
            return tpaper.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean connectExamAndAnnouncement(Integer examId, Integer annId)
            throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            if (texam == null) {
                throw new RuntimeException("无考试");
            }
            hql = "from Tannouncement sdy where sdy.id='" + annId + "'";
            Tannouncement tannouncement = announcementDao.get(hql);
            if (tannouncement == null) {
                throw new RuntimeException("无通知");
            }
            //建立关联
            texam.setTannouncement(tannouncement);
            examDao.saveOrUpdate(texam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }
}
