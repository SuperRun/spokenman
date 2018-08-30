package com.zust.itee.otaorg.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-boot 启动类
 *
 * @author pojun
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zust.itee.otaorg.impl.mapper")
public class OtaOrgImplApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OtaOrgImplApplication.class);
        app.run(args);
    }
}
