package com.anand.springproject.utils.commands;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("command2")
public class Command2 implements Command{

    private static final XLogger logger = XLoggerFactory.getXLogger(Command2.class);

    /**
     *
     */
    @Override
    public void run(){
        logger.info("Running command2...");
    }

}
