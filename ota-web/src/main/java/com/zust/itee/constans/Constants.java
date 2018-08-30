package com.zust.itee.constans;

/**
 * 常量
 *
 * @author pojun
 */
public class Constants {

    /**
     * 组织相关
     */
    public static class Org {

        // 平台组织
        public static final Short ROOT_ORG_LEVEL = (short) 1;
        public static final String ROOT_ORG_NAME = "平台组织";
        public static final String ROOT_ORG_SHORT_NAME = "平台";
    }

    /**
     * mapper 相关
     */
    public static class Mapper {

        // 已删除状态
        public static final String DELETED_STATUS = "deletedStatus";

        // 平台组织 id
        public static final String ROOT_ORG_ID = "rootOrgId";

        // 培训 id
        public static final String TRAINING_ID = "trainingId";

        // startTime
        public static final String START_TIME = "startTime";

        // endTime
        public static final String END_TIME = "endTime";
    }
}
