package com.zust.itee.dto.request.user;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户搜索 dto
 *
 * @author pojun
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchDto {

    @ApiModelProperty("用户姓名-模糊搜索")
    private String name;

    @ApiModelProperty("组织名称-模糊搜索")
    private String orgName;

    @ApiModelProperty("组织区域id,标准国标码")
    private Long areaId;

    @ApiModelProperty(value = "组织等级code", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty("组织条线id")
    private Long typeId;

    @ApiModelProperty(value = "用户状态,-1-删除,1-正常,0-审核拒绝,2-申请中,-2-账号冻结,3-新注册（个人用户注册后未提交资料审核申请）",
            allowableValues = "-1,1,0,2,-2,3")
    private Short status;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
