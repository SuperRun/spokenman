package com.zust.itee.entity.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户资源查看记录
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResource {

    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String time;

    private Short score;

    private Date createTime;

    private Date updateTime;

    private Short status;
}
