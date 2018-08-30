package com.zust.itee.entity.training;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训用户
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingUser {

    private Integer id;

    private Integer trainingId;

    private String trainingName;

    private Integer userId;

    private String userName;

    private Short requiredScore;

    private Short optionalScore;

    private String certificateNo;

    private String certificatePhoto;

    private Date createTime;

    private Short status;

    private Long userAreaId;

    private Long userTypeId;

    private Short userLevel;
}
