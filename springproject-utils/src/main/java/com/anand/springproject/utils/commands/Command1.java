package com.anand.springproject.utils.commands;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component("command1")
public class Command1 implements Command{

    private static final XLogger logger = XLoggerFactory.getXLogger(Command1.class);

    /**
     *
     */
    @Override
    public void run(){
        logger.info("Running command1...");
    }

}
