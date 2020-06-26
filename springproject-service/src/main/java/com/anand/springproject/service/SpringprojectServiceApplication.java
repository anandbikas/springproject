/**
 * @author Bikas Anand
 */
package com.anand.springproject.service;

import com.anand.springproject.library.context.RequestContextHolder;
import com.anand.springproject.library.sql.DataSourceAutoConfig;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(DataSourceAutoConfig.class)
@ImportResource("classpath:spring/springproject-service-context.xml")
public class SpringprojectServiceApplication {

    private static final XLogger logger = XLoggerFactory.getXLogger(SpringprojectServiceApplication.class);

    public static void main(String[] args) {
        RequestContextHolder.createEmptyContext();

        logger.info("Starting SpringprojectApplication...");
        SpringApplication.run(SpringprojectServiceApplication.class, args);
    }

    /**
     * Add shutdown logger.
     */
    @EventListener
    public void onShutdown(ContextClosedEvent event) {
        logger.info("SpringprojectServiceApplication shutdown completed");
    }

}