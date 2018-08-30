package com.zust.itee.entity.datadictionary;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典
 *
 * @author pojun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataDictionary implements Serializable {

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
}
