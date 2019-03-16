package org.summer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.summer.easydb.DataAble;
import org.summer.easydb.impl.DataTable;
import org.summer.util.BeanContainer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelApplicationTests {

    @Test
    public void contextLoads() {
        DataAble wb= BeanContainer.getBean("account", new DataTable().getClass());

    }

}
