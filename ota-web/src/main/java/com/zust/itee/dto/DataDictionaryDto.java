package com.zust.itee.dto;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典 dto
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDictionaryDto {

    private Long id;

    private String remark;

    private Long actionBy;

    private Date actionTime;

    private Long uplink;

    private Long ctrlId;

    private String codeType;

    private String subCodeType;

    private Integer level;

    private Long globalSeq;

    private String valueStr;

    private String valueRule;

    private String link;

    private Long rank;

    private String picPath;

    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
