package com.zust.itee.exam.service.impl.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dao.hibernate.BaseDao2;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.CheckPaper;
import com.zust.itee.exam.dto.exam.member.PaperBaseInfo;
import com.zust.itee.exam.dto.exam.member.PaperBasicInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperDesign;
import com.zust.itee.exam.dto.exam.member.PaperInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperQuestionSubject;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionItems;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamResult;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.entity.Tpaper;
import com.zust.itee.exam.entity.TpaperDesign;
import com.zust.itee.exam.entity.TpaperList;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.entity.TquestionItems;
import com.zust.itee.exam.enums.exam.ExamPartEntityStatusEnum;
import com.zust.itee.exam.enums.exam.PaperTypeEnum;
import com.zust.itee.exam.enums.exam.QuestionRequiredEnum;
import com.zust.itee.exam.service.exam.PaperMemberService;
import com.zust.itee.exam.service.impl.BaseServiceImpl;
import com.zust.itee.exam.util.MyDateUtil;

@Service
@Transactional
public class PaperMemberServiceImpl extends BaseServiceImpl<Tpaper> implements
        PaperMemberService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BaseDao<Tpaper> paperDao;

    @Autowired
    BaseDao<Tquestion> questionDao;

    @Autowired
    BaseDao2<Tquestion> questionDao2;

    @Autowired
    BaseDao<TpaperDesign> paperDesignDao;

    @Autowired
    BaseDao<Tmember> memberDao;

    @Autowired
    BaseDao<Texam> examDao;

    @Autowired
    BaseDao<TpaperList> paperListDao;

    @Autowired
    BaseDao<TquestionItems> questionItemsDao;

    @Autowired
    BaseDao<Tdriver> driverDao;

    @Autowired
    BaseDao<TexamResult> examResultDao;

    @Autowired
    BaseDao<TexamSignup> examSignupDao;

    @Autowired
    BaseDao<DataDictionary> dataDictionaryDao;

    @Override
    public List<PaperQuestionType> getPaperDesignBaseInfo()
            throws RuntimeException {
        List<PaperQuestionType> paperQuestionTypes = new ArrayList<PaperQuestionType>();
        try {
            /*
             * 先遍历题型，再遍历知识点
			 */
            // 获取所有题型
            String hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.codeType=:codeType ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("codeType", "driverhr_question_type");
            DataDictionary questionTypeRoot = dataDictionaryDao
                    .get(hql, params);
            hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.dataDictionaryByUplink=:questionTypeRoot ";
            params.clear();
            params.put("questionTypeRoot", questionTypeRoot);
            List<DataDictionary> questionTypes = dataDictionaryDao.find(hql,
                    params);
            for (DataDictionary questionType : questionTypes) {
                PaperQuestionType paperQuestionType = new PaperQuestionType();
                List<PaperQuestionSubject> paperQuestionSubjects = new
                        ArrayList<PaperQuestionSubject>();
                // 获取题型id
                paperQuestionType.setId(questionType.getId());

                // 获取题型名
                paperQuestionType.setName(questionType.getValueStr());

                // 获取分数
                hql = "from Tquestion sdy where sdy.typeId='"
                        + questionType.getId() + "'";
                List<Tquestion> question = questionDao.find(hql, 1, 1);
                if (question != null && !question.isEmpty()) {
                    paperQuestionType.setScore(question.get(0).getMarks());
                }
                // 获取个数
                hql = "select count(*) from Tquestion sdy where sdy.typeId='"
                        + questionType.getId() + "' and sdy.required='"
                        + QuestionRequiredEnum.NOTREQUIRED.getRequired() + "'";
                Long typeSum = questionDao.count(hql);
                paperQuestionType.setSum(typeSum);

				/*
                 * 获取所有知识点
				 */
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.codeType=:codeType ";
                params.clear();
                params.put("codeType", "driverhr_question_subject");
                DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                        params);
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.dataDictionaryByUplink=:questionSubjectRoot ";
                params.clear();
                params.put("questionSubjectRoot", questionSubjectRoot);
                List<DataDictionary> questionSubjects = dataDictionaryDao.find(
                        hql, params);
                for (DataDictionary questionSubject : questionSubjects) {
                    PaperQuestionSubject paperQuestionSubject = new PaperQuestionSubject();
                    // 获取知识点id
                    paperQuestionSubject.setId(questionSubject.getId());

                    // 获取知识点名
                    paperQuestionSubject.setName(questionSubject.getValueStr());

                    // 获取个数
                    hql = "select count(*) from Tquestion sdy where 1=1 "
                            + "and sdy.subjectId=:subjectId "
                            + "and sdy.typeId=:typeId "
                            + "and sdy.required=:required ";
                    params.clear();
                    params.put("subjectId", questionSubject.getId());
                    params.put("typeId", questionType.getId());
                    params.put("required", QuestionRequiredEnum.NOTREQUIRED.getRequired());
                    Long subjectSum = questionDao.count(hql, params);
                    paperQuestionSubject.setSum(subjectSum);
                    // 加入题型中的list
                    paperQuestionSubjects.add(paperQuestionSubject);
                }
                // 赋值题型中的list
                paperQuestionType.setSubjects(paperQuestionSubjects);

                // 加入题型list
                paperQuestionTypes.add(paperQuestionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return paperQuestionTypes;
    }

    @Override
    public Map<String, Object> getRequiredQuestionsInfo()
            throws RuntimeException {
        Map<String, Object> requiredQuestionsInfo = new HashMap<>();
        try {
            String hql = "select count(*) from Tquestion sdy where 1=1 "
                    + "and sdy.required=:required ";
            Map<String, Object> params = new HashMap<>();
            params.put("required", QuestionRequiredEnum.REQUIRED.getRequired());
            Long questionNum = questionDao.count(hql, params);
            hql = "select sum(marks) from Tquestion sdy where 1=1 "
                    + "and sdy.required=:required ";
            Double questionMarks = questionDao2.sum(hql, params);
            requiredQuestionsInfo.put("num", questionNum);
            requiredQuestionsInfo.put("marks", questionMarks);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return requiredQuestionsInfo;
    }

    @Override
    public List<PaperQuestionType> getPaperDesignBaseInfoWithPaper(
            Integer paperId) throws RuntimeException {
        List<PaperQuestionType> paperQuestionTypes = new ArrayList<PaperQuestionType>();
        try {
            /*
			 * 先遍历题型，再遍历知识点
			 */
            // 获取所有题型
            String hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.codeType=:codeType ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("codeType", "driverhr_question_type");
            DataDictionary questionTypeRoot = dataDictionaryDao
                    .get(hql, params);
            hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.dataDictionaryByUplink=:questionTypeRoot ";
            params.clear();
            params.put("questionTypeRoot", questionTypeRoot);
            List<DataDictionary> questionTypes = dataDictionaryDao.find(hql,
                    params);
            for (DataDictionary questionType : questionTypes) {
                PaperQuestionType paperQuestionType = new PaperQuestionType();
                List<PaperQuestionSubject> paperQuestionSubjects = new
                        ArrayList<PaperQuestionSubject>();
                // 获取题型id
                paperQuestionType.setId(questionType.getId());

                // 获取题型名
                paperQuestionType.setName(questionType.getValueStr());

                // 获取分数
                hql = "from Tquestion sdy where sdy.typeId='"
                        + questionType.getId() + "'";
                List<Tquestion> question = questionDao.find(hql, 1, 1);
                paperQuestionType.setScore(question.get(0).getMarks());

                // 获取个数
                hql = "select count(*) from Tquestion sdy where sdy.typeId='"
                        + questionType.getId() + "'";
                Long typeSum = questionDao.count(hql);
                paperQuestionType.setSum(typeSum);

				/*
				 * 获取所有知识点
				 */
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.codeType=:codeType ";
                params.clear();
                params.put("codeType", "driverhr_question_subject");
                DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                        params);
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.dataDictionaryByUplink=:questionSubjectRoot ";
                params.clear();
                params.put("questionSubjectRoot", questionSubjectRoot);
                List<DataDictionary> questionSubjects = dataDictionaryDao.find(
                        hql, params);
                Integer typeCount = 0;
                for (DataDictionary questionSubject : questionSubjects) {
                    PaperQuestionSubject paperQuestionSubject = new PaperQuestionSubject();
                    // 获取知识点id
                    paperQuestionSubject.setId(questionSubject.getId());

                    // 获取知识点名
                    paperQuestionSubject.setName(questionSubject.getValueStr());

                    // 获取个数
                    hql = "select count(*) from Tquestion sdy where sdy.subjectId=:subjectId and "
                            + "sdy.typeId=:typeId";
                    params.clear();
                    params.put("subjectId", questionSubject.getId());
                    params.put("typeId", questionType.getId());
                    Long subjectSum = questionDao.count(hql, params);
                    paperQuestionSubject.setSum(subjectSum);
                    // 获取原有试卷的该题目类型个数
                    hql = "from TpaperDesign sdy where sdy.tpaper.id=:paperId "
                            + "and sdy.questionSubjectId=:subjectId "
                            + "and sdy.questionTypeId=:typeId ";
                    params.clear();
                    params.put("paperId", paperId);
                    params.put("subjectId", questionSubject.getId());
                    params.put("typeId", questionType.getId());
                    TpaperDesign paperDesign = paperDesignDao.get(hql, params);
                    if (paperDesign != null) {
                        Integer subjectCount = paperDesign.getNum();
                        paperQuestionSubject.setCount(subjectCount);
                        typeCount += subjectCount;
                    } else {
                        paperQuestionSubject.setCount(0);
                    }
                    // 加入题型中的list
                    paperQuestionSubjects.add(paperQuestionSubject);
                }
                // 赋值题型中的list
                paperQuestionType.setSubjects(paperQuestionSubjects);

                // 加入题型list
                paperQuestionType.setCount(typeCount);
                paperQuestionTypes.add(paperQuestionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return paperQuestionTypes;
    }

    @Override
    public int savePaperBaseInfo(List<PaperDesign> paperDesigns,
            PaperBaseInfo paperBaseInfo, Integer memberId)
            throws RuntimeException {
        try {
			/*
			 * 获取命题人信息
			 */
            String hql = "from Tmember sdy where sdy.id='" + memberId + "'";
            Tmember tmember = memberDao.get(hql);
            Torganization torganization = tmember.getTorganization();

			/*
			 * 生成paper
			 */
            Tpaper tpaper = new Tpaper();
            tpaper.setCreateTime(new Date());
            tpaper.setDescription(paperBaseInfo.getPaperDescription());
			/*
			 * TODO 难度系数规定暂定
			 */
            // tpaper.setDifficulty(difficulty);
            tpaper.setEasyPercent(paperBaseInfo.getEasyPercent());
            tpaper.setHardPercent(paperBaseInfo.getHardPercent());
            tpaper.setMediumPercent(paperBaseInfo.getMediumPercent());
            tpaper.setName(paperBaseInfo.getPaperName());
            tpaper.setType(paperBaseInfo.getPaperType());
            tpaper.setPassNum(paperBaseInfo.getPassNum());
            tpaper.setPassScore(paperBaseInfo.getPassScore());
            // 试卷默认可用
            tpaper.setStatus(ExamPartEntityStatusEnum.AVAILABLE.getState());
            tpaper.setTorganization(torganization);
            if (paperBaseInfo.getPaperId() != null) {
                tpaper.setId(paperBaseInfo.getPaperId());
            }
            paperDao.saveOrUpdate(tpaper);

			/*
			 * 删除原有paperDesign
			 */
            hql = "from TpaperDesign sdy where sdy.tpaper.id='"
                    + tpaper.getId() + "'";
            List<TpaperDesign> tpaperDesigns = paperDesignDao.find(hql);
            for (TpaperDesign tpaperDesign : tpaperDesigns) {
                paperDesignDao.delete(tpaperDesign);
            }
			/*
			 * 保存paperDesign
			 */
            for (PaperDesign temp : paperDesigns) {
                TpaperDesign tpaperDesign = new TpaperDesign();
                tpaperDesign.setNum(temp.getNum());
                tpaperDesign.setQuestionSubjectId(temp.getSubjectId());
                tpaperDesign.setQuestionTypeId(temp.getTypeId());
                tpaperDesign.setTpaper(tpaper);
                paperDesignDao.save(tpaperDesign);
            }
            return tpaper.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Tquestion> createTquestionsAndShowQuestions(Integer paperId)
            throws RuntimeException {
        try {
            String hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper tpaper = paperDao.get(hql);
			/*
			 * 获取paper基本信息
			 */
            Set<TpaperDesign> tpaperDesigns = tpaper.getTpaperDesigns();
            double easyPercent = tpaper.getEasyPercent();
            double hardPercent = tpaper.getHardPercent();

			/*
			 * 取消难度分配,设置easy百分比为100%
			 */
            easyPercent = 1.0;
            hardPercent = 0.0;

            List<Tquestion> res = new ArrayList<Tquestion>();

			/*
			 * 先添加所有必须题
			 */
            hql = "from Tquestion sdy where sdy.required=:required ";
            Map<String, Object> param = new HashMap<>();
            param.put("required", QuestionRequiredEnum.REQUIRED.getRequired());
            List<Tquestion> requiredQuestions = questionDao.find(hql, param);
            res.addAll(requiredQuestions);

			/*
			 * 根据paperDesign生成question列表 生成问题算法复杂度:O(n) n为paperDesign个数
			 */
            for (TpaperDesign tpaperDesign : tpaperDesigns) {
                int sum = tpaperDesign.getNum();
				/*
				 * 三种难度分配暂时按多出的一两道分配到中等难度
				 */
                int easy = (int) Math.floor(sum * easyPercent);
                int hard = (int) Math.floor(sum * hardPercent);
                int medium = sum - easy - hard;
                int[] num = { easy, medium, hard };
                Integer flag = 1;
                for (int i = 0; i < num.length; i++) {
					/*
					 * 遍历所有难度，检查是否每个难度的题目都充足
					 */
                    hql = "select count(*) from Tquestion where 1=1 "
                            + "and subject_id=:subjectId "
                            + "and type_id=:typeId "
                            + "and difficulty=:difficulty ";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("subjectId", tpaperDesign.getQuestionSubjectId());
                    params.put("typeId", tpaperDesign.getQuestionTypeId());
                    params.put("difficulty", (short) (i + 1));
                    Long count = questionDao.count(hql, params);
                    if (count < num[i]) {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    // 只有每个难度题目都充足才按每个难度分配个数
                    for (short i = 0; i < num.length; i++) {
						/*
						 * 每个paperDesign都按难度分配个数
						 */

						/*
						 * 从题库抽取题目
						 */
                        String sql = "select * from tquestion where 1=1 "
                                + "and subject_id=:subjectId "
                                + "and type_id=:typeId "
                                + "and difficulty=:difficulty "
                                + "order by rand() limit :num";
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("subjectId",
                                tpaperDesign.getQuestionSubjectId());
                        params.put("typeId", tpaperDesign.getQuestionTypeId());
                        params.put("difficulty", (short) (i + 1));
                        params.put("num", num[i]);
                        List<Tquestion> tquestions = questionDao2
                                .findBySqlGetEntity(sql, Tquestion.class,
                                        params);
                        res.addAll(tquestions);
                    }
                } else {
                    // 某个难度的题目个数不足，不按难度分配
                    String sql = "select * from tquestion where 1=1 "
                            + "and subject_id=:subjectId "
                            + "and type_id=:typeId "
                            + "order by rand() limit :num";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("subjectId", tpaperDesign.getQuestionSubjectId());
                    params.put("typeId", tpaperDesign.getQuestionTypeId());
                    params.put("num", num[0] + num[1] + num[2]);
                    List<Tquestion> tquestions = questionDao2
                            .findBySqlGetEntity(sql, Tquestion.class, params);
                    res.addAll(tquestions);
                }
            }

			/*
			 * 根据question列表生成paperList
			 */
			/*
			 *  resList顺序调整（随机排序）
			 */
            Collections.shuffle(res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Tquestion> getQuestionList(String questionsString)
            throws RuntimeException {
        List<Tquestion> questions = new ArrayList<Tquestion>();
        String[] source = questionsString.split(";");
        try {
            for (int i = 0; i < source.length; i++) {
                String hql = "from Tquestion sdy where sdy.id='" + source[i]
                        + "'";
                Tquestion question = questionDao.get(hql);
                questions.add(question);
            }
        } catch (Exception e) {
            new RuntimeException(e.getMessage());
        }

        return questions;
    }

    @Override
    public boolean savePaperList(List<Tquestion> tquestions, Integer paperId)
            throws RuntimeException {
        try {
            // 获取试卷
            String hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper paper = paperDao.get(hql);
            if (paper == null) {
                throw new RuntimeException("试卷不存在");
            }
			/*
			 * 删除原有paperList
			 */
            hql = "from TpaperList sdy where sdy.tpaper.id='" + paperId + "'";
            List<TpaperList> tpaperLists = paperListDao.find(hql);
            for (TpaperList tpaperList : tpaperLists) {
                paperListDao.delete(tpaperList);
            }
			/*
			 * 保存paperList
			 */
            for (int i = 1; i <= tquestions.size(); i++) {
                TpaperList tpaperList = new TpaperList();
                tpaperList.setSequence(i);
                tpaperList.setTpaper(paper);
                tpaperList.setTquestion(tquestions.get(i - 1));
                paperListDao.save(tpaperList);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PaperInfoForExam> getAllPapers(PageBaseDto pageBaseDto)
            throws RuntimeException {
        List<PaperInfoForExam> papers = new ArrayList<PaperInfoForExam>();
        try {
            // 筛选所有可以用的试卷
            String hql = "from Tpaper sdy where 1=1 "
                    // 可用
                    + "and sdy.status<>:status "
                    // 搜索
                    + "and (sdy.name like:key or sdy.torganization.name like:key) "
                    + "order by createTime desc";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", ExamPartEntityStatusEnum.CANCELED.getState());
            params.put("key", key);
            List<Tpaper> tpapers = paperDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            for (Tpaper tpaper : tpapers) {
                PaperInfoForExam paper = new PaperInfoForExam();
                paper.setCreateTime(MyDateUtil.date2strMD(tpaper
                        .getCreateTime()));
                paper.setDescription(tpaper.getDescription());
                paper.setId(tpaper.getId());
                paper.setName(tpaper.getName());
                paper.setOrgName(tpaper.getTorganization().getName());

                List<PaperDesign> paperDesigns = new ArrayList<PaperDesign>();
                Set<TpaperDesign> tpaperDesigns = tpaper.getTpaperDesigns();
                Integer questionSum = 0;
                Double scoreSum = 0.0;
                for (TpaperDesign tpaperDesign : tpaperDesigns) {
                    PaperDesign paperDesign = new PaperDesign();
                    paperDesign.setNum(tpaperDesign.getNum());
                    questionSum += tpaperDesign.getNum();

                    paperDesign.setSubjectId(tpaperDesign
                            .getQuestionSubjectId());
                    Long questionSubjectId = tpaperDesign
                            .getQuestionSubjectId();
                    hql = "from DataDictionary sdy where sdy.id='"
                            + questionSubjectId + "'";
                    DataDictionary questionSubject = dataDictionaryDao.get(hql);
                    paperDesign.setSubjectName(questionSubject.getValueStr());

                    paperDesign.setTypeId(tpaperDesign.getQuestionTypeId());
                    Long questionTypeId = tpaperDesign.getQuestionTypeId();
                    hql = "from DataDictionary sdy where sdy.id='"
                            + questionTypeId + "'";
                    DataDictionary questionType = dataDictionaryDao.get(hql);
                    paperDesign.setTypeName(questionType.getValueStr());
                    paperDesigns.add(paperDesign);

                    hql = "from Tquestion sdy where 1=1"
                            + "and sdy.subjectId=:subjectId "
                            + "and sdy.typeId=:typeId ";
                    params.clear();
                    params.put("subjectId", tpaperDesign.getQuestionSubjectId());
                    params.put("typeId", tpaperDesign.getQuestionTypeId());
                    List<Tquestion> tquestions = questionDao.find(hql, params);
                    Double oneScore = 0.0;
                    if (tquestions.size() >= 1) {
                        oneScore = tquestions.get(0).getMarks();
                    }
                    scoreSum += tpaperDesign.getNum() * oneScore;
                }
                paper.setPaperDesigns(paperDesigns);
                paper.setQuestionSum(questionSum);
                paper.setScoreSum(scoreSum);

                paper.setPassNum(tpaper.getPassNum());
                paper.setPassScore(tpaper.getPassScore());

                List<QuestionModel> questions = new ArrayList<QuestionModel>();
                hql = "from TpaperList sdy where sdy.tpaper.id='"
                        + tpaper.getId() + "' " + "order by sdy.sequence asc";
                List<TpaperList> tpaperLists = paperListDao.find(hql);
                for (TpaperList tpaperList : tpaperLists) {
                    Tquestion tquestion = tpaperList.getTquestion();
                    QuestionModel questionModel = new QuestionModel(tquestion);
                    hql = "from TquestionItems sdy where sdy.tquestion.id='"
                            + tquestion.getId() + "' "
                            + "order by sdy.sequence asc";
                    List<TquestionItems> tquestionItems = questionItemsDao
                            .find(hql);
                    questionModel.setQuestionItems(QuestionItems
                            .transToQuestionItems(tquestionItems));
                    questions.add(questionModel);
                }
                paper.setQuestions(questions);

                paper.setType(PaperTypeEnum.stateOf(tpaper.getType()).getInfo());

                papers.add(paper);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return papers;
    }

    @Override
    public PageBaseDto getAllPapersPageBaseDto(PageBaseDto pageBaseDto) throws RuntimeException {
        try {
            // 筛选所有可以用的试卷
            String hql = " select count(*) from Tpaper sdy where 1=1 "
                    // 可用
                    + "and sdy.status<>:status "
                    // 搜索
                    + "and (sdy.name like:key or sdy.torganization.name like:key) "
                    + "order by createTime desc";
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", ExamPartEntityStatusEnum.CANCELED.getState());
            params.put("key", key);
            Long sum = paperDao.count(hql, params);
            pageBaseDto.setSum(sum);
            Integer pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                    / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);
            pageBaseDto.setPageNum(pageNum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean scorePaperForOfficialExam(Integer examId, Integer driverId)
            throws RuntimeException {
		/*
		 * 获取每一题的结果
		 */
        String hql = "from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("examId", examId);
        params.put("driverId", driverId);
        List<TexamResult> texamResults = examResultDao.find(hql, params);

		/*
		 * 遍历算出总分
		 */
        double score = 0;
        for (TexamResult texamResult : texamResults) {
            score += texamResult.getMarks();
        }

		/*
		 * 添加总分记录
		 */
        hql = "from TexamSignup sdy where 1=1 " + "and sdy.texam.id=:examId "
                + "and sdy.tdriver.id=:driverId ";
        TexamSignup texamSignup = examSignupDao.get(hql, params);
        texamSignup.setFinalScore(score);
        examSignupDao.saveOrUpdate(texamSignup);

        return true;
    }

    @Override
    public List<CheckPaper> checkPaperDetail(Integer examId, Integer driverId) {
		/*
		 * 获取所有的题目结果
		 */
        String hql = "from TexamResult sdy where 1=1 "
                + "and sdy.texam.id=:examId " + "and sdy.tdriver.id=:driverId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("examId", examId);
        params.put("driverId", driverId);
        List<TexamResult> texamResults = examResultDao.find(hql, params);

		/*
		 * 转成dto
		 */
        List<CheckPaper> checkPapers = examResultListTransToCheckPaperList(texamResults);
        return checkPapers;
    }

    @Override
    public List<PaperBasicInfoForExam> getPaperBasicInfoForExam(
            PageBaseDto pageBaseDto, Integer memberOrgId) throws RuntimeException {
        List<PaperBasicInfoForExam> paperBasicInfoForExams = new ArrayList<PaperBasicInfoForExam>();
        try {
            String hql = "from Tpaper sdy where 1=1 "
                    // 试卷可用
                    + "and sdy.status<>:status "
                    //只显示本组织发起的所有试卷
                    + "and sdy.torganization.id=:orgId "
                    // 搜索
                    + "and (sdy.name like:key or sdy.torganization.name like:key) "
                    + "order by sdy.createTime desc";
            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("status", ExamPartEntityStatusEnum.CANCELED.getState());
            params.put("orgId", memberOrgId);
            params.put("key", key);
            List<Tpaper> paperList = paperDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            paperBasicInfoForExams = transTpaperToPaperBasicInfoForExams(paperList);
            return paperBasicInfoForExams;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PageBaseDto getPaperBasicInfoPageBaseDtoForExam(
            PageBaseDto pageBaseDto, Integer memberOrgId) throws RuntimeException {
        try {
            String hql = "select count(*) from Tpaper sdy where 1=1 "
                    // 试卷可用
                    + "and sdy.status<>:status "
                    //只筛选本组织发起的试卷
                    + "and sdy.torganization.id=:orgId "
                    // 搜索
                    + "and (sdy.name like:key or sdy.torganization.name like:key) ";
            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("status", ExamPartEntityStatusEnum.CANCELED.getState());
            params.put("orgId", memberOrgId);
            params.put("key", key);
            Long sum = paperDao.count(hql, params);
            Integer pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                    / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);
            pageBaseDto.setPageNum(pageNum);
            pageBaseDto.setSum(sum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deletePaper(Integer paperId) {
        try {
			/*
			 * 修改试卷状态
			 */
            String hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper paper = paperDao.get(hql);
            paper.setStatus(ExamPartEntityStatusEnum.CANCELED.getState());
            paperDao.saveOrUpdate(paper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public PaperInfoForExam getPaperDetailInfo(Integer paperId)
            throws RuntimeException {
        try {
            PaperInfoForExam paperInfoForExam = new PaperInfoForExam();
            // 获取试卷
            String hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper paper = paperDao.get(hql);
            // 获取试卷设计
            hql = "from TpaperDesign sdy where sdy.tpaper.id='" + paperId + "'";
            List<TpaperDesign> tpaperDesigns = paperDesignDao.find(hql);
            Double score = 0.0;
            Integer sum = 0;
            List<PaperDesign> paperDesigns = new ArrayList<PaperDesign>();
            for (TpaperDesign tpaperDesign : tpaperDesigns) {
                PaperDesign paperDesign = new PaperDesign();
                // 获取题型信息
                Long questionTypeId = tpaperDesign.getQuestionTypeId();
                hql = "from DataDictionary sdy where sdy.id='" + questionTypeId
                        + "'";
                DataDictionary questionType = dataDictionaryDao.get(hql);
                paperDesign.setTypeId(questionTypeId);
                paperDesign.setTypeName(questionType.getValueStr());
                // 获取知识点信息
                Long questionSubjectId = tpaperDesign.getQuestionSubjectId();
                hql = "from DataDictionary sdy where sdy.id='"
                        + questionSubjectId + "'";
                DataDictionary questionSubject = dataDictionaryDao.get(hql);
                paperDesign.setSubjectId(questionSubjectId);
                paperDesign.setSubjectName(questionSubject.getValueStr());
                // 题目数目相关
                paperDesign.setNum(tpaperDesign.getNum());
                sum += tpaperDesign.getNum();
                // 算进总分数
                hql = "from Tquestion sdy where sdy.typeId='"
                        + tpaperDesign.getQuestionTypeId() + "'";
                Tquestion tquestion = questionDao.get(hql);
                score += (tquestion.getMarks() * tpaperDesign.getNum());

                paperDesigns.add(paperDesign);
            }

            // 获取试卷题目详情
            List<QuestionModel> questionModels = new ArrayList<>();
            hql = "from TpaperList sdy where sdy.tpaper.id='" + paperId + "'";
            List<TpaperList> tpaperLists = paperListDao.find(hql);
            if (tpaperDesigns.size() > 0) {
                for (TpaperList tpaperList : tpaperLists) {
                    Tquestion tquestion = tpaperList.getTquestion();
                    QuestionModel questionModel = new QuestionModel(tquestion);
                    hql = "from TquestionItems sdy where sdy.tquestion.id='"
                            + tquestion.getId() + "' order by sdy.sequence asc";
                    List<TquestionItems> tquestionItems = questionItemsDao
                            .find(hql);
                    List<QuestionItems> questionItems = QuestionItems
                            .transToQuestionItems(tquestionItems);
                    questionModel.setQuestionItems(questionItems);
                    questionModels.add(questionModel);
                }
            }

            //加上必选题个数、分数
            hql = "from Tquestion sdy where 1=1 "
                    + "and sdy.required=:required ";
            Map<String, Object> params = new HashMap<>();
            params.put("required", QuestionRequiredEnum.REQUIRED.getRequired());
            List<Tquestion> requiredQuestions = questionDao.find(hql, params);
            for (Tquestion tquestion : requiredQuestions) {
                score += tquestion.getMarks();
                sum += 1;
            }

            // 赋值
            paperInfoForExam.setCreateTime(MyDateUtil.dateToAjaxString(paper
                    .getCreateTime()));
            paperInfoForExam.setDescription(paper.getDescription());
            paperInfoForExam.setId(paperId);
            paperInfoForExam.setName(paper.getName());
            paperInfoForExam.setOrgName(paper.getTorganization().getName());
            paperInfoForExam.setPaperDesigns(paperDesigns);
            paperInfoForExam.setPassNum(paper.getPassNum());
            paperInfoForExam.setPassScore(paper.getPassScore());
            paperInfoForExam.setQuestions(questionModels);
            paperInfoForExam.setQuestionSum(sum);
            paperInfoForExam.setScoreSum(score);
            paperInfoForExam.setType(PaperTypeEnum.stateOf(paper.getType())
                    .getInfo());
            return paperInfoForExam;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public PaperBaseInfo getPaperBaseInfoForEditPaper(Integer paperId)
            throws RuntimeException {
        try {
            PaperBaseInfo paperBaseInfo = new PaperBaseInfo();
            String hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper paper = paperDao.get(hql);
            if (paper == null) {
                throw new RuntimeException("试卷不存在");
            }
            Double start = paper.getEasyPercent() * 100;
            Double end = paper.getEasyPercent() * 100
                    + paper.getMediumPercent() * 100;
            String startArray[] = start.toString().split("\\.");
            String endArray[] = end.toString().split("\\.");
            String starString = startArray[0];
            String endString = endArray[0];
            paperBaseInfo.setSlideStart(Integer.parseInt(starString));
            paperBaseInfo.setSlideEnd(Integer.parseInt(endString));
            paperBaseInfo.setPaperDescription(paper.getDescription());
            paperBaseInfo.setPaperId(paperId);
            paperBaseInfo.setPaperName(paper.getName());
            paperBaseInfo.setPaperType(paper.getType());
            paperBaseInfo.setPassNum(paper.getPassNum());
            paperBaseInfo.setPassScore(paper.getPassScore());
            return paperBaseInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getPrePaperInfo(
            List<PaperQuestionType> questionTypes) throws RuntimeException {
        Map<String, Object> res = new HashMap<>();
        Integer sumNum = 0;
        Double sumScore = 0.0;
        for (PaperQuestionType paperQuestionType : questionTypes) {
            sumNum += paperQuestionType.getCount();
            sumScore += paperQuestionType.getCount()
                    * paperQuestionType.getScore();
        }
        res.put("sumNum", sumNum);
        res.put("sumScore", sumScore);
        return res;
    }

    @Override
    public Integer getExamPaperId(Integer examId) throws RuntimeException {
        Integer paperId = null;
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam texam = examDao.get(hql);
            Tpaper tpaper = texam.getTpaper();
            if (tpaper != null) {
                paperId = tpaper.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return paperId;
    }

    @Override
    public List<QuestionModel> questionListTransToQuerstionModelList(
            List<Tquestion> tquestions) {
        List<QuestionModel> questionModels = new ArrayList<QuestionModel>();
        for (Tquestion tquestion : tquestions) {
            QuestionModel questionModel = new QuestionModel(tquestion);
            String hql = "from TquestionItems sdy where sdy.tquestion.id='"
                    + tquestion.getId() + "' " + "order by sdy.sequence asc";
            List<TquestionItems> tquestionItems = questionItemsDao.find(hql);
            questionModel.setQuestionItems(QuestionItems
                    .transToQuestionItems(tquestionItems));
            questionModels.add(questionModel);
        }
        return questionModels;
    }

    @Override
    public List<CheckPaper> examResultListTransToCheckPaperList(
            List<TexamResult> texamResults) {
        List<CheckPaper> checkPapers = new ArrayList<CheckPaper>();
        for (TexamResult texamResult : texamResults) {
            Tquestion tquestion = texamResult.getTquestion();
            QuestionModel questionModel = new QuestionModel(tquestion);
            String hql = "from TquestionItems sdy where sdy.tquestion.id='"
                    + tquestion.getId() + "' " + "order by sdy.sequence asc";
            List<TquestionItems> tquestionItems = questionItemsDao.find(hql);
            questionModel.setQuestionItems(QuestionItems
                    .transToQuestionItems(tquestionItems));
            CheckPaper checkPaper = new CheckPaper(questionModel, texamResult);
            checkPapers.add(checkPaper);
        }
        return checkPapers;
    }

    @Override
    public List<PaperDesign> stringTransToPaperDesignList(
            String paperDesignString) {
        List<PaperDesign> paperDesigns = new ArrayList<PaperDesign>();
        String[] paperDesignsStrs = paperDesignString.split(";");
        for (int i = 0; i < paperDesignsStrs.length; i++) {
            String[] paperDesignDetail = paperDesignsStrs[i].split("&");
            Long typeId = Long.parseLong(paperDesignDetail[0]);
            Long subjectId = Long.parseLong(paperDesignDetail[1]);
            Integer num = Integer.parseInt(paperDesignDetail[2]);
            PaperDesign paperDesign = new PaperDesign();
            paperDesign.setNum(num);
            paperDesign.setSubjectId(subjectId);
            paperDesign.setTypeId(typeId);
            paperDesigns.add(paperDesign);
        }
        return paperDesigns;
    }

    @Override
    public boolean paperConnectExam(Integer paperId, Integer examId)
            throws RuntimeException {
        try {
            String hql = "from Texam sdy where sdy.id='" + examId + "'";
            Texam exam = examDao.get(hql);
            hql = "from Tpaper sdy where sdy.id='" + paperId + "'";
            Tpaper paper = paperDao.get(hql);
            exam.setTpaper(paper);
            examDao.saveOrUpdate(exam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<PaperBasicInfoForExam> transTpaperToPaperBasicInfoForExams(
            List<Tpaper> paperList) throws RuntimeException {
        List<PaperBasicInfoForExam> paperBasicInfoForExams = new ArrayList<PaperBasicInfoForExam>();
        try {
            for (Tpaper paper : paperList) {
                PaperBasicInfoForExam paperBasicInfoForExam = new PaperBasicInfoForExam();
                paperBasicInfoForExam.setCreateTime(MyDateUtil
                        .dateToAjaxString(paper.getCreateTime()));
                paperBasicInfoForExam.setDescription(paper.getDescription());
                paperBasicInfoForExam.setId(paper.getId());
                paperBasicInfoForExam.setName(paper.getName());
                paperBasicInfoForExam.setOrgName(paper.getTorganization()
                        .getName());
                paperBasicInfoForExam.setType(PaperTypeEnum.stateOf(
                        paper.getType()).getInfo());
                paperBasicInfoForExams.add(paperBasicInfoForExam);
            }
            return paperBasicInfoForExams;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
