import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zust.itee.otacore.generator.MapperGenerator;
import com.zust.itee.otacore.generator.MapperInterfaceGenerator;
import com.zust.itee.otacore.generator.ServiceGenerator;
import com.zust.itee.otacore.generator.ServiceImplGenerator;
import com.zust.itee.otaorg.client.domain.Organization;

/**
 * @author pojun
 */
@SpringBootTest
public class GenBaseCode {

    private static final Class clazz = Organization.class;

    private static final String name = "组织";

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

