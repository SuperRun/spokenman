package com.zust.itee.entity.training;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 培训实体类
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {

    private Integer id;

    private String name;

    private Integer orgId;

    private Integer userId;

    private String userName;

    private Short requiredScore;

    private Short optionalScore;

    private Date startTime;

    private Date endTime;

    private Date signStartTime;

    private Date signEndTime;

    private Short level;

    private Long typeId;

    private String description;

    private Date createTime;

    private Short status;

    private String pic;
}
