package org.spring.springboot.config.ds;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {"org.spring.springboot.dao.ds3.mapper"},sqlSessionFactoryRef = "ds3SqlSessionFactory")
public class DataSource3Config {

    //@Primary
    @Bean(name = "ds3DataSource")
    @ConfigurationProperties(prefix = "ds3.datasource")
    public DataSource dataSource() {
        DataSource ds = DataSourceBuilder.create().build();

        return ds;
    }

    //@Primary
    @Bean(name = "ds3TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("ds3DataSource")  DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Primary
    @Bean(name = "ds3SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds3DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("org.spring.springboot.dao.ds3.mapper");
        return factoryBean.getObject();
    }
}

