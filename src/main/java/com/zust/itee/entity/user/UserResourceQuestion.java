package com.zust.itee.entity.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户培训资源答题记录
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceQuestion {

    private Integer id;

    private Integer resourceQuestionId;

    private Date createTime;
}
