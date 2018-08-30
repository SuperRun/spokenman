package com.zust.itee.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 * Created by dxb on 2016/12/7.
 */
@Controller
@RequestMapping("")
public class PageController {

    // @RequestMapping("")
    // public String pageIndex(SessionInfo sessionInfo) {
    //
    //     //        LoginTypeEnum loginType = sessionInfo.getLoginType();
    //     //        String redirectTo = null;
    //     //        if (loginType == null) {
    //     //            redirectTo = "/member/login";
    //     //        } else if (loginType.equals(LoginTypeEnum.DRIVER)) {
    //     //            redirectTo = "/driver";
    //     //        } else if (loginType.equals(LoginTypeEnum.MEMBER)) {
    //     //            redirectTo = "/member";
    //     //
    //     //        }
    //     //
    //     //        return "redirect:" + redirectTo;
    //     return "forward:/member";
    // }
}
