package com.zust.itee.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.zust.itee.argumentresolver.SessionInfoArgumentResolver;
import com.zust.itee.interceptor.AuthInterceptor;
import com.zust.itee.interceptor.LoginInterceptor;

import freemarker.template.TemplateException;

/**
 * web-mvc 配置
 *
 * @author pojun
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        freeMarkerViewResolver.setOrder(0);
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPath("classpath:templates");
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(factory.createConfiguration());
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ServletWebArgumentResolverAdapter(new SessionInfoArgumentResolver()));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                // 后端接口
                .addPathPatterns("/org/**", "/lecturer/**", "/training/**",
                        "/user/**").excludePathPatterns("/user/login")
                // 前端页面接口
                .addPathPatterns("/users/**", "/platform/**", "/unitUser/**", "/puUser/**",
                        "/perUser/**")
                .excludePathPatterns("/users/login", "/users/regist");

        // 权限拦截器
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/users/**", "/platform/**", "/unitUser/**", "/puUser/**",
                        "/perUser/**", "/index");
    }
}
