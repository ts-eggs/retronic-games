package com.retronic.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource dataSource() throws NamingException {
        return createJndiDatasource("java:comp/env/retronicDs");
    }

    private DataSource createJndiDatasource(String jndiName) throws NamingException {
        JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
        factoryBean.setJndiName(jndiName);
        factoryBean.afterPropertiesSet();
        return (DataSource) factoryBean.getObject();
    }
}
