package com.zust.itee.exam.dto.exam.member;

import java.util.List;

import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.enums.exam.QuestionDifficultyEnum;
import com.zust.itee.exam.util.MyDateUtil;

/**
 * 显示试卷题目
 *
 * @author sdy
 */
public class QuestionModel {

    // id
    private Integer id;

    // 题干
    private String content;

    // 图片
    private String pic1;

    private String pic2;

    private String pic3;

    // 题目类型id
    private long typeId;

    // 题型名
    private String typeName;

    // 题目知识点id
    private long subjectId;

    // 知识点名
    private String subjectName;

    // 题目分数
    private double marks;

    // 题目答案
    private String answer;

    // 难度
    private String difficulty;

    // 是否为选择题
    private boolean select;

    // 是否为必选题
    private Boolean required;

    // 驾驶员之前的答案
    private String answerPre;

    // 答题时提示
    private String help;

    // 发布时间
    private String date;

    // 发布组织
    private String organization;

    // 选项类型(font:文字；picture：图片)
    private String questionItemType;

    // 题目选项
    private List<QuestionItems> questionItems;

    //多答案
    private List<String> answers;

    //创建时间
    private String createTime;

    public QuestionModel() {
        super();
    }

    public QuestionModel(Tquestion tquestion) {
        super();
        this.id = tquestion.getId();
        this.content = tquestion.getContent();
        this.pic1 = tquestion.getPic1();
        this.pic2 = tquestion.getPic2();
        this.pic3 = tquestion.getPic3();
        this.typeId = tquestion.getTypeId();
        this.subjectId = tquestion.getSubjectId();
        this.marks = tquestion.getMarks();
        this.answer = tquestion.getAnswer();
        this.date = MyDateUtil.date2strMDHM(tquestion.getCreateTime());
        this.difficulty = QuestionDifficultyEnum.getIndexOf(
                tquestion.getDifficulty()).getInfo();
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<QuestionItems> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionItems> questionItems) {
        this.questionItems = questionItems;
    }

    @Override
    public String toString() {
        return "QuestionModel [content=" + content + ", pic1=" + pic1
                + ", pic2=" + pic2 + ", pic3=" + pic3 + ", typeId=" + typeId
                + ", subjectId=" + subjectId + ", marks=" + marks + ", answer="
                + answer + ", questionItems=" + questionItems + "]";
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getAnswerPre() {
        return answerPre;
    }

    public void setAnswerPre(String answerPre) {
        this.answerPre = answerPre;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getQuestionItemType() {
        return questionItemType;
    }

    public void setQuestionItemType(String questionItemType) {
        this.questionItemType = questionItemType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
