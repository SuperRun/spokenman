package com.zust.itee;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import com.zust.itee.enums.DateConvertRule;

@SpringBootApplication
@EnableAutoConfiguration
public class OtaWebApplication extends SpringBootServletInitializer {

    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String s) {
                return DateConvertRule.parseDateFromStr(s);
            }
        };
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(OtaWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OtaWebApplication.class, args);
    }
}
