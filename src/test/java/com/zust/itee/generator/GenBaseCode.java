package com.zust.itee.generator;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zust.itee.entity.resource.LearningResource;

/**
 * @author pojun
 */
@SpringBootTest
public class GenBaseCode {

    private static final Class clazz = LearningResource.class;

    private static final String name = "学习资源";

    @Test
    public void mapperTest() {
        System.out.println(MapperGenerator.generateMapper(clazz));
    }

    @Test
    public void mapperInterfaceTest() {
        System.out.println(MapperInterfaceGenerator.generateMapperInterface(clazz));
    }

    @Test
    public void serviceTest() {
        System.out.println(ServiceGenerator.generateService(clazz));
    }

    @Test
    public void serviceImplTest() {
        System.out.println(ServiceImplGenerator.generateServiceImpl(clazz, name));
    }
}

