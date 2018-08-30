package com.zust.itee.exam.service.impl.exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.PaperQuestionSubject;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionCreateModel;
import com.zust.itee.exam.dto.exam.member.QuestionItems;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.DataDictionary;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.entity.TquestionItems;
import com.zust.itee.exam.enums.DataDictionaryEnum;
import com.zust.itee.exam.enums.OutputFilePathEnmu;
import com.zust.itee.exam.enums.exam.ExamPartEntityStatusEnum;
import com.zust.itee.exam.enums.exam.QuestionAnswersSplitChar;
import com.zust.itee.exam.enums.exam.QuestionDifficultyEnum;
import com.zust.itee.exam.enums.exam.QuestionItemTypeEnum;
import com.zust.itee.exam.enums.exam.QuestionRequiredEnum;
import com.zust.itee.exam.service.exam.QuestionService;
import com.zust.itee.exam.util.ExcelUtil;
import com.zust.itee.exam.util.MyDateUtil;
import com.zust.itee.exam.util.UploadPathUtil;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BaseDao<Tquestion> questionDao;

    @Autowired
    BaseDao<TquestionItems> questionItmesDao;

    @Autowired
    BaseDao<DataDictionary> dataDictionaryDao;

    @Autowired
    BaseDao<Tmember> memberDao;

    @Override
    public List<QuestionModel> getAllQuestions(PageBaseDto pageBaseDto,
            String subjectIdStr, String typeIdStr, String requiredStr)
            throws RuntimeException {
        List<QuestionModel> questionModels = new ArrayList<>();
        try {
            /*
             * 筛选知识点
			 */
            Boolean subjectAll = false;
            List<Long> subjectIds = new ArrayList<>();
            if (subjectIdStr == null) {
                subjectAll = true;
            } else {
                String[] subjectIdStrs = subjectIdStr.split(";");
                for (int i = 0; i < subjectIdStrs.length; i++) {
                    String subjectId = subjectIdStrs[i];
                    if (subjectId.equals("-1")) {
                        subjectAll = true;
                        break;
                    } else {
                        Long subjectLong = Long.parseLong(subjectId);
                        subjectIds.add(subjectLong);
                    }
                }
            }

			/*
             * 筛选题型
			 */
            Boolean typeAll = false;
            List<Long> typeIds = new ArrayList<>();
            if (typeIdStr == null) {
                typeAll = true;
            } else {
                String[] typeIdStrs = typeIdStr.split(";");
                for (int i = 0; i < typeIdStrs.length; i++) {
                    String typeId = typeIdStrs[i];
                    if (typeId.equals("-1")) {
                        typeAll = true;
                        break;
                    } else {
                        Long typeIdLong = Long.parseLong(typeId);
                        typeIds.add(typeIdLong);
                    }
                }
            }

			/*
             * 筛选必选题
			 */
            Boolean requiredAll = false;
            List<Short> requiredIds = new ArrayList<>();
            if (requiredStr == null) {
                requiredAll = true;
            } else {
                String[] requiredIdStrs = requiredStr.split(";");
                for (int i = 0; i < requiredIdStrs.length; i++) {
                    String requiredId = requiredIdStrs[i];
                    if (requiredId.equals("-1")) {
                        requiredAll = true;
                        break;
                    } else {
                        Short requiredShort = Short.parseShort(requiredId);
                        requiredIds.add(requiredShort);
                    }
                }
            }

			/*
             * 查询题目
			 */
            String hql = "from Tquestion sdy where 1=1 "
                    // 状态不为删除
                    + "and sdy.status<>:delete "
                    // 搜索
                    + "and sdy.content like:key ";

            Map<String, Object> params = new HashMap<>();
            params.put("delete", ExamPartEntityStatusEnum.CANCELED.getState());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);

            // 添加知识点筛选
            if (subjectAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Long subjectId : subjectIds) {
                    hql += "or sdy.subjectId='" + subjectId + "' ";
                }
                hql += " )";
            }

            // 添加题型筛选
            if (typeAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Long typeId : typeIds) {
                    hql += "or sdy.typeId='" + typeId + "' ";
                }
                hql += " )";
            }

            // 添加必选题筛选
            if (requiredAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Short requiredId : requiredIds) {
                    hql += "or sdy.required='" + requiredId + "' ";
                }
                hql += " )";
            }
            hql += "order by sdy.createTime desc";

            // 获取实体类
            List<Tquestion> tquestions = questionDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());

			/*
             * 转化成dto
			 */
            for (Tquestion tquestion : tquestions) {
                QuestionModel questionModel = new QuestionModel();

                String answer = tquestion.getAnswer();
                // 获取多答案
                String[] answerSource = answer
                        .split(QuestionAnswersSplitChar.OR.getSplitChar());
                List<String> answers = new ArrayList<>();
                for (int i = 0; i < answerSource.length; i++) {
                    answers.add(answerSource[i]);
                }
                // 获取多选题答案
                String[] answerAnds = answer.split(QuestionAnswersSplitChar.AND
                        .getSplitChar());
                // 获取所有选项
                hql = "from TquestionItems sdy where 1=1 "
                        + "and sdy.tquestion.id=:questionId ";
                params.clear();
                params.put("questionId", tquestion.getId());
                List<TquestionItems> tquestionItemsList = questionItmesDao
                        .find(hql, params);
                List<QuestionItems> questionItemsList = new ArrayList<>();
                for (TquestionItems tquestionItems : tquestionItemsList) {
                    QuestionItems questionItems = new QuestionItems();
                    questionItems.setContent(tquestionItems.getContent());
                    questionItems.setId(tquestionItems.getId());
                    questionItems.setPic(tquestionItems.getPic());
                    char idx = (char) ('A' - 1 + tquestionItems.getSequence());
                    String idxStr = String.valueOf(idx);
                    questionItems.setSequence(idxStr);
                    for (int i = 0; i < answerAnds.length; i++) {
                        if (idxStr.equals(answerAnds[i])) {
                            // 如果多选题答案包含该选项，设为被选择
                            questionItems.setSelected(true);
                            break;
                        }
                    }

                    questionItemsList.add(questionItems);
                }

                // 获取知识点
                hql = "from DataDictionary sdy where sdy.id='"
                        + tquestion.getSubjectId() + "'";
                DataDictionary subject = dataDictionaryDao.get(hql);
                // 获取题型
                hql = "from DataDictionary sdy where sdy.id='"
                        + tquestion.getTypeId() + "'";
                DataDictionary type = dataDictionaryDao.get(hql);

                if (questionItemsList.size() <= 0) {
                    //无选项的时候才直接显示答案
                    questionModel.setAnswers(answers);
                }
                questionModel.setContent(tquestion.getContent());
                questionModel.setDifficulty(QuestionDifficultyEnum.getIndexOf(
                        tquestion.getDifficulty()).getInfo());
                questionModel.setId(tquestion.getId());
                questionModel.setMarks(tquestion.getMarks());
                questionModel.setPic1(tquestion.getPic1());
                questionModel.setPic2(tquestion.getPic2());
                questionModel.setPic3(tquestion.getPic3());
                questionModel.setQuestionItems(questionItemsList);
                if (tquestion.getRequired() == QuestionRequiredEnum.REQUIRED
                        .getRequired()) {
                    questionModel.setRequired(true);
                } else {
                    questionModel.setRequired(false);
                }
                questionModel.setSubjectId(tquestion.getSubjectId());
                questionModel.setSubjectName(subject.getValueStr());
                questionModel.setTypeId(tquestion.getTypeId());
                questionModel.setTypeName(type.getValueStr());
                questionModel.setCreateTime(MyDateUtil
                        .dateToAjaxDateOnlyString(tquestion.getCreateTime()));

                questionModels.add(questionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return questionModels;
    }

    @Override
    public PageBaseDto getAllQuestionPageBaseDto(PageBaseDto pageBaseDto,
            String subjectIdStr, String typeIdStr, String requiredStr)
            throws RuntimeException {
        try {
            /*
             * 筛选知识点
			 */
            Boolean subjectAll = false;
            List<Long> subjectIds = new ArrayList<>();
            if (subjectIdStr == null) {
                subjectAll = true;
            } else {
                String[] subjectIdStrs = subjectIdStr.split(";");
                for (int i = 0; i < subjectIdStrs.length; i++) {
                    String subjectId = subjectIdStrs[i];
                    if (subjectId.equals("-1")) {
                        subjectAll = true;
                        break;
                    } else {
                        Long subjectLong = Long.parseLong(subjectId);
                        subjectIds.add(subjectLong);
                    }
                }
            }

			/*
             * 筛选题型
			 */
            Boolean typeAll = false;
            List<Long> typeIds = new ArrayList<>();
            if (typeIdStr == null) {
                typeAll = true;
            } else {
                String[] typeIdStrs = typeIdStr.split(";");
                for (int i = 0; i < typeIdStrs.length; i++) {
                    String typeId = typeIdStrs[i];
                    if (typeId.equals("-1")) {
                        typeAll = true;
                        break;
                    } else {
                        Long typeIdLong = Long.parseLong(typeId);
                        typeIds.add(typeIdLong);
                    }
                }
            }

			/*
			 * 筛选必选题
			 */
            Boolean requiredAll = false;
            List<Short> requiredIds = new ArrayList<>();
            if (requiredStr == null) {
                requiredAll = true;
            } else {
                String[] requiredIdStrs = requiredStr.split(";");
                for (int i = 0; i < requiredIdStrs.length; i++) {
                    String requiredId = requiredIdStrs[i];
                    if (requiredId.equals("-1")) {
                        requiredAll = true;
                        break;
                    } else {
                        Short requiredShort = Short.parseShort(requiredId);
                        requiredIds.add(requiredShort);
                    }
                }
            }

			/*
			 * 查询题目
			 */
            String hql = "select count(*) from Tquestion sdy where 1=1 "
                    // 状态不为删除
                    + "and sdy.status<>:delete "
                    // 搜索
                    + "and sdy.content like:key ";

            Map<String, Object> params = new HashMap<>();
            params.put("delete", ExamPartEntityStatusEnum.CANCELED.getState());
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);

            // 添加知识点筛选
            if (subjectAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Long subjectId : subjectIds) {
                    hql += "or sdy.subjectId='" + subjectId + "' ";
                }
                hql += " )";
            }

            // 添加题型筛选
            if (typeAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Long typeId : typeIds) {
                    hql += "or sdy.typeId='" + typeId + "' ";
                }
                hql += " )";
            }

            // 添加必选题筛选
            if (requiredAll) {
                // 全部不需要限定条件
            } else {
                hql += "and (1=0 ";
                for (Short requiredId : requiredIds) {
                    hql += "or sdy.required='" + requiredId + "' ";
                }
                hql += " )";
            }

            Long sum = questionDao.count(hql, params);
            pageBaseDto.setSum(sum);
            pageBaseDto.setPageNum(pageBaseDto.getRows(), sum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PaperQuestionType> getAllQuestionType() throws RuntimeException {
        List<PaperQuestionType> questionTypes = new ArrayList<>();
        try {
			/*
			 * 获取题型根节点
			 */
            String hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.level=:root "
                    + "and sdy.valueStr=:questionType ";
            Map<String, Object> params = new HashMap<>();
            params.put("root",
                    DataDictionaryEnum.QUESTIONTYPEROOR.getLevel());
            params.put("questionType",
                    DataDictionaryEnum.QUESTIONTYPEROOR.getValueStr());
            DataDictionary questionTypeRoot = dataDictionaryDao
                    .get(hql, params);
			/*
			 * 获取具体题型
			 */
            hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.dataDictionaryByCtrlId=:questionTypeRoot "
                    + "order by sdy.id asc";
            params.clear();
            params.put("questionTypeRoot", questionTypeRoot);
            List<DataDictionary> tQuestionTypes = dataDictionaryDao.find(hql,
                    params);
            for (DataDictionary dataDictionary : tQuestionTypes) {
                PaperQuestionType paperQuestionType = new PaperQuestionType();
                paperQuestionType.setId(dataDictionary.getId());
                paperQuestionType.setName(dataDictionary.getValueStr());
				/*
				 * 获取题型分数
				 */
                hql = "from Tquestion sdy where 1=1 "
                        + "and sdy.typeId=:typeId ";
                params.clear();
                params.put("typeId", dataDictionary.getId());
                Tquestion tquestion = questionDao.get(hql, params);
                if (tquestion != null) {
                    paperQuestionType.setScore(tquestion.getMarks());
                }
                questionTypes.add(paperQuestionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return questionTypes;
    }

    @Override
    public List<PaperQuestionSubject> getAllQuestionSubject()
            throws RuntimeException {
        List<PaperQuestionSubject> questionSubjects = new ArrayList<>();
        try {
			/*
			 * 获取题目知识点根节点
			 */
            String hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.level=:root "
                    + "and sdy.valueStr=:questionSubject ";
            Map<String, Object> params = new HashMap<>();
            params.put("root",
                    DataDictionaryEnum.QUESTIONSUBJECTROOT.getLevel());
            params.put("questionSubject",
                    DataDictionaryEnum.QUESTIONSUBJECTROOT
                            .getValueStr());
            DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                    params);
			/*
			 * 获取具体题目知识点
			 */
            hql = "from DataDictionary sdy where 1=1 "
                    + "and sdy.dataDictionaryByCtrlId=:questionSubjectRoot "
                    + "order by sdy.id asc";
            params.clear();
            params.put("questionSubjectRoot", questionSubjectRoot);
            List<DataDictionary> tQuestionSubjects = dataDictionaryDao.find(
                    hql, params);
            for (DataDictionary dataDictionary : tQuestionSubjects) {
                PaperQuestionSubject paperQuestionSubject = new PaperQuestionSubject();
                paperQuestionSubject.setId(dataDictionary.getId());
                paperQuestionSubject.setName(dataDictionary.getValueStr());
                questionSubjects.add(paperQuestionSubject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return questionSubjects;
    }

    @Override
    public QuestionModel getQuestionInfo(Integer questionId)
            throws RuntimeException {
        try {
            String hql = "from Tquestion sdy where sdy.id='" + questionId + "'";
            Tquestion tquestion = questionDao.get(hql);
            if (tquestion == null) {
                throw new RuntimeException("题目不存在！");
            }
            QuestionModel questionModel = new QuestionModel(tquestion);
            // 获取题型、知识点信息
            hql = "from DataDictionary sdy where sdy.id='"
                    + tquestion.getTypeId() + "'";
            DataDictionary questionType = dataDictionaryDao.get(hql);
            questionModel.setTypeName(questionType.getValueStr());

            hql = "from DataDictionary sdy where sdy.id='"
                    + tquestion.getSubjectId() + "'";
            DataDictionary questionSubject = dataDictionaryDao.get(hql);
            questionModel.setSubjectName(questionSubject.getValueStr());

            //获取多答案
            String answersSource = tquestion.getAnswer();
            String[] answersStrings = answersSource.split(
                    QuestionAnswersSplitChar.OR.getSplitChar());
            List<String> answers = new ArrayList<>();
            for (int i = 0; i < answersStrings.length; i++) {
                answers.add(answersStrings[i]);
            }
            questionModel.setAnswers(answers);

            // 获取选项
            hql = "from TquestionItems sdy where sdy.tquestion.id='"
                    + tquestion.getId() + "' " + "order by sequence asc";
            List<TquestionItems> tquestionItems = questionItmesDao.find(hql);
            if (tquestionItems.size() > 0) {
                if (tquestionItems.get(0).getContent() != null) {
                    // 文字选项
                    questionModel.setQuestionItemType(QuestionItemTypeEnum.FONT
                            .getName());
                } else {
                    // 图片选项
                    questionModel
                            .setQuestionItemType(QuestionItemTypeEnum.PICTURE
                                    .getName());
                }
                List<QuestionItems> questionItems = QuestionItems
                        .transToQuestionItems(tquestionItems);

                //判断选项是否是答案
                String[] answersAnd = answersSource.split(
                        QuestionAnswersSplitChar.AND.getSplitChar());
                for (QuestionItems questionItems2 : questionItems) {
                    questionItems2.setSelected(false);
                    for (int i = 0; i < answersAnd.length; i++) {
                        if (answersAnd[i].equals(questionItems2.getSequence())) {
                            questionItems2.setSelected(true);
                            break;
                        }
                    }
                }

                questionModel.setQuestionItems(questionItems);
                questionModel.setSelect(true);
            } else {
                // 无选项
                questionModel.setQuestionItemType(QuestionItemTypeEnum.NONE
                        .getName());
            }
            if (tquestion.getRequired() == QuestionRequiredEnum.REQUIRED
                    .getRequired()) {
                questionModel.setRequired(true);
            } else {
                questionModel.setRequired(false);
            }
            return questionModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public String getQuestionImportExcel(HttpServletRequest request)
            throws RuntimeException {
        try {
			/*
			 * 新建excel
			 */
            HSSFWorkbook wb = new HSSFWorkbook();

			/*
			 * 获取所有题型
			 */
            String hql = "from DataDictionary sdy where sdy.valueStr=:questionType";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("questionType",
                    DataDictionaryEnum.QUESTIONTYPEROOR.getValueStr());
            DataDictionary questionTypeRoot = dataDictionaryDao.get(hql, param);
            List<DataDictionary> questionTypes = questionTypeRoot
                    .getDataDictionariesForUplink();
			/*
			 * 编写题型参照表
			 */
            HSSFSheet sheet = wb.createSheet("题型参照表");
            sheet.setDefaultColumnWidth(35);
            sheet.setDefaultRowHeight((short) 400);
            // 创建单元格居中格式
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 创建第一行
            HSSFRow row = sheet.createRow((int) 0);
            // 编写第一行表头信息
            HSSFCell cell = row.createCell(0);
            cell.setCellValue("编号");
            cell.setCellStyle(style);

            cell = row.createCell((int) 1);
            cell.setCellValue("题型");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("分值");
            cell.setCellStyle(style);

            // 写入实际题型信息
            for (int i = 0; i < questionTypes.size(); i++) {
                // 新建行
                row = sheet.createRow(i + 1);
                // 获取数据
                DataDictionary questionType = questionTypes.get(i);
                // 赋值
                cell = row.createCell(0);
                cell.setCellValue(questionType.getId());
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(questionType.getValueStr());
                cell.setCellStyle(style);

                hql = "from Tquestion sdy where sdy.typeId='"
                        + questionType.getId() + "'";
                Tquestion tquestion = questionDao.get(hql);
                Double markD = 0.0D;
                if (tquestion != null) {
                    markD = tquestion.getMarks();
                }
                cell = row.createCell(2);
                cell.setCellValue(markD == 0.0 ? "未知" : markD.toString());
                cell.setCellStyle(style);
            }

			/*
			 * 获取所有知识点
			 */
            hql = "from DataDictionary sdy where sdy.valueStr=:questionSubject";
            param.clear();
            param.put("questionSubject",
                    DataDictionaryEnum.QUESTIONSUBJECTROOT
                            .getValueStr());
            DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                    param);
            List<DataDictionary> questionSubjects = questionSubjectRoot
                    .getDataDictionariesForUplink();
			/*
			 * 编写题目知识点参照表
			 */
            sheet = wb.createSheet("知识点参照表");
            sheet.setDefaultColumnWidth(35);
            sheet.setDefaultRowHeight((short) 400);
            // 编写第一行表头
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("编号");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("知识点");
            cell.setCellStyle(style);
            // 填写实际知识点信息
            for (int i = 0; i < questionSubjects.size(); i++) {
                row = sheet.createRow(i + 1);
                DataDictionary questionSubject = questionSubjects.get(i);
                cell = row.createCell(0);
                cell.setCellValue(questionSubject.getId());
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(questionSubject.getValueStr());
                cell.setCellStyle(style);
            }

			/*
			 * 创建新题导入表
			 */
            sheet = wb.createSheet("新题导入表");
            sheet.setDefaultColumnWidth(35);
            sheet.setDefaultRowHeight((short) 400);
            // 第一行提示信息
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("题型、知识点、难度请参照参照表填写编号，新题型或知识点直接填写提醒信息或知识点信息");
            cell = row.createCell(1);
            cell = row.createCell(2);
            cell = row.createCell(3);
            cell = row.createCell(4);
            cell = row.createCell(5);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

            // 第二行表头
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("题型编号");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("知识点编号");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("题干信息");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("分值(若为已有题型，请填写对应题型分值 )");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("难度编号（易：0，中：1，难：2）");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("答案(选择题答案可导入后设置)");
            cell.setCellStyle(style);

			/*
			 * 将文件存到指定位置
			 */
            // 创建文件夹
            String savePath = UploadPathUtil.getWebRoot(request);
            File file = new File(savePath
                    + OutputFilePathEnmu.QUESTIONEXCEL.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(savePath
                    + OutputFilePathEnmu.QUESTIONEXCEL.getPath()
                    + "questionImport" + ".xls");
            wb.write(fout);
            logger.info(file.getAbsolutePath());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return OutputFilePathEnmu.QUESTIONEXCEL.getPath() + "questionImport"
                + ".xls";
    }

    @Override
    public boolean importQuestionsByExcel(File file, Integer memberId) {
        try {
            // 获取对应流
            InputStream inputStream = new FileInputStream(file);
            // 将excel读取成二维数组
            HSSFCell[][] questionInfo = ExcelUtil.transExcel(inputStream,
                    "新题导入表");
			/*
			 * 遍历题目信息添加题目
			 */
            logger.info("excel有=" + (questionInfo.length - 1) + "=条数据");
            // 跳过第一行提示信息
            for (int i = 1; i < questionInfo.length; i++) {
                Tquestion tquestion = new Tquestion();
                String hql;
				/*
				 * 获取题型编号
				 */
                if (questionInfo[i][0] == null) {
                    // 未填写题型信息跳过
                    continue;
                }
                String questionTypeInfo = questionInfo[i][0]
                        .getStringCellValue();
                Long questionTypeId = null;
                try {
                    questionTypeId = Long.parseLong(questionTypeInfo);
                } catch (Exception e) {
					/*
					 * 格式不是整型，新建题型
					 */
                    // 获取题型根节点
                    hql = "from DataDictionary sdy where sdy.valueStr=:questionType and sdy"
                            + ".level=:root ";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("questionType",
                            DataDictionaryEnum.QUESTIONTYPEROOR
                                    .getValueStr());
                    params.put("root",
                            DataDictionaryEnum.QUESTIONTYPEROOR
                                    .getLevel());
                    DataDictionary questionTypeRoot = dataDictionaryDao.get(
                            hql, params);
                    // 新建题型
                    DataDictionary dataDictionary = new DataDictionary();
                    dataDictionary.setValueStr(questionTypeInfo);
                    dataDictionary.setDataDictionaryByCtrlId(questionTypeRoot);
                    dataDictionary.setDataDictionaryByUplink(questionTypeRoot);
                    dataDictionary.setLevel(1);
                    dataDictionaryDao.save(dataDictionary);
                    tquestion.setTypeId(dataDictionary.getId());
                    questionTypeId = dataDictionary.getId();
                }
                hql = "from DataDictionary sdy where sdy.id='" + questionTypeId
                        + "'";
                DataDictionary questionType = dataDictionaryDao.get(hql);
                if (questionType == null) {
                    // 题型编号输入错误，题型不存在
                    continue;
                } else {
                    tquestion.setTypeId(questionTypeId);
                }
				/*
				 * 获取知识点
				 */
                if (questionInfo[i][1] == null) {
                    // 未填写知识点信息跳过
                    continue;
                }
                String questionSubjectInfo = questionInfo[i][1]
                        .getStringCellValue();
                Long questionSubjectId = null;
                try {
                    questionSubjectId = Long.parseLong(questionSubjectInfo);
                } catch (Exception e) {
					/*
					 * 新知识点
					 */
                    // 获取知识点根节点
                    hql = "from DataDictionary sdy where sdy.valueStr=:questionSubject and sdy"
                            + ".level=:root ";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("questionSubject",
                            DataDictionaryEnum.QUESTIONSUBJECTROOT
                                    .getValueStr());
                    params.put("root",
                            DataDictionaryEnum.QUESTIONSUBJECTROOT
                                    .getLevel());
                    DataDictionary questionSubjectRoot = dataDictionaryDao.get(
                            hql, params);
                    // 新建知识点
                    DataDictionary dataDictionary = new DataDictionary();
                    dataDictionary.setValueStr(questionSubjectInfo);
                    dataDictionary
                            .setDataDictionaryByCtrlId(questionSubjectRoot);
                    dataDictionary
                            .setDataDictionaryByUplink(questionSubjectRoot);
                    dataDictionary.setLevel(1);
                    dataDictionaryDao.save(dataDictionary);
                    tquestion.setSubjectId(dataDictionary.getId());
                    questionSubjectId = dataDictionary.getId();
                }
                hql = "from DataDictionary sdy where sdy.id='"
                        + questionSubjectId + "'";
                DataDictionary questionSubject = dataDictionaryDao.get(hql);
                if (questionSubject == null) {
                    // 题目知识点输入有误
                    continue;
                } else {
                    tquestion.setSubjectId(questionSubjectId);
                }

				/*
				 * 获取题干信息
				 */
                if (questionInfo[i][2] == null) {
                    // 未填写题干信息跳过
                    continue;
                }
                String questionContent = questionInfo[i][2]
                        .getStringCellValue();
                tquestion.setContent(questionContent);
				/*
				 * 获取分值
				 */
                String questionMarkInfo;
                if (questionInfo[i][3] == null) {
                    questionMarkInfo = "";
                } else {
                    questionMarkInfo = questionInfo[i][3].getStringCellValue();
                }
                Double questionMark = null;
                // 先找题型，若已有题型，直接赋值原先题型分值
                hql = "from Tquestion sdy where sdy.typeId='"
                        + tquestion.getTypeId() + "'";
                Tquestion questionForMark = questionDao.get(hql);
                if (questionForMark != null) {
                    tquestion.setMarks(questionForMark.getMarks());
                } else {
                    try {
                        questionMark = Double.parseDouble(questionMarkInfo);
                    } catch (Exception e) {
                        continue;
                    }
                    if (questionMark != null) {
                        tquestion.setMarks(questionMark);
                    }
                }

				/*
				 * 获取难度
				 */
                String difficultyInfo;
                if (questionInfo[i][4] == null) {
                    difficultyInfo = "";
                } else {
                    difficultyInfo = questionInfo[i][4].getStringCellValue();
                }
                Short difficulty = null;
                try {
                    difficulty = Short.parseShort(difficultyInfo);
                } catch (Exception e) {
                    // 如果格式不正确，设置成中等
                    tquestion.setDifficulty(QuestionDifficultyEnum.MEDIUM
                            .getId());
                }
                if (difficulty != null) {
                    int flag = 0;
                    for (QuestionDifficultyEnum questionDifficultyEnum : QuestionDifficultyEnum
                            .values()) {
                        if (difficulty == questionDifficultyEnum.getId()) {
                            tquestion.setDifficulty(difficulty);
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        tquestion.setDifficulty(QuestionDifficultyEnum.MEDIUM
                                .getId());
                    }
                }

				/*
				 * 获取答案
				 */
                String answer;
                if (questionInfo[i][5] == null) {
                    answer = "";
                } else {
                    answer = questionInfo[i][5].getStringCellValue();
                }
                tquestion.setAnswer(answer);
                tquestion.setCreateTime(new Date());
                tquestion.setOperatorId(memberId);

                questionDao.save(tquestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        // 删除服务器中文件
        file.delete();
        return true;
    }

    @Override
    public boolean createOrUpdateQuestion(QuestionCreateModel questionModel,
            Integer memberId) throws RuntimeException {
        try {
            if (questionModel.getTypeId() == -1) {
				/*
				 * 新建题型
				 */
                // 获取题型根节点
                String hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.level=:root "
                        + "and sdy.valueStr=:questionTypeRoot ";
                Map<String, Object> params = new HashMap<>();
                params.put("root",
                        DataDictionaryEnum.QUESTIONTYPEROOR.getLevel());
                params.put("questionTypeRoot",
                        DataDictionaryEnum.QUESTIONTYPEROOR
                                .getValueStr());
                DataDictionary questionTypeRoot = dataDictionaryDao.get(hql,
                        params);
                // 创建新题型
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setValueStr(questionModel.getNewType());
                dataDictionary.setDataDictionaryByCtrlId(questionTypeRoot);
                dataDictionary.setDataDictionaryByUplink(questionTypeRoot);
                dataDictionary.setLevel(DataDictionaryEnum.SUBDATA
                        .getLevel());
                dataDictionaryDao.saveOrUpdate(dataDictionary);
                // 题目type修改
                questionModel.setTypeId(dataDictionary.getId());
            }
            if (questionModel.getSubjectId() == -1) {
				/*
				 * 新建知识点
				 */
                // 获取知识点根
                String hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.level=:root "
                        + "and sdy.valueStr=:questionSubjectRoot ";
                Map<String, Object> params = new HashMap<>();
                params.put("root",
                        DataDictionaryEnum.QUESTIONSUBJECTROOT
                                .getLevel());
                params.put("questionSubjectRoot",
                        DataDictionaryEnum.QUESTIONSUBJECTROOT
                                .getValueStr());
                DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                        params);
                // 创建知识点
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setValueStr(questionModel.getNewSubject());
                dataDictionary.setLevel(DataDictionaryEnum.SUBDATA
                        .getLevel());
                dataDictionary.setDataDictionaryByCtrlId(questionSubjectRoot);
                dataDictionary.setDataDictionaryByUplink(questionSubjectRoot);
                dataDictionaryDao.saveOrUpdate(dataDictionary);
                // 修改题目subjectId
                questionModel.setSubjectId(dataDictionary.getId());
            }

			/*
			 * 如果原来题目有选项删除选项(为了兼容编辑题目)
			 */
            if (questionModel.getId() != null) {
                String hql = "from TquestionItems sdy where sdy.tquestion.id=:questionId ";
                Map<String, Object> params = new HashMap<>();
                params.put("questionId", questionModel.getId());
                List<TquestionItems> tquestionItems = questionItmesDao.find(
                        hql, params);
                for (TquestionItems tquestionItems2 : tquestionItems) {
                    questionItmesDao.delete(tquestionItems2);
                }
            }

			/*
			 * 保存题目
			 */
            Tquestion tquestion = new Tquestion(questionModel, new Date(),
                    memberId);
            if (questionModel.getId() != null) {
                tquestion.setId(questionModel.getId());
            }
            questionDao.saveOrUpdate(tquestion);

			/*
			 * 保存选项
			 */
            if (QuestionItemTypeEnum.FONT.getName().equals(
                    questionModel.getQuestionItemsType())) {
                // 文字选项
                String[] itemContents = questionModel
                        .getQuestionItemsContentStr().split(";");
                for (int i = 0; i < itemContents.length; i++) {
                    TquestionItems tquestionItems = new TquestionItems();
                    tquestionItems.setContent(itemContents[i]);
                    tquestionItems.setSequence((short) (i + 1));
                    tquestionItems.setTquestion(tquestion);
                    questionItmesDao.saveOrUpdate(tquestionItems);
                }
            } else if (QuestionItemTypeEnum.PICTURE.getName().equals(
                    questionModel.getQuestionItemsType())) {
                // 图片选项
                String[] itemPics = questionModel.getQuestionItemsPicStr()
                        .split(";");
                for (int i = 0; i < itemPics.length; i++) {
                    TquestionItems tquestionItems = new TquestionItems();
                    tquestionItems.setPic(itemPics[i]);
                    tquestionItems.setSequence((short) (i + 1));
                    tquestionItems.setTquestion(tquestion);
                    questionItmesDao.saveOrUpdate(tquestionItems);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean editQuestion(Integer memberId, Integer questionId,
            QuestionCreateModel questionModel) throws RuntimeException {
        try {
            // 获取原题目
            String hql = "from Tquestion sdy where sdy.id='" + questionId + "'";
            Tquestion tquestion = questionDao.get(hql);

            if (questionModel.getTypeId() == -1) {
				/*
				 * 新建题型
				 */
                // 获取题型根节点
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.level=:root "
                        + "and sdy.valueStr=:questionTypeRoot ";
                Map<String, Object> params = new HashMap<>();
                params.put("root",
                        DataDictionaryEnum.QUESTIONTYPEROOR.getLevel());
                params.put("questionTypeRoot",
                        DataDictionaryEnum.QUESTIONTYPEROOR
                                .getValueStr());
                DataDictionary questionTypeRoot = dataDictionaryDao.get(hql,
                        params);
                // 创建新题型
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setValueStr(questionModel.getNewType());
                dataDictionary.setDataDictionaryByCtrlId(questionTypeRoot);
                dataDictionary.setDataDictionaryByUplink(questionTypeRoot);
                dataDictionary.setLevel(DataDictionaryEnum.SUBDATA
                        .getLevel());
                dataDictionaryDao.saveOrUpdate(dataDictionary);
                // 题目type修改
                questionModel.setTypeId(dataDictionary.getId());
            }
            if (questionModel.getSubjectId() == -1) {
				/*
				 * 新建知识点
				 */
                // 获取知识点根
                hql = "from DataDictionary sdy where 1=1 "
                        + "and sdy.level=:root "
                        + "and sdy.valueStr=:questionSubjectRoot ";
                Map<String, Object> params = new HashMap<>();
                params.put("root",
                        DataDictionaryEnum.QUESTIONSUBJECTROOT
                                .getLevel());
                params.put("questionSubjectRoot",
                        DataDictionaryEnum.QUESTIONSUBJECTROOT
                                .getValueStr());
                DataDictionary questionSubjectRoot = dataDictionaryDao.get(hql,
                        params);
                // 创建知识点
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setValueStr(questionModel.getNewSubject());
                dataDictionary.setLevel(DataDictionaryEnum.SUBDATA
                        .getLevel());
                dataDictionary.setDataDictionaryByCtrlId(questionSubjectRoot);
                dataDictionary.setDataDictionaryByUplink(questionSubjectRoot);
                dataDictionaryDao.saveOrUpdate(dataDictionary);
                // 修改题目subjectId
                questionModel.setSubjectId(dataDictionary.getId());
            }

			/*
			 * 修改题目中属性，保存新题目
			 */
            tquestion.setAnswer(questionModel.getAnswer());
            tquestion.setContent(questionModel.getContent());
            tquestion.setCreateTime(new Date());
            tquestion.setDifficulty(questionModel.getDifficulty());
            tquestion.setMarks(questionModel.getMarks());
            tquestion.setOperatorId(memberId);
            tquestion.setPic1(questionModel.getPic1());
            tquestion.setPic2(questionModel.getPic2());
            tquestion.setPic3(questionModel.getPic3());
            tquestion.setStatus(ExamPartEntityStatusEnum.AVAILABLE.getState());
            tquestion.setSubjectId(questionModel.getSubjectId());
            tquestion.setTypeId(questionModel.getTypeId());
            questionDao.saveOrUpdate(tquestion);

			/*
			 * 保存题目选项
			 */
            // 删除原有选项
            Set<TquestionItems> tquestionItemsList = tquestion
                    .getTquestionItemses();
            tquestion.setTquestionItemses(null);
            for (TquestionItems tquestionItems : tquestionItemsList) {
                questionItmesDao.delete(tquestionItems);
            }

			/*
			 * 保存选项
			 */
            if (QuestionItemTypeEnum.FONT.getName().equals(
                    questionModel.getQuestionItemsType())) {
                // 文字选项
                String[] itemContents = questionModel
                        .getQuestionItemsContentStr().split(";");
                for (int i = 0; i < itemContents.length; i++) {
                    TquestionItems tquestionItems = new TquestionItems();
                    tquestionItems.setContent(itemContents[i]);
                    tquestionItems.setSequence((short) (i + 1));
                    tquestionItems.setTquestion(tquestion);
                    questionItmesDao.saveOrUpdate(tquestionItems);
                }
            } else if (QuestionItemTypeEnum.PICTURE.getName().equals(
                    questionModel.getQuestionItemsType())) {
                // 图片选项
                String[] itemPics = questionModel.getQuestionItemsPicStr()
                        .split(";");
                for (int i = 0; i < itemPics.length; i++) {
                    TquestionItems tquestionItems = new TquestionItems();
                    tquestionItems.setPic(itemPics[i]);
                    tquestionItems.setSequence((short) (i + 1));
                    tquestionItems.setTquestion(tquestion);
                    questionItmesDao.saveOrUpdate(tquestionItems);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deleteQuestion(Integer questionId) throws RuntimeException {
        try {
			/*
			 * 删除题目及题目选项
			 */
            String hql = "from Tquestion sdy where sdy.id='" + questionId + "'";
            Tquestion tquestion = questionDao.get(hql);
            hql = "from TquestionItems sdy where sdy.tquestion.id='"
                    + questionId + "'";
            List<TquestionItems> tquestionItems = questionItmesDao.find(hql);
            for (TquestionItems tquestionItems2 : tquestionItems) {
                questionItmesDao.delete(tquestionItems2);
            }

            // /*
            // * 如果涉及的题型已经没有其他题目删除题型
            // */
            // Long typeId=tquestion.getTypeId();
            // hql="from Tquestion sdy where sdy.typeId='"+typeId+"'";
            // Tquestion questionInType=questionDao.get(hql);
            // if(questionInType==null){
            // hql="from DataDictionary sdy where sdy.id='"+typeId+"'";
            // DataDictionary questionType=dataDictionaryDao.get(hql);
            // dataDictionaryDao.delete(questionType);
            // }
            // /*
            // * 如果涉及的知识点已经没有其他题目删除知识点
            // */
            // Long subjectId=tquestion.getSubjectId();
            // hql="from Tquestion sdy where sdy.subjectId='"+subjectId+"'";
            // Tquestion questionInSubject=questionDao.get(hql);
            // if(questionInSubject==null){
            // hql="from DataDictionary sdy where sdy.id='"+subjectId+"'";
            // DataDictionary dataDictionary=dataDictionaryDao.get(hql);
            // dataDictionaryDao.delete(dataDictionary);
            // }
            questionDao.delete(tquestion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<QuestionModel> getRequiredQuestions(PageBaseDto pageBaseDto,
            Long subjectId, Long typeId) {
        List<QuestionModel> questions = new ArrayList<QuestionModel>();
        try {
            String hql = "from Tquestion sdy where 1=1 "
                    // 搜索
                    + "and sdy.content like:key "
                    // 只获取必选题
                    + "and sdy.required=:required ";
            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);
            params.put("required", QuestionRequiredEnum.REQUIRED.getRequired());
            if (subjectId != null && subjectId != -1) {
                hql += "and sdy.subjectId=:subjectId ";
                params.put("subjectId", subjectId);
            }
            if (typeId != null && typeId != -1) {
                hql += "and sdy.typeId=:typeId ";
                params.put("typeId", typeId);
            }
            hql += "order by sdy.createTime desc ";
            List<Tquestion> tquestions = questionDao.find(hql, params,
                    pageBaseDto.getPage(), pageBaseDto.getRows());
            for (Tquestion tquestion : tquestions) {
                QuestionModel questionModel = new QuestionModel(tquestion);
                // 获取出题组织
                if (tquestion.getOperatorId() != null) {
                    hql = "from Tmember sdy where sdy.id='"
                            + tquestion.getOperatorId() + "'";
                    Tmember member = memberDao.get(hql);
                    if (member != null) {
                        try {
                            questionModel.setOrganization(member
                                    .getTorganization().getName());
                        } catch (Exception e) {

                        }
                    }
                }

                // 获取选项
                hql = "from TquestionItems sdy where sdy.tquestion.id='"
                        + tquestion.getId() + "' "
                        + "order by sdy.sequence asc";
                List<TquestionItems> tquestionItems = questionItmesDao
                        .find(hql);
                if (tquestionItems.size() > 0) {
                    questionModel.setQuestionItems(QuestionItems
                            .transToQuestionItems(tquestionItems));
                    questionModel.setSelect(true);
                }
                // 获取题型、知识点信息
                hql = "from DataDictionary sdy where sdy.id='"
                        + tquestion.getSubjectId() + "'";
                DataDictionary questionSubject = dataDictionaryDao.get(hql);
                questionModel.setSubjectName(questionSubject.getValueStr());

                hql = "from DataDictionary sdy where sdy.id='"
                        + tquestion.getTypeId() + "'";
                DataDictionary questionType = dataDictionaryDao.get(hql);
                questionModel.setTypeName(questionType.getValueStr());

                questions.add(questionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return questions;
    }

    @Override
    public PageBaseDto getRequiredQuestionsPageBaseDto(PageBaseDto pageBaseDto,
            Long subjectId, Long typeId) {
        try {
            String hql = "select count(*) from Tquestion sdy where 1=1 "
                    // 搜索
                    + "and sdy.content like:key "
                    // 只获取必选题
                    + "and sdy.required=:required ";
            Map<String, Object> params = new HashMap<String, Object>();
            String key = "%" + pageBaseDto.getSearchKey() + "%";
            params.put("key", key);
            params.put("required", QuestionRequiredEnum.REQUIRED.getRequired());
            if (subjectId != null && subjectId != -1) {
                hql += "and sdy.subjectId=:subjectId ";
                params.put("subjectId", subjectId);
            }
            if (typeId != null && typeId != -1) {
                hql += "and sdy.typeId=:typeId ";
                params.put("typeId", typeId);
            }
            Long sum = questionDao.count(hql, params);
            Integer pageNum = (int) (sum % pageBaseDto.getRows() == 0 ? sum
                    / pageBaseDto.getRows() : sum / pageBaseDto.getRows() + 1);
            pageBaseDto.setSum(sum);
            pageBaseDto.setPageNum(pageNum);
            return pageBaseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean setRequiredQuestion(Integer questionId) {
        try {
            String hql = "from Tquestion sdy where sdy.id='" + questionId + "'";
            Tquestion tquestion = questionDao.get(hql);
            tquestion.setRequired(QuestionRequiredEnum.REQUIRED.getRequired());
            questionDao.saveOrUpdate(tquestion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean removeRequiredQuestion(Integer questionId) {
        try {
            String hql = "from Tquestion sdy where sdy.id='" + questionId + "'";
            Tquestion tquestion = questionDao.get(hql);
            tquestion.setRequired(QuestionRequiredEnum.NOTREQUIRED
                    .getRequired());
            questionDao.saveOrUpdate(tquestion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }
}
