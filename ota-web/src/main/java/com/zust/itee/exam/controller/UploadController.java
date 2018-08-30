package com.zust.itee.exam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zust.itee.exam.controller.base.CommonUpload;
import com.zust.itee.exam.util.UploadPathUtil;

/**
 * Title: 蓝拓易<br>
 * Description: 文件上传<br>
 * Copyright: Copyright (c) 2009<br>
 * Company: DCS<br>
 *
 * @author alsa
 * @version 1.0
 */
@Controller
@RequestMapping("/up")
public class UploadController {

    /**
     * 临时路径
     */
    public static final String TEMPDIR = "D:\\tmp";
    /**
     * 限制文件大小
     */
    private static int LIMITSIZE = 10 * 1024 * 1000;
    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    public static Map<String, Object> upload(HttpServletRequest request,
            String module, int size) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String files = CommonUpload.upload(request, module, size);
            map.put("success", true);

            map.put("files", files);
            return map;
        } catch (SizeLimitExceededException sizeE) {
            //logger.error("文件大小超过限制：" + size, sizeE);
            map.put("errorInfo", "文件大小超过限制：" + size / 1000 + "k");
            map.put("success", false);
            return map;
        } catch (Exception e) {
            //logger.error("文件保存失败", e);
            map.put("errorInfo", "文件保存失败");
            map.put("success", false);
            return map;
        }
    }

    /**
     * 上传营业执照扫描件
     *
     * @author dxb
     * @why 创建、修改车企时须有营业执照扫描件
     */
    @RequestMapping("/businessphoto")
    @ResponseBody
    public Map<String, Object> uploadBusinessPhoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, LIMITSIZE);
    }

    @RequestMapping("/excel")
    @ResponseBody
    public Map<String, Object> uploadExcel(HttpServletRequest request) {
        return upload(request, UploadPathUtil.EXCEL, LIMITSIZE);
    }

    /**
     * 上传税务登记证扫描件
     *
     * @author dxb
     * @why 创建、修改车企时须有营业执照扫描件
     */
    @RequestMapping("/taxcodephoto")
    @ResponseBody
    public Map<String, Object> uploadTaxCodePhoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, LIMITSIZE);
    }

    /**
     * 上传机构信用证
     *
     * @author dxb
     * @why 创建、修改车企时须有营业执照扫描件
     */
    @RequestMapping("/creditcodephoto")
    @ResponseBody
    public Map<String, Object> uploadcreditcodephoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, LIMITSIZE);
    }

    /**
     * 上传组织机构证
     *
     * @author dxb
     * @why
     */
    @RequestMapping("/orgcodephoto")
    @ResponseBody
    public Map<String, Object> uploadorgcodephoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, LIMITSIZE);
    }

    /**
     * 上传安全生产许可证
     *
     * @author dxb
     * @why 创建、修改车企时须有营业执照扫描件
     */
    @RequestMapping("/safeproductionlicensephoto")
    @ResponseBody
    public Map<String, Object> uploadsafeproductionlicensephoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, LIMITSIZE);
    }

    /**
     * 成绩批量导入excel上传
     *
     * @author sdy
     * @why
     */
    @RequestMapping("/scoreExcel")
    @ResponseBody
    public Map<String, Object> uploadScoreExcel(HttpServletRequest request) {
        return upload(request, UploadPathUtil.SCOREEXCELPATH, LIMITSIZE);
    }

    /**
     * @author sdy
     * @why 证书录入excel上传
     */
    @RequestMapping("/certificateExcel")
    @ResponseBody
    public Map<String, Object> uploadCertificateExcel(HttpServletRequest request) {
        return upload(request, UploadPathUtil.CERTIFICATEEXCELPATH, LIMITSIZE);
    }

    /**
     * @author sdy
     * @why 上传证书扫描件照片
     */
    @RequestMapping("/certificatePhoto")
    @ResponseBody
    public Map<String, Object> uploadCertificatePhoto(HttpServletRequest request) {
        return upload(request, UploadPathUtil.CERTIFICATEPHOTOPATH, LIMITSIZE);
    }

    /**
     * @author sdy
     * @why 上传批量导入题目excel
     */
    @RequestMapping("/questionExcel")
    @ResponseBody
    public Map<String, Object> uploadQuestionExcel(HttpServletRequest request) {
        return upload(request, UploadPathUtil.QUESTIONEXCELPATH, LIMITSIZE);
    }

    /**
     * @author sdy
     * @why 上传题干图片
     */
    @RequestMapping("/questionPic")
    @ResponseBody
    public Map<String, Object> uploadQuestionPic(HttpServletRequest request) {
        return upload(request, UploadPathUtil.QUESTIONPICPATH, LIMITSIZE);
    }

    /**
     * @author sdy
     * @why 上传题目选项图片
     */
    @RequestMapping("/questionItemPic")
    @ResponseBody
    public Map<String, Object> uploadQuestionItemPic(HttpServletRequest request) {
        return upload(request, UploadPathUtil.QUESTIONITEMPICPATH, LIMITSIZE);
    }

    /**
     * 上传演示页面
     */
    @RequestMapping(value = "/upPage")
    public String inUpload(HttpServletRequest request) {
        return "/upload";
    }

    /**
     * 演示页面图片上传
     */
    @RequestMapping(value = "/test")
    public
    @ResponseBody
    Map<String, Object> test(HttpServletRequest request) {
        return upload(request, "test", LIMITSIZE);
    }

    /**
     * 在线报告上传
     */
    @RequestMapping(value = "/uploadOnlineReport")
    public
    @ResponseBody
    Map<String, Object> uploadOnlineReport(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ONLINEREPORTPATH, 5 * 1024 * 1000);
    }

    @RequestMapping(value = "/uploadNewsPicture")
    public
    @ResponseBody
    Map<String, Object> uploadNewsPicture(HttpServletRequest request) {
        return upload(request, UploadPathUtil.NEWSPATH, 1 * 1024 * 1000);
    }

    /**
     * 机构发布服务贴本地图片上传
     */
    @RequestMapping(value = "/uploadServicePicture")
    public
    @ResponseBody
    Map<String, Object> uploadServicePicture(HttpServletRequest request) {
        return upload(request, UploadPathUtil.SERVICEPATH, 1 * 1024 * 1000);
    }

    @RequestMapping(value = "/uploadDDPicture")
    public
    @ResponseBody
    Map<String, Object> uploadDDPicture(HttpServletRequest request) {
        return upload(request, UploadPathUtil.DDPATH, 1 * 1024 * 1000);
    }

    /**
     * 机构相关图片上传(机构账号图像/认证相关照片等)
     */
    @RequestMapping(value = "/uploadOrgnizationPic")
    public
    @ResponseBody
    Map<String, Object> uploadOrgnizationPic(HttpServletRequest request) {
        return upload(request, UploadPathUtil.ORGNIZATIONPATH, 1 * 1024 * 1000);
    }

    @RequestMapping(value = "/uploadElims")
    public
    @ResponseBody
    Map<String, Object> uploadElims(HttpServletRequest request) {
        System.out.println("上传到检啦收到请求");
        return upload(request, UploadPathUtil.ELIMSPATH, 20 * 1024 * 1000);
    }
}
