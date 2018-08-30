package com.zust.itee.dto.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.enums.LoginTypeEnum;
import com.zust.itee.exam.enums.OrganizationTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * session 信息
 *
 * @author pojun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("session 信息")
public class SessionInfo implements Serializable {

    @ApiModelProperty("考试系统用户id")
    private Integer userId;

    @ApiModelProperty("培训平台用户id")
    private Integer otaUserId;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户所属组织id")
    private Integer orgId;

    @ApiModelProperty("用户组织名称")
    private String orgName;

    @ApiModelProperty(value = "用户类型。0：平台用户，1：个人用户，2：单位用户，3：讲师")
    private Short type;

    @ApiModelProperty(value = "组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级", allowableValues = "1,2,3,4,5")
    private Short level;

    @ApiModelProperty(value = "用户状态。-1：删除，1：正常，0：审核拒绝，2：申请中。3：新注册（个人用户未提交材料申请）,-2:账号冻结",
            allowableValues = "-1,1,0,2,3,-2")
    private Short status;
    /*
     * 真实姓名 organization登录时为联系人的真实姓名，member登录时为登录者的真实姓名
     */
    private String realName;

    /*
     * 所属机构id
     */
    private Integer organizationId;

    /*
     * 所属机构的名称
     */
    private String organizationName;

    /*
     * 机构类型
     */
    private OrganizationTypeEnum organizationType;

    /*
     * 所属部门id organization登录时为null，member登录时为其所属部门id
     */
    private Integer departmentId;

    /*
     * 所属部门名称 organization登录时为null，member登录时为其所属部门名称
     */
    private String departmentName;

    /*
     * 登录名 ，member登录时为loginName ，driver时为 手机号
     */
    private String loginName;

    /*
     * 登录类型 0表示member，1表示driver
     */
    private LoginTypeEnum loginType;

    // 权限信息
    private String ip;// IP地址
    private String ua;//user agent +

    private String authIds;// 拥有的权限ID集合
    private String authNames;
    private String authUrls;
    private String roleIds;
    private String roleNames;

    public SessionInfo(Integer userId) {
        this.userId = userId;
    }

    public SessionInfo(Tmember tmember) {
        this.userId = tmember.getId();
        this.realName = tmember.getName();
        this.loginName = tmember.getLoginName();
        this.loginType = LoginTypeEnum.MEMBER;
        if (tmember.getTorganization() != null) {
            this.organizationId = tmember.getTorganization().getId();
            this.organizationName = tmember.getTorganization().getName();
            this.organizationType = OrganizationTypeEnum.stateOf(tmember
                    .getTorganization().getType());
        }
    }

    public SessionInfo(Tmember tmember, String ip) {
        this.userId = tmember.getId();
        this.realName = tmember.getName();
        this.loginName = tmember.getLoginName();
        this.loginType = LoginTypeEnum.MEMBER;
        if (tmember.getTorganization() != null) {
            this.organizationId = tmember.getTorganization().getId();
            this.organizationName = tmember.getTorganization().getName();
            this.organizationType = OrganizationTypeEnum.stateOf(tmember
                    .getTorganization().getType());
        }
        this.ip = ip;
    }

    public SessionInfo(Tdriver tdriver) {
        this.userId = tdriver.getId();
        this.realName = tdriver.getName();
        this.loginName = tdriver.getMobile();
        this.loginType = LoginTypeEnum.DRIVER;
        if (tdriver.getTorganization() != null) {
            this.organizationId = tdriver.getTorganization().getId();
            this.organizationName = tdriver.getTorganization().getName();
            this.organizationType = OrganizationTypeEnum.stateOf(tdriver
                    .getTorganization().getType());
        }
    }

    public SessionInfo(Tdriver tdriver, String ip) {
        this.userId = tdriver.getId();
        this.realName = tdriver.getName();
        this.loginName = tdriver.getMobile();
        this.loginType = LoginTypeEnum.DRIVER;
        if (tdriver.getTorganization() != null) {
            this.organizationId = tdriver.getTorganization().getId();
            this.organizationName = tdriver.getTorganization().getName();
            this.organizationType = OrganizationTypeEnum.stateOf(tdriver
                    .getTorganization().getType());
        }
        this.ip = ip;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
