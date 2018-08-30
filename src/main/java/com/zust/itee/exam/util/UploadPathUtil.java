package com.zust.itee.exam.util;

import javax.servlet.http.HttpServletRequest;

public class UploadPathUtil {
    public static final String ONLINEREPORTPATH = "onlinereport";
    public static final String OFFLINEREPORTPATH = "offlinereport";
    public static final String ORGIMAGESPATH = "orgimages";
    public static final String MEMBERPATH = "member";
    public static final String SERVICEPATH = "service";
    public static final String NEWSPATH = "news";
    public static final String DDPATH = "dd";
    public static final String ELIMSPATH = "elims";


    public static final String virtualRoot = "uploadFile";

    /*
     * driverhr
     *
     *
     */
    public static final String ORGNIZATIONPATH = "orgnization";

    public static final String SCOREEXCELPATH = "exam/score";

    public static final String CERTIFICATEEXCELPATH = "exam/certificate";

    public static final String CERTIFICATEPHOTOPATH = "exam/certificate/photo";

    public static final String QUESTIONEXCELPATH = "exam/question";

    public static final String QUESTIONPICPATH = "exam/question/pic";

    public static final String QUESTIONITEMPICPATH = "exam/question/itmes/pic";

    public static final String EXCEL = "excel";


    public static String getWebRoot(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }
}
