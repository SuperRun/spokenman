package com.zust.itee.exam.controller.hr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.controller.UploadController;
import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.entity.Tannouncement;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;
import com.zust.itee.exam.service.AnnouncementService;
import com.zust.itee.exam.service.MemberService;
import com.zust.itee.exam.service.OrganizationService;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfAjax = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    /**
     * 限制文件大小
     */
    private static int LIMITSIZE = 10 * 1024 * 1000;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * 上传通知文件
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/file")
    public @ResponseBody
    Map<String, Object> driverPhoto(HttpServletRequest request) {
        return UploadController.upload(request, "hr/announcement/file", LIMITSIZE);
    }

    /**
     * 新建通知页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/new/{type}", method = RequestMethod.GET)
    public String deliverAnnouncement(
            @PathVariable("type") Short type,
            HttpServletRequest request) {
        request.setAttribute("typeEnum", AnnouncementTypeEnum.stateOf(type));
        return "2/hr/announcement/new";
    }

    /**
     * 新建通知
     *
     * @throws ParseException
     * @author ly
     * @why
     */
    @RequestMapping(value = "/news/{types}", method = RequestMethod.POST)
    @ResponseBody
    public boolean deliverAnnouncements(
            @PathVariable("types") Short type,
            AnnouncementDto announcementDto,
            @RequestParam(value = "closureDateStr", required = false) String closureDateStr,
            SessionInfo sessionInfo
    ) throws ParseException {
        if (type == null) {
            type = 0;
        }
        announcementDto.setType(AnnouncementTypeEnum.stateOf(type));
        if (closureDateStr != null && closureDateStr.length() > 0) {
            announcementDto.setClosureDate(sdfAjax.parse(closureDateStr));
        }
        announcementDto.setCreateDate(new Date());
        //从sessionInfo获取信息
        announcementDto.setMemberId(sessionInfo.getUserId());
        announcementDto.setOrganizationId(sessionInfo.getOrganizationId());
        announcementDto.setReadCount(0);
        Integer annId = announcementService.createAnn(announcementDto);
        if (annId != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新通知页面
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{annouuncementId}/update", method = RequestMethod.GET)
    public String updateAnnouncement(
            @PathVariable("annouuncementId") Integer annouuncementId,
            HttpServletRequest request) {
        AnnouncementDto ann = announcementService.getDtoById(annouuncementId);
        request.setAttribute("ann", ann);
        return "2/hr/announcement/update";
    }

    /**
     * 更新通知
     *
     * @throws ParseException
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{annouuncementId}/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateAnnouncements(
            @PathVariable("annouuncementId") Integer annouuncementId,
            AnnouncementDto announcementDto,
            @RequestParam(value = "closureDateStr", required = false) String closureDateStr,
            SessionInfo sessionInfo
    ) throws ParseException {
        if (closureDateStr != null && closureDateStr.length() > 0) {
            announcementDto.setClosureDate(sdfAjax.parse(closureDateStr));
        }
        announcementDto.setCreateDate(new Date());
        //从sessionInfo获取信息
        announcementDto.setMemberId(sessionInfo.getUserId());
        announcementDto.setOrganizationId(sessionInfo.getOrganizationId());
        announcementDto.setReadCount(0);
        return announcementService.updateAnn(annouuncementId, announcementDto);
    }

    /**
     * 通知详情
     *
     * @author ly
     * @why
     */
    @RequestMapping(value = "/{annouuncementId}", method = RequestMethod.GET)
    public ModelAndView readAnnouncement(
            @PathVariable Integer annouuncementId) {
        ModelAndView mv = new ModelAndView("2/hr/announcement/reading");
        Tannouncement announcement = announcementService.getById(annouuncementId);
        //阅读数+1
        announcement.setReadCount(announcement.getReadCount() + 1);
        announcementService.saveOrupdate(announcement);

        AnnouncementDto announcementDto = announcementService.transToModel(announcement);
        mv.addObject("annDto", announcementDto);
        Torganization org = organizationService.getById(announcementDto.getOrganizationId());
        mv.addObject("org", org);

        return mv;
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public boolean deleteAnn(@PathVariable("id") Integer id) {
        return announcementService.deleteAnn(id);
    }

    @RequestMapping(value = "/list")
    public ModelAndView announcementList() {
        ModelAndView mv = new ModelAndView("2/hr/announcement/list");
        List<AnnouncementDto> announcementDtos = announcementService.getAllAnnouncement();
        mv.addObject("annType", -1);
        mv.addObject("anns", announcementDtos);
        return mv;
    }

    @RequestMapping(value = "/list/{type}")
    public ModelAndView announcementTypeList(@PathVariable("type") Short type) {
        ModelAndView mv = new ModelAndView("2/hr/announcement/list");
        List<AnnouncementDto> announcementDtos = announcementService.getAnnouncementByType(type);
        mv.addObject("annType", type);
        mv.addObject("anns", announcementDtos);
        return mv;
    }
}
