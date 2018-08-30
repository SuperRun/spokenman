package com.zust.itee.otauser.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-boot 启动类
 *
 * @author pojun
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zust.itee.otauser.impl.mapper")
public class OtaUserImplApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OtaUserImplApplication.class);
        app.run(args);
    }
}
