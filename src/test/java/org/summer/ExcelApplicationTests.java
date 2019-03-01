package org.summer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.summer.easydb.impl.Sheet;
import org.summer.util.BeanContainer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelApplicationTests {

    @Test
    public void contextLoads() {
        Sheet wb= BeanContainer.getBean("account", new Sheet().getClass());

    }

}
