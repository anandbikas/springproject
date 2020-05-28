package com.anand.springproject.utils;

import com.anand.springproject.core.exception.UnexpectedException;
import com.anand.springproject.utils.commands.Command;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner, ApplicationContextAware {

    private static final XLogger logger = XLoggerFactory.getXLogger(AppStartupRunner.class);

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Application started with commands: {} and options: {}", args.getNonOptionArgs(), args.getOptionNames());

        args.getNonOptionArgs().forEach(command -> {
            try {
                logger.info("Running command: {} ...", command);
                applicationContext.getBean(command, Command.class).run();
            } catch (Exception ex){
                throw new UnexpectedException(String.format("Failed to run command %s", command), ex);
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}