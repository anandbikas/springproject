package com.anand.springproject.library.sql;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty( name = "spring.datasource.configure", havingValue = "True")
@Import(DataSourceAutoConfiguration.class)
public class DataSourceAutoConfig {
}
