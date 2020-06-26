package com.anand.springproject.library.sql;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnProperty( name = "spring.datasource.configure", havingValue = "True")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.anand.springproject.library.sql",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@EntityScan("com.anand.springproject.core.domain")
public class Config {

}