package com.zust.itee.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传视频响应结果
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoResponse {

    @ApiModelProperty("视频 url")
    private String url;

    @ApiModelProperty("视频时长")
    private Integer duration;
}
