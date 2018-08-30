package com.zust.itee.otadatadictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-boot 启动类
 *
 * @author pojun
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zust.itee.otadatadictionary.mapper")
public class OtaDataDictionaryApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OtaDataDictionaryApplication.class);
        app.run(args);
    }
}
