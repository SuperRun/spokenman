package com.zust.itee;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.entity.base.DateFilter;
import com.zust.itee.entity.resource.LearningResource;
import com.zust.itee.entity.user.User;
import com.zust.itee.exam.dao.hibernate.BaseDao;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.service.training.TrainingResourceService;
import com.zust.itee.service.training.TrainingUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OtaWebApplicationTests {

    @Resource
    private BaseDao<Tdriver> driverDao;

    @Resource
    private TrainingUserService trainingUserService;

    @Resource
    private TrainingResourceService trainingResourceService;

    @Test
    @Transactional
    public void hibernateDaoTest() {
        List<Tdriver> tdrivers = driverDao.find("from Tdriver");
        System.out.println(JSONObject.toJSONString(tdrivers));
    }

    @Test
    public void trainingUserTest() {
        User user = new User();
        Integer trainingId = 1;
        Integer page = 1;
        Integer limit = Integer.MAX_VALUE;
        List<User> users = trainingUserService.listUnselectedUserBySearch(user, trainingId, page,
                limit);
        System.out.println(JSONObject.toJSONString(users));
        Long sum = trainingUserService.countUnselectedUserBySearch(user, trainingId);
        System.out.println(sum);

        user.setName("浙");
        users = trainingUserService.listUnselectedUserBySearch(user, trainingId, page,
                limit);
        System.out.println(JSONObject.toJSONString(users));
        sum = trainingUserService.countUnselectedUserBySearch(user, trainingId);
        System.out.println(sum);
    }

    @Test
    public void trainingResourceTest() {
        LearningResource learningResource = new LearningResource();
        Integer trainingId = 1;
        Integer rootOrgId = 1;
        Integer page = 1;
        Integer limit = Integer.MAX_VALUE;
        DateFilter dateFilter = new DateFilter();

        List<LearningResource> learningResources = trainingResourceService.listUnselectedResource(
                learningResource, dateFilter, trainingId, rootOrgId, page, limit);
        System.out.println(JSONObject.toJSONString(learningResources));
        Long sum = trainingResourceService.countUnselectedResource(learningResource, dateFilter,
                trainingId, rootOrgId);
        System.out.println(sum);

        learningResource.setName("宣");
        learningResources = trainingResourceService.listUnselectedResource(
                learningResource, dateFilter, trainingId, rootOrgId, page, limit);
        System.out.println(JSONObject.toJSONString(learningResources));
        sum = trainingResourceService.countUnselectedResource(learningResource, dateFilter,
                trainingId, rootOrgId);
        System.out.println(sum);
    }
}
