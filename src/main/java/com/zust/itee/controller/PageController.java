package com.zust.itee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.enums.user.UserStatusType;
import com.zust.itee.enums.user.UserType;

import lombok.extern.slf4j.Slf4j;

@Controller("ota-pageController")
@Slf4j
public class PageController {

    @RequestMapping("")
    public String index() {
        return "forward:/index";
    }

    /**
     * 平台及单位对用户管理相关页面
     */
    @RequestMapping("/index")
    public String homepage() {
        log.info("进入首页");
        return "index";
    }

    @RequestMapping("/users/login")
    public String login() {
        log.info("进入登录页 ");
        return "login";
    }

    @RequestMapping("/users/regist")
    public String regist() {
        log.info("进入注册页 ");
        return "regist";
    }

    @RequestMapping("/platform/unitUser")
    public String unitUserManagePage() {
        log.info("平台进入单位用户管理页面");
        return "user/unitUser";
    }

    @RequestMapping("/platform/perUser")
    public String perUserManagePage() {
        log.info("平台进入个人用户管理页面");
        return "user/perUser";
    }

    @RequestMapping("/platform/checkUser/{userId}")
    public String checkUser() {
        log.info("平台进入审核用户信息页面");
        return "user-apply/checkUser";
    }

    @RequestMapping("/platform/lecUser")
    public String lecUser() {
        log.info("平台进入讲师管理页面");
        return "user/lecUser";
    }

    @GetMapping("/platform/getExprience/{userId}")
    public String getExprience() {
        log.info("平台获取某个讲师的所有经历");
        return "user/lecturer";
    }

    @RequestMapping("/platform/addLecturer")
    public String addLecturer() {
        log.info("平台进入新增讲师经历的页面");
        return "user/addLecturer";
    }

    @GetMapping("/platform/editLecturer/{lecId}")
    public String editLecturer() {
        log.info("平台进入修改讲师经历的页面");
        return "user/editLecturer";
    }

    @GetMapping("/puUser/exam")
    public String exam() {
        log.info("管理员进入考试页面");
        return "redirect:/exam/member/home";
    }

    @GetMapping("/puUser/question")
    public String question() {
        log.info("管理员进入题目页面");
        return "redirect:/question/list";
    }

    @RequestMapping("/unitUser/listUser")
    public String unitUserGetUser() {
        log.info("单位用户进入用户管理页面");
        return "user/unitUser/listUser";
    }

    @RequestMapping("/userType")
    public String userType(SessionInfo sessionInfo) {
        log.info("中间页面");
        if (sessionInfo == null) {
            return "redirect:/index";
        }

        Short type = sessionInfo.getType();
        Short status = sessionInfo.getStatus();
        if (UserType.ROOT.getValue() == type) {
            return "redirect:/index";
        } else if (UserType.PERSONAL.getValue() == type || UserType.COMPANY.getValue() == type) {
            String page = "redirect:/perUser/resIndex";
            if (UserStatusType.NORMAL.getStatus() != status) {
                page = "redirect:/perUser/applyTip";
            }
            return page;
        }

        return "redirect:/index";
    }

    @RequestMapping("/perUser/applyTip")
    public String applyTip() {
        log.info("个人用户进入'请用户填写身份认证信息的提示'页面");
        return "applyTip";
    }

    /**
     * 平台及单位对资源管理相关页面
     */

    @RequestMapping("/platform/resourcesManage")
    public String resourcesManage() {
        log.info("平台进入资源管理页面");
        return "resources/learnResources";
    }

    @RequestMapping("/puUser/addLearnResource")
    public String addResources() {
        log.info("进入资源增加管理页面");
        return "resources/addResource";
    }

    @GetMapping("/puUser/editResource/{resId}")
    public String editResources() {
        log.info("进入资源修改管理页面");
        return "resources/editResource";
    }

    @RequestMapping("/unitUser/resourcesManage")
    public String resourcesUnitManage() {
        log.info("单位用户进入资源管理页面");
        return "resources/unitUser/listResources";
    }

    @GetMapping("/puUser/questionRes/{resId}")
    public String questionRes() {
        log.info("进入资源详情页面");
        return "resources/question/questionRes";
    }

    @GetMapping("/puUser/{resId}/addQuestion")
    public String addQuestion() {
        log.info("打开给资源添加问题页面");
        return "resources/question/addQuestion";
    }

    @GetMapping("/puUser/{id}/editQuestion")
    public String editQuestion() {
        log.info("打开给资源添加问题页面");
        return "resources/question/editQuestion";
    }

    /**
     * 平台及单位对培训管理相关页面
     */
    @RequestMapping("/puUser/trainManage")
    public String trainManage() {
        log.info("平台进入培训管理页面");
        return "train/trainManage";
    }

    @RequestMapping("/puUser/trainConfig/{trainingId}")
    public String trainConfig() {
        log.info("平台进入培训配置管理页面");
        return "train/trainConfig";
    }

    @RequestMapping("/puUser/trainResult/{trainingId}")
    public String trainResult() {
        log.info("平台进入培训结果管理页面");
        return "train/trainResult";
    }

    @RequestMapping("/puUser/addTrain")
    public String addTrain() {
        log.info("平台或单位用户进入增加培训页面");
        return "train/addTrain";
    }

    @RequestMapping("/puUser/editTrain/{trainingId}")
    public String editTrain() {
        log.info("平台或单位用户进入修改培训页面");
        return "train/editTrain";
    }

    @RequestMapping("/puUser/addTrainUser/{trainingId}")
    public String addTrainUser() {
        log.info("平台或单位用户进入添加培训用户页面");
        return "train/addTrainUser";
    }

    @RequestMapping("/puUser/addTrainResource/{trainingId}")
    public String addTrainResource() {
        log.info("平台或单位用户进入添加培训资源页面");
        return "train/addTrainResource";
    }

    /**
     * 用户的学习资源相关页面
     */
    @GetMapping("/perUser/resIndex")
    public String resIndex() {
        log.info("用户浏览学习资源页面");
        return "resIndex";
    }

    @GetMapping("/perUser/resourceDetail/{resId}")
    public String getResourceDetail() {
        log.info("个人用户进入学习资源介绍的页面");
        return "resources/resourceDetail";
    }

    @GetMapping("/perUser/{userId}/listUserResource")
    public String listUserResource() {
        log.info("个人用户进入列出学习资源的页面");
        return "user/perUser/listUserResource";
    }

    @GetMapping("/video/{resId}")
    public String playUserResource() {
        log.info("个人用户进入资源播放页");
        return "user/perUser/resPlayer";
    }

    @GetMapping("/question/{id}")
    public String layerQuestion() {
        log.info("个人用户进入问题弹出页");
        return "user/perUser/layerQuestion";
    }

    /**
     * 用户的个人信息认证查看等相关页面
     */
    @RequestMapping("/perUser/fillPerInfo")
    public String fillPerInfo() {
        log.info("个人用户进入完善个人信息页面");
        return "user/perUser/perInfo";
    }

    @RequestMapping("/perUser/authenticate")
    public String authenticate() {
        log.info("个人用户进入实名认证页面");
        return "user/perUser/authenticate";
    }

    @RequestMapping("/perUser/accountBind")
    public String accountBind() {
        log.info("个人用户进入账号绑定页面");
        return "user/perUser/accountBind";
    }

    /**
     * 用户的培训相关页面
     */
    @RequestMapping("/perUser/{userId}/listUserTrain")
    public String listUserTrain() {
        log.info("个人用户进入列出培训页面");
        return "train/perUser/listUserTrain";
    }

    @RequestMapping("/perUser/trainDetail/{trainId}")
    public String trainDetail() {
        log.info("个人用户进入培训详情页面");
        return "train/trainDetail";
    }

    /**
     * 个人用户考试相关页面
     */
    @RequestMapping("/perUser/exam/login")
    public String userExamLogin() {
        return "redirect:/exam/driver/login";
    }
}
