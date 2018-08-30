package com.zust.itee.exam.service;

import java.util.List;

import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.entity.Tannouncement;

public interface AnnouncementService extends BaseService<Tannouncement> {

    /**
     * 加工成Model
     *
     * @author dxb
     * @why
     */
    AnnouncementDto transToModel(Tannouncement tannouncement);

    Tannouncement tranToTannouncement(AnnouncementDto announcementDto);

    List<AnnouncementDto> transToModel(List<Tannouncement> tannouncement);

    AnnouncementDto getDtoById(Integer id);

    boolean deleteAnn(Integer id);

    Integer createAnn(AnnouncementDto announcementDto);

    boolean updateAnn(Integer id, AnnouncementDto announcementDto);

    /**
     * 通过创建人所在的orgId获取 通知
     *
     * @author ly
     * @why
     */
    List<Tannouncement> getAnnouncementByOrgId(Integer orgId, short announcementType);

    List<Tannouncement> getTannouncementByType(short type);

    List<Tannouncement> getAllTannouncement();

    List<AnnouncementDto> getAnnouncementByType(short type);

    List<AnnouncementDto> getAllAnnouncement();
}
