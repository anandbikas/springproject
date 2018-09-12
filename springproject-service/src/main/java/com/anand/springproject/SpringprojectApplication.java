/**
 * @author Bikas Anand
 */
package com.anand.springproject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/springproject-service-context.xml")
public class SpringprojectApplication {

    private static final XLogger logger = XLoggerFactory.getXLogger(SpringprojectApplication.class);

    public static void main(String[] args) {
        logger.info("Starting SpringprojectApplication...");
        SpringApplication.run(SpringprojectApplication.class, args);
    }
}