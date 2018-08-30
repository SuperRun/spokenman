package com.zust.itee.exam.service.impl.exam.driver.examing;

import java.text.SimpleDateFormat;
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
import com.zust.itee.exam.dto.exam.driver.ChangeQuestion;
import com.zust.itee.exam.dto.exam.driver.ExamingBaseInfo;
import com.zust.itee.exam.dto.exam.member.QuestionItems;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamResult;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tpaper;
import com.zust.itee.exam.entity.TpaperList;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.entity.TquestionItems;
import com.zust.itee.exam.enums.exam.DriverExamEnum;
import com.zust.itee.exam.enums.exam.ExamStatusEnum;
import com.zust.itee.exam.enums.exam.QuestionAnswersSplitChar;
import com.zust.itee.exam.enums.exam.QuestionTypeEnum;
import com.zust.itee.exam.service.exam.driver.examing.ExamDriverExamingService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;

@Service
@Transactional
public class ExamDriverExamingServiceImpl extends BaseServiceImpl<Texam>
        implements ExamDriverExamingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseDao<TexamSignup> examSignupDao;

    @Autowired
    private BaseDao<Texam> examDao;

    @Autowired
    private BaseDao<Tdriver> driverDao;

    @Autowired
    private BaseDao<TexamResult> examResultDao;

    @Autowired
    private BaseDao<TquestionItems> questionItemsDao;

    @Autowired
    private BaseDao<TpaperList> paperListDao;

    @Autowired
    private BaseDao<DataDictionary> dataDictionaryDao;

    @Override
    public boolean createExamResult(Integer examId, Integer driverId) {
        /*
         * 获取paperList
		 */
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        Texam exam = examDao.get(hql);
        Tpaper paper = exam.getTpaper();
        hql = "from TpaperList sdy where sdy.tpaper.id='" + paper.getId()
                + "' " + "order by sdy.sequence asc";
        List<TpaperList> paperLists = paperListDao.find(hql);
        if (paperLists.size() <= 0) {
            // 还未组卷，需要组卷
            return false;
        }
		/*
		 * 检查是否已存在examResult
		 */

        hql = "select count(*) from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("examId", examId);
        param.put("driverId", driverId);
        Long sum = examResultDao.count(hql, param);
        if (sum >= 1) {
            return true;
        }

		/*
		 * 获取驾驶员
		 */
        hql = "from Tdriver sdy where sdy.id='" + driverId + "'";
        Tdriver driver = driverDao.get(hql);
        Date now = new Date();
        int i = 1;
        for (TpaperList paperList : paperLists) {
            TexamResult examResult = new TexamResult();
            // 没做的题目答案为空
            examResult.setAnswer(null);
            examResult.setSequence(i);
            examResult.setSubmitTime(now);
            examResult.setTdriver(driver);
            examResult.setTexam(exam);
            examResult.setTquestion(paperList.getTquestion());
            // 保存examResult
            examResultDao.save(examResult);
            i++;
        }
		/*
		 * 修改驾驶员参加考试状态
		 */
        hql = "from TexamSignup sdy where sdy.texam.id=:examId "
                + "and sdy.tdriver.id=:driverId ";
        param.clear();
        param.put("examId", examId);
        param.put("driverId", driverId);
        TexamSignup texamSignup = examSignupDao.get(hql, param);
        texamSignup.setStatus(DriverExamEnum.EXAMED.getStatus());
        texamSignup.setTakeExamTime(new Date());
        examSignupDao.saveOrUpdate(texamSignup);

        return true;
    }

    @Override
    public Integer getPaperId(Integer examId) throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            if (texam == null) {
                throw new RuntimeException("考试不存在！");
            }
            Tpaper tpaper = texam.getTpaper();
            if (tpaper == null) {
                throw new RuntimeException("试卷不存在！");
            }
            return tpaper.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean createExamResultWithQuestions(List<Tquestion> questions,
            Integer examId, Integer driverId) {
		/*
		 * 先检查是否存在examResult
		 */
        String hql = "select count(*) from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("examId", examId);
        param.put("driverId", driverId);
        Long sum = examResultDao.count(hql, param);
        if (sum >= 1) {
            return true;
        }

		/*
		 * 获取其他参数
		 */
        hql = "from Texam sdy where sdy.id='" + examId + "'";
        Texam exam = examDao.get(hql);
        hql = "from Tdriver sdy where sdy.id='" + driverId + "'";
        Tdriver driver = driverDao.get(hql);

		/*
		 * 生成结果
		 */
        Date now = new Date();
        int i = 1;
        for (Tquestion question : questions) {
            TexamResult examResult = new TexamResult();
            examResult.setAnswer(null);
            examResult.setSequence(i);
            examResult.setSubmitTime(now);
            examResult.setTdriver(driver);
            examResult.setTexam(exam);
            examResult.setTquestion(question);
            examResultDao.save(examResult);
            i++;
        }
		/*
		 * 修改驾驶员参加考试状态
		 */
        hql = "from TexamSignup sdy where sdy.texam.id=:examId "
                + "and sdy.tdriver.id=:driverId ";
        param.clear();
        param.put("examId", examId);
        param.put("driverId", driverId);
        TexamSignup texamSignup = examSignupDao.get(hql, param);
        texamSignup.setStatus(DriverExamEnum.EXAMED.getStatus());
        texamSignup.setTakeExamTime(new Date());
        examSignupDao.saveOrUpdate(texamSignup);

        return true;
    }

    @Override
    public boolean checkHaveSubmit(Integer examId, Integer driverId) {
        String hql = "from TexamSignup sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("examId", examId);
        params.put("driverId", driverId);
        TexamSignup examSignup = examSignupDao.get(hql, params);
        if (examSignup.getStatus() != DriverExamEnum.SUBMITED.getStatus()) {
            // 还没交卷
            return false;
        }
        return true;
    }

    @Override
    public boolean saveAnswer(Integer driverId, Integer examId,
            ChangeQuestion changeQuestion) throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            Date now = new Date();
            if (now.after(exam.getExamEndTime())) {
                // 再次验证能否提交
                return false;
            }
            hql = "from TexamResult sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId "
                    + "and sdy.sequence=:sequence ";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("examId", examId);
            param.put("driverId", driverId);
            param.put("sequence", changeQuestion.getSequenceNow());
            TexamResult examResult = examResultDao.get(hql, param);
            // 更改答案
            examResult.setAnswer(changeQuestion.getAnswer());
            examResultDao.saveOrUpdate(examResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }

    @Override
    public QuestionModel getQuestion(Integer driverId, Integer examId,
            ChangeQuestion changeQuestion) throws RuntimeException {
        try {
			/*
			 * 从考试结果表中获取题目
			 */
            String hql = "from TexamResult sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId "
                    + "and sdy.sequence=:sequence ";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("examId", examId);
            param.put("driverId", driverId);
            param.put("sequence", changeQuestion.getSequenceNext());
            TexamResult examResult = examResultDao.get(hql, param);
            Tquestion question = examResult.getTquestion();
            QuestionModel questionModel = new QuestionModel(question);
            hql = "from TquestionItems sdy where sdy.tquestion.id='"
                    + question.getId() + "' " + "order by sdy.sequence asc";
            List<TquestionItems> tquestionItems = questionItemsDao.find(hql);
            // 获取题型
            hql = "from DataDictionary sdy where sdy.id='"
                    + question.getTypeId() + "'";
            DataDictionary questionType = dataDictionaryDao.get(hql);
            questionModel.setQuestionItems(QuestionItems
                    .transToQuestionItems(tquestionItems));
            questionModel.setAnswerPre(examResult.getAnswer());
            questionModel.setTypeName(questionType.getValueStr());
            questionModel.setHelp(QuestionTypeEnum.getIndexOf(
                    questionType.getValueStr()).getHelp());
            // 选择题判断哪些选项之前被选中过
            String answerPre = examResult.getAnswer(); // 原先若为多选题答案为&&answer1&&……
            if (answerPre != null) {
                String[] answerPres = answerPre
                        .split(QuestionAnswersSplitChar.AND.getSplitChar());
                List<QuestionItems> questionItemsList = questionModel
                        .getQuestionItems();
                for (QuestionItems questionItems : questionItemsList) {
                    // 对于每一个选项，都遍历已选的所有选项，如果匹配成功，选项之前被选中
                    for (int i = 0; i < answerPres.length; i++) {
                        if (questionItems.getSequence().equals(answerPres[i])) {
                            questionItems.setSelected(true);
                            break;
                        }
                    }
                }
            }

            return questionModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ChangeQuestion updateChangeQuestion(Integer driverId,
            Integer examId, ChangeQuestion changeQuestion,
            QuestionModel questionModel) {
        ChangeQuestion res = new ChangeQuestion();
        String hql = "from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId "
                + "order by sdy.sequence asc";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("examId", examId);
        param.put("driverId", driverId);
        List<TexamResult> examResults = examResultDao.find(hql, param);
        List<Boolean> sequenceStatus = new ArrayList<Boolean>();
        for (TexamResult examResult : examResults) {
            if (examResult.getAnswer() == null) {
                sequenceStatus.add(false);
            } else {
                sequenceStatus.add(true);
            }
        }
        res.setSequenceStatus(sequenceStatus);
        res.setAnswer(null);
        res.setQuestionIdNow(questionModel.getId());
        res.setQuestionTypeNow(questionModel.getTypeId());
        res.setSequenceNext(null);
        res.setSequenceNow(changeQuestion.getSequenceNext());
        res.setSubmitable(changeQuestion.isSubmitable());
        res.setHaveSubmit(changeQuestion.isHaveSubmit());
        return res;
    }

    @Override
    public Long getQuestionSum(Integer driverId, Integer examId) {
        String hql = "select count(*) from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("examId", examId);
        param.put("driverId", driverId);
        Long sum = examResultDao.count(hql, param);
        return sum;
    }

    @Override
    public ExamingBaseInfo getExamingBaseInfo(Integer driverId, Integer examId) {
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        Texam exam = examDao.get(hql);
        hql = "from Tdriver sdy where sdy.id='" + driverId + "'";
        Tdriver driver = driverDao.get(hql);
        ExamingBaseInfo examingBaseInfo = new ExamingBaseInfo();
        examingBaseInfo.setDriverName(driver.getName());
        examingBaseInfo.setDuration(exam.getDuration());
        examingBaseInfo.setExamName(exam.getName());
        examingBaseInfo.setPaperName(exam.getTpaper().getName());
        return examingBaseInfo;
    }

    @Override
    public Map<String, Object> getexamDate(Integer examId) {
        Map<String, Object> examDate = new HashMap<String, Object>();
        String hql = "from Texam sdy where sdy.id='" + examId + "'";
        Texam exam = examDao.get(hql);
        Date examStartTime = exam.getExamStartTime();
        Date examEndTime = exam.getExamEndTime();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm");
        examDate.put("examStartTime", simpleDateFormat.format(examStartTime));
        examDate.put("examEndTime", simpleDateFormat.format(examEndTime));
        examDate.put("now", simpleDateFormat.format(now));
        return examDate;
    }

    @Override
    public boolean countScoreInOfficialExam(Integer examId, Integer driverId)
            throws RuntimeException {
        try {
			/*
			 * 遍历答题结果，得出总分
			 */
            String hql = "from TexamResult sdy where 1=1 "
                    + "and sdy.texam.id=:examId "
                    + "and sdy.tdriver.id=:driverId ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("examId", examId);
            params.put("driverId", driverId);
            List<TexamResult> examResults = examResultDao.find(hql, params);
            Double sum = 0.0;
            Integer num = 0;
            for (TexamResult examResult : examResults) {
                Tquestion question = examResult.getTquestion();
                String answer = question.getAnswer();
                if (answer.indexOf(QuestionAnswersSplitChar.OR.getSplitChar()) >= 0) {
                    // 答案为包含多个只需满足一个的答案
                    String[] orAnswers = answer
                            .split(QuestionAnswersSplitChar.OR.getSplitChar());
                    int i = 0;
                    for (i = 0; i < orAnswers.length; i++) {
                        if (orAnswers[i].equals(examResult.getAnswer())) {
                            break;
                        }
                    }
                    if (i < orAnswers.length) {
                        // 匹配到答案
                        sum += question.getMarks();
                        num++;
                    }
                } else {
                    // 需要跟答案完全匹配
                    if (question.getAnswer().equals(examResult.getAnswer())) {
                        sum += question.getMarks();
                        num++;
                    }
                }
            }

			/*
			 * 修改驾驶员考试状态
			 */
            hql = "from TexamSignup sdy where 1=1 "
                    + "and sdy.tdriver.id=:driverId "
                    + "and sdy.texam.id=:examId ";
            TexamSignup texamSignup = examSignupDao.get(hql, params);
            texamSignup.setFinalScore(sum);
            texamSignup.setFinalNum(num);
            texamSignup.setStatus(DriverExamEnum.SUBMITED.getStatus());
            examSignupDao.saveOrUpdate(texamSignup);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public Integer examLogin(String sfzNo) throws RuntimeException {
        Integer examId = null;
        try {
            // 检查驾驶员是否存在
            String hql = "from Tdriver sdy where sdy.sfzNo='" + sfzNo + "'";
            Tdriver tdriver = driverDao.get(hql);
            if (tdriver == null) {
                throw new RuntimeException("用户不存在！");
            }

			/*
			 * 查找正在进行的考试
			 */
            Date now = new Date();
            java.sql.Date nowSQL = new java.sql.Date(now.getTime());
            hql = "from Texam sdy where 1=1 " + "and sdy.status<>:status "
                    + "and sdy.examStartTime<=:nowSQL "
                    + "and sdy.examEndTime>=:nowSQL ";
            Map<String, Object> params = new HashMap<>();
            params.put("status", ExamStatusEnum.CANCELED.getState());
            params.put("nowSQL", nowSQL);
            List<Texam> exams = examDao.find(hql, params);
            if (exams.size() <= 0) {
                // 当前无考试中的考试
                throw new RuntimeException("当前无进行中考试！");
            } else {
                TexamSignup texamSignup = null;
                for (Texam texam : exams) {
                    // 遍历考试，检查有没有报名的考试
                    hql = "from TexamSignup sdy where 1=1 "
                            + "and sdy.texam.id=:examId "
                            + "and sdy.tdriver.id=:driverId ";
                    params.clear();
                    params.put("examId", texam.getId());
                    params.put("driverId", tdriver.getId());
                    TexamSignup texamSignup2 = examSignupDao.get(hql, params);
                    if (texamSignup2 != null
                            && texamSignup2.getStatus() != DriverExamEnum.CANCELED
                            .getStatus()) {
                        // 如果报名信息有效
                        // 规定同一时间驾驶员只会参加一场考试
                        texamSignup = texamSignup2;
                        break;
                    }
                }
                if (texamSignup == null) {
                    // 所有正在进行中的考试都没有有效报名信心
                    throw new RuntimeException("未报名考试！");
                } else {
                    examId = texamSignup.getTexam().getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return examId;
    }

    @Override
    public Tdriver driverLogin(String sfzNo) {
        try {
            String hql = "from Tdriver sdy where sdy.sfzNo='" + sfzNo + "'";
            Tdriver tdriver = driverDao.get(hql);
            return tdriver;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
