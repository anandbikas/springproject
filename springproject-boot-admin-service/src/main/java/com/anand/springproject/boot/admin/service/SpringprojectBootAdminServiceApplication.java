package com.anand.springproject.boot.admin.service;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAdminServer
public class SpringprojectBootAdminServiceApplication {

    private static final XLogger logger = XLoggerFactory.getXLogger(SpringprojectBootAdminServiceApplication.class);

    public static void main(String[] args) {

        logger.info("Starting SpringprojectBootAdminServiceApplication...");
        SpringApplication.run(SpringprojectBootAdminServiceApplication.class, args);
    }

    /**
     * Add shutdown logger.
     */
    @EventListener
    public void onShutdown(ContextClosedEvent event) {
        logger.info("SpringprojectBootAdminServiceApplication shutdown completed");
    }

}