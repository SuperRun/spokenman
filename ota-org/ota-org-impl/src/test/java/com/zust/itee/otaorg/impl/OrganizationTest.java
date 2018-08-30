package com.zust.itee.otaorg.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zust.itee.otaorg.client.domain.Organization;
import com.zust.itee.otaorg.client.service.OrganizationService;

/**
 * TODO 类描述
 *
 * @author pojun
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationTest {

    @Resource
    private OrganizationService organizationService;

    @Test
    public void orgTest() {
        // Organization organization = Organization.builder().name("测试组织").shortName("测试")
        //         .userId(1).level((short) 1).type(1L).phone("123").email("123")
        //         .creatorId(1).build();
        // Integer orgId = organizationService.save(organization);
        // System.out.println(orgId);

        Organization organization = Organization.builder().email("123").build();
        // List<Organization> res = organizationService.listByFilter(organization, 1,
        //         Integer.MAX_VALUE);
        // System.out.println(JSONObject.toJSONString(res));

        System.out.println(organizationService.countByFilter(organization));
    }
}
