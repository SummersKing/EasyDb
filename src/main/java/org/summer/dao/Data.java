package org.summer.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.summer.easydb.DataSourceConfig;
import org.summer.easydb.DataTable;
import org.summer.entity.Account;
import org.summer.entity.Company;
import org.summer.easydb.DataSourceScanner;


@Repository
public class Data  implements DataSourceConfig {

    @Override
    public String DataSource() {
        return "C:/dev/demo.xls";
    }
    @Bean(name="company")
    public DataTable company(){
        return DataSourceScanner.connectSheet(new Company(),this);
    }
    @Bean(name="account")
    public DataTable account(){
        return  DataSourceScanner.connectSheet(new Account(),this);
    }


}
