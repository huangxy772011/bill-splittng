package com.ascending.project.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.ascending.project",
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.ascending.project.api.*"))
public class AppConfig {

//    @Bean(name = "databaseProperties")
////    public PropertiesFactoryBean getDbProperties(){
////        PropertiesFactoryBean bean = new PropertiesFactoryBean();
//////        String profile =
////    }

}
