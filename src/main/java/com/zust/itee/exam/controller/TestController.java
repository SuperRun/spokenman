package com.zust.itee.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO 类描述
 *
 * @author pojun
 */
@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test-ftl";
    }
}
