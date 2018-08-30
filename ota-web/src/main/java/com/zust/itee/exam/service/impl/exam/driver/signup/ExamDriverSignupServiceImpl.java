package com.zust.itee.exam.service.impl.exam.driver.signup;

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
import com.zust.itee.exam.entity.TexamResult;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.enums.DriverStatusEnum;
import com.zust.itee.exam.enums.OrganizationTypeEnum;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamExposerEnum;
import com.zust.itee.exam.exception.SignupCancelException;
import com.zust.itee.exam.service.exam.driver.signup.ExamDriverSignupService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;

@Service
@Transactional
public class ExamDriverSignupServiceImpl extends BaseServiceImpl<Texam>
        implements ExamDriverSignupService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<TexamResult> examResultDao;

    @Override
    public List<ExamExposer> getExamHome(PageBaseDto pageBaseDto, int driverId)
            throws RuntimeException {
        List<ExamExposer> examExposers = new ArrayList<ExamExposer>();
        try {
            String hql = "from Texam sdy where 1=1 "
                    // 时间删选在报名的考试
                    + "and sdy.signupStartTime <= :now "
                    + "and sdy.signupEndTime >= :now "
                    // 搜索
                    + "and (sdy.name like :key "
                    + "or sdy.description like :key "
                    + "or sdy.torganization.name like :key) "
                    /*
					 * TODO权限判断
					 */
                    // 按创建时间倒序
                    + "order by sdy.createTime desc";

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("now", new java.sql.Date(new Date().getTime()));
            params.put("key", "%" + pageBaseDto.getSearchKey() + "%");

			/*
			 * 获取entity
			 */
            List<Texam> texams = examDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());

            for (Texam texam : texams) {
				/*
				 * 检查是否已经报名
				 */
                hql = "from TexamSignup sdy where sdy.texam.id=:examId and sdy.tdriver"
						+ ".id=:driverId and status<>:status";
                params.clear();
                params.put("examId", texam.getId());
                params.put("driverId", driverId);
                params.put("status", DriverExamEnum.CANCELED.getStatus());
                TexamSignup texamSignup = examSignupDao.get(hql, params);
                ExamExposer examExposer = new ExamExposer();
                if (texamSignup != null) {
					/*
					 * 已报名
					 */
                    examExposer = new ExamExposer(
                            ExamExposerEnum.HAVESIGNUPCANCANCEL.getState(),
                            ExamExposerEnum.HAVESIGNUPCANCANCEL.getStateInfo(),
                            texam, texam.getId());
                } else {
					/*
					 * 未报名
					 */
                    examExposer = new ExamExposer(
                            ExamExposerEnum.INSIGNUP.getState(),
                            ExamExposerEnum.INSIGNUP.getStateInfo(), texam,
                            texam.getId());
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
    public PageBaseDto getExamPageBaseDto(PageBaseDto pageBaseDto) {
        PageBaseDto res = new PageBaseDto();
        String hql = "select count(*) from Texam sdy where 1=1 "
                // 时间删选在报名的考试
                + "and sdy.signupStartTime <= :now "
                + "and sdy.signupEndTime >= :now "
                // 搜索
                + "and sdy.name like :key " + "or sdy.description like :key "
                + "or sdy.torganization.name like :key "
				/*
				 * TODO权限判断
				 */
                // 按创建时间倒序
                + "order by sdy.createTime desc";
        java.sql.Date nowSql = new java.sql.Date(new Date().getTime());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("now", nowSql);
        params.put("key", "%" + pageBaseDto.getSearchKey() + "%");

        long sum = examDao.count(hql, params);

        int pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);

        res.setPage(pageBaseDto.getPage());
        res.setPageNum(pageNum);
        res.setRows(pageBaseDto.getRows());
        res.setSearchKey(pageBaseDto.getSearchKey());
        res.setStatus(pageBaseDto.getStatus());
        res.setSum(sum);

        return res;
    }

    @Override
    public boolean signup(int examId, int driverId) throws RuntimeException {
        Date now = new Date();
        try {
			/*
			 * 获取考试
			 */
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            if (texam == null) {
                throw new RuntimeException("考试不存在");
            }

			/*
			 * 获取驾驶员
			 */
            hql = "from Tdriver sdy where sdy.id='" + driverId + "'";
            Tdriver tdriver = driverDao.get(hql);
            if (tdriver == null) {
                throw new RuntimeException("驾驶员不存在");
            }
            if (tdriver.getStatus() != DriverStatusEnum.NEEDTTRAINED
                    .getStatus()) {
                // 驾驶员类型不为待培训类型
                throw new RuntimeException("没有报名资格");
            }
            if (now.before(texam.getSignupStartTime())) {
                // 报名未开始
                throw new RuntimeException("报名未开始！");
            } else if (now.after(texam.getSignupEndTime())) {
                // 报名已结束
                throw new RuntimeException("报名已结束！");
            } else {
                hql = "from TexamSignup sdy where sdy.texam.id=:examId and sdy.tdriver"
						+ ".id=:driverId";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("examId", examId);
                params.put("driverId", driverId);
                TexamSignup texamSignup = examSignupDao.get(hql, params);
                if (texamSignup != null
                        && texamSignup.getStatus() == DriverExamEnum.SIGNUPD
                        .getStatus()) {
                    // 重复报名
                    throw new RuntimeException("重复报名！");
                } else {
                    if (texamSignup != null) {
                        // 更为可用
                        texamSignup.setStatus(DriverExamEnum.SIGNUPD
                                .getStatus());
                        examSignupDao.saveOrUpdate(texamSignup);
                    } else {
                        // 添加报名
                        TexamSignup texamSignup2 = new TexamSignup(texam,
                                tdriver, now,
                                DriverExamEnum.SIGNUPD.getStatus());
                        examSignupDao.saveOrUpdate(texamSignup2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean signupCancel(int examId, int driverId, Short memberOrgType)
            throws RuntimeException {
		/*
		 * 获取考试
		 */
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        try {
            Texam texam = examDao.get(hql);
            if (texam == null) {
                throw new RuntimeException("考试不存在！");
            } else {
                // 获取考试报名结束时间作为取消时限
                Date deadline = texam.getSignupEndTime();
                Date now = new Date();
                if (now.after(deadline)
                        && (memberOrgType == null || memberOrgType == OrganizationTypeEnum.COMPANY
                        .getType())) {
                    throw new SignupCancelException("取消时限已过！");
                } else {
					/*
					 * 获取报名
					 */
                    hql = "from TexamSignup sdy where sdy.texam.id=:examId and sdy.tdriver.id=:driverId";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("examId", examId);
                    params.put("driverId", driverId);
                    TexamSignup texamSignup = examSignupDao.get(hql, params);
                    if (texamSignup == null) {
                        throw new RuntimeException("重复取消报名！");
                    } else {
                        // 删除报名记录，或者改变报名记录状态
                        texamSignup.setStatus(DriverExamEnum.CANCELED
                                .getStatus());
                        examSignupDao.saveOrUpdate(texamSignup);
                    }
                }
            }
        } catch (SignupCancelException e1) {
            throw e1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }
}
