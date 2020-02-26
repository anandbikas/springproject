package com.anand.springproject.library.sql;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.anand.springproject.library.sql")
@EntityScan("com.anand.springproject.core.domain")
public class Config {

}