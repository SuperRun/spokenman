package com.zust.itee.entity.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训资源
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingResource {

    private Integer id;

    private Integer trainingId;

    private Integer resourceId;

    private String resourceName;

    private Short required;

    private Short score;

    private Short status;

    private String remark;

    private String lecturerName;

    private Long typeId;

    private Short level;
}
