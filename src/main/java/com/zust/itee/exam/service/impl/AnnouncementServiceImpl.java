package com.zust.itee.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.dto.AnnouncementDto;
import com.zust.itee.exam.entity.Tannouncement;
import com.zust.itee.exam.entity.Tmember;
import com.zust.itee.exam.entity.Torganization;
import com.zust.itee.exam.enums.AnnouncementStatusEnum;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;
import com.zust.itee.exam.service.AnnouncementService;

@Service
@Transactional
public class AnnouncementServiceImpl extends BaseServiceImpl<Tannouncement>
        implements AnnouncementService {

    @Autowired
    private BaseDao<Torganization> organizationDao;

    @Autowired
    private BaseDao<Tmember> memberDao;

    @Autowired
    private BaseDao<Tannouncement> announcementDao;

    @Override
    public List<Tannouncement> getAnnouncementByOrgId(Integer orgId, short announcementType) {

		/*String hql = "from Tannouncement a where a.type like " + "'%" + orgTypes + "%'";

		if (isDriver) {

			Torganization org = organizationDao.get(Torganization.class, OrgId);
			Integer OrgId2 = org.getTorganization().getId();
			Integer OrgId3 = org.getTorganization().getTorganization().getId();
			Integer OrgId4 = org.getTorganization().getTorganization().getTorganization().getId();

			hql += " and ((a.torganization.type = 3 and a.torganization.id = " + OrgId + ")";
			hql += " or (a.torganization.type = 2 and a.torganization.id = " + OrgId2 + ")";
			hql += " or (a.torganization.type = 1 and a.torganization.id = " + OrgId3 + ")";
			hql += " or (a.torganization.type = 0 and a.torganization.id = " + OrgId4 + ")";
			hql += ")";

		} else {
			hql += " and a.torganization.id = " + OrgId;
		}

		hql += " order by a.createDate desc";
		return announcementDao.find(hql);*/
        String hql = "from Tannouncement a where a.type like " + "'%" + announcementType + "%'";
        hql += " and a.status = 1 and (";
        hql += "(a.torganization.id = :o )";
        hql += " or (a.torganization.torganization.id = :o )";
        hql += " or (a.torganization.torganization.torganization.id = :o )";
        hql += " or (a.torganization.torganization.torganization.torganization.id = :o )";
        hql += " )";
        hql += " order by a.createDate desc";
        Map<String, Object> params = new HashMap<>();
        params.put("o", orgId);

        return announcementDao.find(hql, params);
    }

    @Override
    public List<Tannouncement> getTannouncementByType(short type) {
        String hql = " from Tannouncement a where a.type = " + type;
        hql += " and a.status = 1  order by a.createDate desc ";
        return announcementDao.find(hql);
    }

    @Override
    public List<Tannouncement> getAllTannouncement() {
        return announcementDao.find(
                "from Tannouncement a where a.status = 1  order by a.createDate desc ");
    }

    @Override
    public List<AnnouncementDto> getAnnouncementByType(short type) {
        return transToModel(getTannouncementByType(type));
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncement() {
        return transToModel(getAllTannouncement());
    }

    @Override
    public AnnouncementDto transToModel(Tannouncement tannouncement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        BeanUtils.copyProperties(tannouncement, announcementDto);

        Date dateNow = new Date();
        if (tannouncement.getClosureDate() != null && dateNow.after(
                tannouncement.getClosureDate())) {
            announcementDto.setStatus(AnnouncementStatusEnum.OVERDUE);
        } else {
            announcementDto.setStatus(AnnouncementStatusEnum.AVAILABLE);
        }

        //announcementDto.setType(AnnouncementTypeEnum.stateOf(tannouncement.getType()));
        announcementDto.setType(AnnouncementTypeEnum.stateOf(tannouncement.getType()));

        if (tannouncement.getTmember() != null) {
            announcementDto.setMemberId(tannouncement.getTmember().getId());
        }
        if (tannouncement.getTorganization() != null) {
            announcementDto.setOrganizationId(tannouncement.getTorganization().getId());
        }

        return announcementDto;
    }

    @Override
    public Tannouncement tranToTannouncement(AnnouncementDto ann) {
        Tmember tmember = memberDao.get(Tmember.class, ann.getMemberId());
        Torganization torganization = organizationDao.get(Torganization.class,
                ann.getOrganizationId());
        return new Tannouncement(ann.getTitle(), ann.getText(), ann.getCreateDate(),
                ann.getClosureDate(), ann.getAttachment(), ann.getType().getType(),
                ann.getReadCount(),
                tmember, torganization);
    }

    @Override
    public List<AnnouncementDto> transToModel(List<Tannouncement> tannouncements) {
        List<AnnouncementDto> list = new ArrayList<>();
        for (Tannouncement tannouncement : tannouncements) {
            list.add(transToModel(tannouncement));
        }
        return list;
    }

    @Override
    public AnnouncementDto getDtoById(Integer id) {
        Tannouncement ann = announcementDao.get(Tannouncement.class, id);
        return transToModel(ann);
    }

    @Override
    public boolean deleteAnn(Integer id) {
        try {
            Tannouncement ann = announcementDao.get(Tannouncement.class, id);
            ann.setStatus((short) 0);
            announcementDao.update(ann);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer createAnn(AnnouncementDto announcementDto) {
        try {
            Tannouncement tann = tranToTannouncement(announcementDto);
            tann.setStatus((short) 1);
            announcementDao.save(tann);
            return tann.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateAnn(Integer id, AnnouncementDto ann) {
        try {
            Tannouncement tann = announcementDao.get(Tannouncement.class, id);
            tann.setTitle(ann.getTitle());
            tann.setText(ann.getText());
            tann.setClosureDate(ann.getClosureDate());
            tann.setAttachment(ann.getAttachment());
            announcementDao.update(tann);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
