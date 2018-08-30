package com.zust.itee.controller.base;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.util.UploadPathUtil;

/**
 * Title: 蓝拓易<br>
 * Description: 文件上传<br>
 * Copyright: Copyright (c) 2009<br>
 * Company: DCS<br>
 *
 * @author alsa
 * @version 1.0
 */
@Controller("commonUpload2.0")
public class CommonUpload {

    /**
     * 允许上传文件的类型
     */
    public static final String[] ALLOWEDTYPES = new String[] { "jpg", "png",
            "gif", "bmp", "txt", "doc", "docx", "xls", "xlsx", "pdf" };

    /**
     * 上传文件　并返回文件名，　多个文件　其文件名以“#”隔开 文件名保存格式：登陆名_时间戳.postfix
     *
     * @param request 包含文件域的客户端请求
     * @param moduleName 文件保存目录路径： 1、所有上传文件的根为webapp下的uploadFile 2、机构和企业会员的的放在orgnization子目录下
     * 3、员工的放在member子目录下 4、质检报告放在report子目录下 5、用户上传的报告放在
     * @param limitSize 文件大小限制
     */
    public static String upload(HttpServletRequest request, String moduleName,
            int limitSize, String[] allowTypes) throws Exception {

        String fileNames = "";
        /** 　保存路径，这个是大的临时文件路径，请根据需要放置到自己的私有文件夹中 */
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置文件体积的最大值
        upload.setSizeMax(limitSize);
        upload.setHeaderEncoding("utf-8");
        // 取出所有上传的文件
        @SuppressWarnings("unchecked")

        List<FileItem> fileItems = upload.parseRequest(request);
        // 文件名
        String prefix = "null";
        SessionInfo sessionInfo = (SessionInfo) request.getSession()
                .getAttribute("sessionInfo");
        if (sessionInfo != null) {

            prefix = sessionInfo.getUserId().toString();
            //			prefix = sessionInfo.getOrganizationId().toString();
        }
        prefix += "_";
        String name = "";
        // 文件后缀
        String postfix = "";
        // 开始循环，取出所有文件
        for (FileItem item : fileItems) {
            if (!item.isFormField()) {
                // 获取文件名
                name = item.getName();
                // 获取文件后缀
                boolean isP = false;
                postfix = name.substring(name.lastIndexOf(".") + 1,
                        name.length());
                if (allowTypes == null || allowTypes.length == 0) {
                    allowTypes = ALLOWEDTYPES;
                }
                for (int i = 0; i < allowTypes.length; i++) {
                    if (allowTypes[i].equals(postfix)) {
                        isP = true;
                        break;
                    } else {
                        if (i == allowTypes.length - 1) {
                            isP = false;
                        } else {
                            continue;
                        }
                    }
                }
                if (!isP) {
                    throw new BusinessException(
                            String.format("上传文件类型不支持，仅支持 %s 文件", Arrays.toString(allowTypes)));
                }

                // 文件以日期形式命名新文件名　+　文件后缀名
                name = prefix + System.currentTimeMillis() + "." + postfix;
                // 输出文件
                // fileNames +=UploadPathUtil.virtualRoot+"/"+moduleName+"/"+
                // name + "#";
                // 斜杠反过来
                fileNames += UploadPathUtil.virtualRoot + "/" + moduleName
                        + "/" + name + "#";
                String savePath = UploadPathUtil.getWebRoot(request) + "/"
                        + UploadPathUtil.virtualRoot + "/" + moduleName + "/";
                File file = new File(savePath + "/" + name);
                file.getParentFile().mkdirs();
                item.write(file);
            }
        }
        if (fileNames.contains("#")) {
            fileNames = fileNames.substring(0, fileNames.length() - 1);
        }
        return fileNames;
    }
}
