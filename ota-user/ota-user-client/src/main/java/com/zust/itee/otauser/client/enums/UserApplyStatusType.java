package com.zust.itee.otauser.client.enums;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 用户审批状态枚举
 *
 * @author pojun
 */
public enum UserApplyStatusType {

    APPLYING((short) 1, "申请中"),
    REJECT((short) 0, "拒绝"),
    APPROVE((short) 2, "通过");

    private short status;

    private String description;

    private static final Map<Short, UserApplyStatusType> USER_APPLY_STATUS_TYPE_MAP = Stream.of(
            values()).collect(toMap(UserApplyStatusType::getStatus, statusType -> statusType));

    UserApplyStatusType(short status, String description) {
        this.status = status;
        this.description = description;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
