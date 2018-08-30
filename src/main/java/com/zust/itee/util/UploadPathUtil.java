package com.zust.itee.util;

import javax.servlet.http.HttpServletRequest;

public class UploadPathUtil {

    public static final String virtualRoot = "uploadFile";

    public static final String VIDEO_PATH = "video";

    public static String getWebRoot(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }
}
