package com.zust.itee.manager.impl.base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zust.itee.dto.VideoResponse;
import com.zust.itee.exception.BusinessException;
import com.zust.itee.manager.base.FileUploadManager;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件上传 manager 实现类
 *
 * @author pojun
 */
@Slf4j
@Service
public class FileUploadManagerImpl implements FileUploadManager {

    // TODO 后续优化为上传到七牛云或其他对象服务器
    private static final String ROOT_PATH = "src/main/webapp";

    private static final String VIDEO_PATH = "/upload/video/";

    private static final String PIC_PATH = "/upload/pic/";

    @Value("${file-upload.video.types}")
    private String videoAllowTypes;

    @Value("${file-upload.pic.types}")
    private String picAllowTypes;

    @Override
    public VideoResponse uploadVideo(MultipartFile file, HttpServletRequest request)
            throws Exception {
        String url = uploadFile(file, VIDEO_PATH, videoAllowTypes);
        Integer duration = getVideoDuration(url, request);
        return VideoResponse.builder()
                .url(url)
                .duration(duration)
                .build();
    }

    @Override
    public String uploadPic(MultipartFile file) throws Exception {
        return uploadFile(file, PIC_PATH, picAllowTypes);
    }

    private String uploadFile(MultipartFile file, String subPath, String allowTypes)
            throws Exception {
        if (!file.isEmpty()) {
            validateType(file.getOriginalFilename(), allowTypes);
            String saveFileName = file.getOriginalFilename();
            String newFilePath = subPath + System.currentTimeMillis() + saveFileName;
            File saveFile = new File(
                    ROOT_PATH + newFilePath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return newFilePath;
            } catch (FileNotFoundException e) {
                log.error("==上传失败== e:{}", e);
                throw e;
            } catch (IOException e) {
                log.error("==上传失败== e:{}", e);
                throw e;
            }
        } else {
            throw new BusinessException("上传失败，文件为空");
        }
    }

    /**
     * 获取视频文件时长
     */
    private Integer getVideoDuration(String fileUrl, HttpServletRequest request) {
        String paths = request.getServletContext().getRealPath("/");
        paths = paths.replaceAll("\\\\", "/");
        paths += fileUrl.substring(1, fileUrl.length());
        File source = new File(paths);
        Encoder encoder = new Encoder();
        Long duration = 0L;
        try {
            MultimediaInfo multimediaInfo = encoder.getInfo(source);
            duration = multimediaInfo.getDuration() / 1000;
        } catch (EncoderException e) {
            log.error("==解析视频文件失败== e:{}", e);
        }
        return duration.intValue();
    }

    private void validateType(String originalName, String allowTypes) {
        String[] allowTypesArray = allowTypes.split(",");
        String fileType = originalName.substring(originalName.lastIndexOf(".") + 1,
                originalName.length());
        Long match = Arrays.stream(allowTypesArray).filter(type -> type.equals(fileType)).count();
        if (match <= 0) {
            throw new BusinessException(
                    "文件类型不符合，仅允许上传 " + Arrays.toString(allowTypesArray) + " 类型文件");
        }
    }
}
