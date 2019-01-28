/**
 * @author Bikas Anand
 */
package com.anand.springproject.utils;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/springproject-utils-context.xml")
public class SpringprojectUtilsApplication {

    private static final XLogger logger = XLoggerFactory.getXLogger(SpringprojectUtilsApplication.class);

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        logger.info("Starting SpringprojectUtilsApplication...");
        SpringApplication.run(SpringprojectUtilsApplication.class, args);

        logger.info("Time taken: " + (System.currentTimeMillis() - start));
        System.exit(0);
    }
}