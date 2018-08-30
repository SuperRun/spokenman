package com.zust.itee.entity.resource;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 讲师
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecturer {

    private Integer id;

    private Integer userId;

    private Date startTime;

    private Date endTime;

    private String name;

    private String introduction;

    private Integer orgId;

    private Long areaId;

    private Short level;

    private Long typeId;

    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
