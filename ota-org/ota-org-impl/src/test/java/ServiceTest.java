import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zust.itee.otaorg.client.domain.Organization;
import com.zust.itee.otaorg.client.service.OrganizationService;
import com.zust.itee.otaorg.impl.OtaOrgImplApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * service 测试
 *
 * @author pojun
 */
@SpringBootTest(classes = OtaOrgImplApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceTest {

    @Resource
    private OrganizationService organizationService;

    @Test
    public void normalTest() {
        // Organization organization = Organization.builder()
        //         .name("浙江省安全生产厅")
        //         .shortName("浙安厅")
        //         .email("asd12")
        //         .creatorId(1)
        //         .phone("123123")
        //         .level((short) 1)
        //         .userId(1)
        //         .areaId(6L)
        //         .typeId(171L)
        //         .status((short) 1)
        //         .build();
        // organizationService.upsert(organization);
        // Integer pid = organization.getId();
        //
        // organization = Organization.builder()
        //         .name("杭州市安全生产局")
        //         .shortName("杭安局")
        //         .email("asd12")
        //         .creatorId(1)
        //         .phone("123123")
        //         .level((short) 1)
        //         .userId(1)
        //         .areaId(10L)
        //         .typeId(171L)
        //         .parentId(pid)
        //         .status((short) 1)
        //         .build();
        // organizationService.upsert(organization);
        // Integer cid = organization.getId();
        //
        // organization = Organization.builder()
        //         .name("西湖区安全生产办")
        //         .shortName("西湖安办")
        //         .email("asd12")
        //         .creatorId(1)
        //         .phone("123123")
        //         .level((short) 2)
        //         .userId(1)
        //         .areaId(74L)
        //         .typeId(171L)
        //         .parentId(cid)
        //         .status((short) 1)
        //         .build();
        // organizationService.upsert(organization);

        Organization organization = Organization.builder()
                .name("浙江省组织宣传厅")
                .shortName("浙组厅")
                .email("asd12")
                .creatorId(1)
                .phone("123123")
                .level((short) 1)
                .userId(1)
                .areaId(6L)
                .typeId(172L)
                .status((short) 1)
                .build();
        organizationService.upsert(organization);
        Integer pid = organization.getId();

        organization = Organization.builder()
                .name("杭州市组织宣传局")
                .shortName("杭组局")
                .email("asd12")
                .creatorId(1)
                .phone("123123")
                .level((short) 1)
                .userId(1)
                .areaId(10L)
                .typeId(172L)
                .parentId(pid)
                .status((short) 1)
                .build();
        organizationService.upsert(organization);
        Integer cid = organization.getId();

        organization = Organization.builder()
                .name("西湖区组织宣传办")
                .shortName("西湖组办")
                .email("asd12")
                .creatorId(1)
                .phone("123123")
                .level((short) 2)
                .userId(1)
                .areaId(74L)
                .typeId(172L)
                .parentId(cid)
                .status((short) 1)
                .build();
        organizationService.upsert(organization);
    }
}
