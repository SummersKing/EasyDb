package org.summer.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.summer.easydb.DataAble;
import org.summer.easydb.DataSourceConfig;
import org.summer.entity.Account;
import org.summer.entity.Company;
import org.summer.easydb.DataSourceFactory;


@Repository
public class Data  implements DataSourceConfig {
    @Override
    public String dataSource() {
        return "C:/dev/demo.xlsx";
    }

    @Override
    public String password() {
        return "asd123";
    }

    @Bean(name="company")
    public DataAble company(){
        return DataSourceFactory.connectSheet(new Company(),this);
    }
    @Bean(name="account")
    public DataAble account(){
        return  DataSourceFactory.connectSheet(new Account(),this);
    }


}
