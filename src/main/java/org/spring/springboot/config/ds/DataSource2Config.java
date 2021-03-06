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
@MapperScan(basePackages = {"org.spring.springboot.dao.ds2.mapper"},sqlSessionFactoryRef = "ds2SqlSessionFactory")
public class DataSource2Config {

    //@Primary
    @Bean(name = "ds2DataSource")
    @ConfigurationProperties(prefix = "ds2.datasource")
    public DataSource dataSource() {
        DataSource ds = DataSourceBuilder.create().build();

        return ds;
    }

    //@Primary
    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("ds2DataSource")  DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Primary
    @Bean(name = "ds2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("org.spring.springboot.dao.ds2.mapper");
        return factoryBean.getObject();
    }
}

