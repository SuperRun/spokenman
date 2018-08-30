package com.zust.itee.exam.dto.exam.member;

import java.util.ArrayList;
import java.util.List;

import com.zust.itee.exam.entity.TquestionItems;

/**
 * 创建题目选项所需信息
 *
 * @author sdy
 */
public class QuestionItems {

    // id
    private Integer id;

    // 序号
    private String sequence;

    // 选项题干
    private String content;

    // 图片
    private String pic;

    //是否被选中
    private Boolean selected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public static List<QuestionItems> transToQuestionItems(
            List<TquestionItems> tquestionItems) {
        List<QuestionItems> questionItems = new ArrayList<QuestionItems>();
        if (tquestionItems.size() > 0) {
            for (int i = 0; i < tquestionItems.size(); i++) {
                QuestionItems questionItems2 = new QuestionItems();
                TquestionItems ori = tquestionItems.get(i);
                questionItems2.setContent(ori.getContent());
                questionItems2.setId(ori.getId());
                questionItems2.setPic(ori.getPic());
                questionItems2.setSequence(String.valueOf((char) (ori
                        .getSequence() - 1 + 'A')));
                questionItems.add(questionItems2);
            }
        }
        return questionItems;
    }
}
