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
@MapperScan(basePackages = {"org.spring.springboot.dao.ds1.mapper"},sqlSessionFactoryRef = "ds1SqlSessionFactory")
public class DataSource1Config {

    @Primary
    @Bean(name = "ds1DataSource")
    @ConfigurationProperties(prefix = "ds1.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ds1TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("ds1DataSource")  DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "ds1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("org.spring.springboot.dao.ds1.mapper");
        return factoryBean.getObject();
    }
}
