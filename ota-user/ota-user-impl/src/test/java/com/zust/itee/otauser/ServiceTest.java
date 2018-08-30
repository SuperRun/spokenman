package com.zust.itee.otauser;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.otauser.client.domain.User;
import com.zust.itee.otauser.client.domain.UserApply;
import com.zust.itee.otauser.client.service.UserApplyService;
import com.zust.itee.otauser.client.service.UserService;
import com.zust.itee.otauser.impl.OtaUserImplApplication;

/**
 * service 测试
 *
 * @author pojun
 */
@SpringBootTest(classes = OtaUserImplApplication.class)
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Resource
    private UserApplyService userApplyService;

    @Resource
    private UserService userService;

    @Test
    public void normalTest() {
        UserApply userApply = new UserApply().builder()
                .userId(1)
                .orgId(1)
                .status((short) 1)
                .build();
        userApplyService.upsert(userApply);
        System.out.println(userApply);
        userApply.setStatus((short) 2);
        userApplyService.upsert(userApply);

        UserApply query = UserApply.builder().orgId(1).build();
        List<UserApply> res = userApplyService.listByFilter(query, 1, Integer.MAX_VALUE);
        System.out.println(JSONObject.toJSONString(res));

        System.out.println(userApplyService.countByFilter(query));
    }

    @Test
    public void search() {
        User user = User.builder()
                .orgId(1)
                .areaId(3L)
                .type((short) 1)
                .level((short) 2)
                .typeId(1L)
                .name("o")
                .build();
        List<User> res = userService.listBySearch(user, 1, Integer.MAX_VALUE);
        Long count = userService.countBySearch(user);
        System.out.println(JSONObject.toJSONString(res));
        System.out.println(count);
    }
}
