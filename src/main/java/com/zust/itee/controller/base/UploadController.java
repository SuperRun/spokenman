package com.zust.itee.controller.base;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.util.UploadPathUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: 蓝拓易<br>
 * Description: 文件上传<br>
 * Copyright: Copyright (c) 2009<br>
 * Company: DCS<br>
 *
 * @author alsa
 * @version 1.0
 */
@Controller("ota-uploadController")
@RequestMapping("/up")
@Slf4j
public class UploadController {

    private static final String[] VIDEO_TYPES = (String[]) Arrays.asList("avi", "mp4", "wmv", "asf",
            "asx", "rm", "rmvb", "3gp", "mov", "m4v", "dat", "mkv", "flv", "vob", "doc").toArray();

    public static JsonResponse<String> upload(HttpServletRequest request,
            String module, int size, String[] allowTypes) {
        try {
            String files = CommonUpload.upload(request, module, size, allowTypes);
            return JsonResponse.success(files);
        } catch (SizeLimitExceededException sizeE) {
            return JsonResponse.error("文件大小超过限制：" + size / 1000 + "k");
        } catch (Exception e) {
            log.error("==文件保存失败== e:{}", e);
            return JsonResponse.error("文件保存失败");
        }
    }

    @PostMapping("/video")
    public JsonResponse<String> uploadVideo(HttpServletRequest request) {
        return upload(request, UploadPathUtil.VIDEO_PATH, 100 * 1024 * 1000, VIDEO_TYPES);
    }
}
