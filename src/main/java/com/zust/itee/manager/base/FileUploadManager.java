package com.zust.itee.manager.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zust.itee.dto.VideoResponse;

/**
 * 文件上传 manager
 *
 * @author pojun
 */
public interface FileUploadManager {

    /**
     * 视频上传
     */
    VideoResponse uploadVideo(MultipartFile file, HttpServletRequest request) throws Exception;

    /**
     * 图片上传
     */
    String uploadPic(MultipartFile file) throws Exception;
}
