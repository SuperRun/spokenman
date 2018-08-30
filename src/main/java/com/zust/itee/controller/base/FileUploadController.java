package com.zust.itee.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zust.itee.dto.VideoResponse;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.manager.base.FileUploadManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传 controller
 *
 * @author pojun
 */
@RestController
@RequestMapping("file-upload")
@Api(tags = "文件上传相关接口")
public class FileUploadController {

    @Resource
    private FileUploadManager fileUploadManager;

    @ApiOperation(
            "上传视频-允许文件类型见 application.properties file-upload.video.types。上传后文件存储于 "
                    + "/src/main/resources/static/upload/video/")
    @PostMapping("/video")
    public JsonResponse<VideoResponse> videoUpload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request)
            throws Exception {
        return JsonResponse.success(fileUploadManager.uploadVideo(file, request));
    }

    @ApiOperation(
            "上传图片-允许文件类型 见application.properties file-upload.pic.types。上传后文件存储于 "
                    + "/src/main/resources/static/upload/video/")
    @PostMapping("/pic")
    public JsonResponse<String> picUpload(@RequestParam("file") MultipartFile file)
            throws Exception {
        return JsonResponse.success(fileUploadManager.uploadPic(file));
    }
}
