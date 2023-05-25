package com.example.push.api.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "mathcellDataSource")
    @ConfigurationProperties("spring.datasource.hikari.mathcell")
    public DataSource mathCellDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //sqlSessionFactory 생성
    @Bean(name = "mathcellSessionFactory")
    public SqlSessionFactory mathcellSessionFactory(@Qualifier("mathcellDataSource") DataSource mathCellDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(mathCellDataSource);
        factoryBean.setTypeAliasesPackage("com.example.push.dto");
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/db/sqlmap/sql-mapper-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/db/sqlmap/mappers/*.xml"));
        return factoryBean.getObject();
    }

    //sqlSession 생성
    @Bean(name = "mathcellSessionTemplate")
    public SqlSessionTemplate mathcellSessionTemplate(SqlSessionFactory mathcellSessionFactory) throws Exception {
        return new SqlSessionTemplate(mathcellSessionFactory);
    }
}
