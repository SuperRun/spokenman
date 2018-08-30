package com.zust.itee.dto.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户学习资源
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResourceUpdateDto {

    //上次观看至视频时间
    @ApiModelProperty("提交问题的时间")
    private String time;

    private Short score;

    /**
     * 学习状态
     * {@link com.zust.itee.enums.user.UserResourceStatus}
     */
    @ApiModelProperty(value = "状态。1：学习中；2:学习完成", allowableValues = "1,2")
    private Short status;
}
