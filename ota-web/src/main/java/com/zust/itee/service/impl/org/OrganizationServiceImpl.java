package com.zust.itee.service.impl.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.org.Organization;
import com.zust.itee.mapper.org.OrganizationMapper;
import com.zust.itee.service.org.OrganizationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 组织service实现
 *
 * @author pojun
 */
@Service("ota-organizationService")
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public Integer upsert(Organization organization) {
        log.info("==插入或更新组织== org:{}", organization);
        if (organization.getId() == null || getById(organization.getId()) == null) {
            log.info("==插入或更新组织，插入组织== org:{}", organization);
            return organizationMapper.save(organization);
        } else {
            log.info("==插入或更新组织，更新组织== org:{}", organization);
            return organizationMapper.update(organization);
        }
    }

    @Override
    public Organization getById(Integer id) {
        log.info("==根据id获取组织== id:{}", id);
        return organizationMapper.getById(id);
    }

    @Override
    public List<Organization> listByFilter(Organization organization, Integer page, Integer limit)
            throws RuntimeException {
        Map<String, Object> params = new HashMap<>();
        Integer offset = (page - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        params.putAll(JSONObject.parseObject(JSONObject.toJSONString(organization)));
        return organizationMapper.listByFilter(params);
    }

    @Override
    public Long countByFilter(Organization organization) {
        return organizationMapper.countByFilter(organization);
    }

    @Override
    public Organization getParentOrg(Integer id) {
        log.info("==根据id获取父级组织== id:{}", id);
        Organization org = getById(id);
        validateOrgNotNull(org, id);
        Integer parentId = org.getParentId();
        return parentId == null ? null : getById(parentId);
    }

    @Override
    public List<Organization> getSubOrgs(Integer id, Integer page, Integer limit)
            throws RuntimeException {
        log.info("==根据id获取子组织== id:{}", id);
        Organization org = getById(id);
        validateOrgNotNull(org, id);
        return listByFilter(Organization.builder().parentId(id).build(), page, limit);
    }

    /**
     * 校验组织不为空
     */
    private void validateOrgNotNull(Organization organization, Integer id) {
        if (organization == null) {
            log.warn("==当前组织不存在== id:{}", id);
            throw new RuntimeException(String.format("无效组织id:%s", id));
        }
    }
}
