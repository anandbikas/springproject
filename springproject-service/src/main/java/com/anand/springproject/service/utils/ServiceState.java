/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.utils;

import com.anand.springproject.library.context.RequestContextHolder;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@EnableRetry
@EnableScheduling
public class ServiceState {

    private static final XLogger logger = XLoggerFactory.getXLogger(ServiceState.class);

    Integer currentState=0;

    final Random random = new Random();

    @Autowired
    @Qualifier("retryTemplate")
    private RetryTemplate retryTemplate;

    @Value("${springproject.retry.maxAttempts:3}")
    private int maxAttempts;


    /**
     *
     */
    @PostConstruct
    public void init(){
        retryTemplate.execute(context -> {
            try {
                logger.info("Trying to update currentState...");

                if(random.nextInt(2)==0) {
                    throw new RuntimeException();
                }
                synchronized (currentState) {
                    currentState = random.nextInt(2);
                }
                logger.info("currentState updated to " + currentState);

            } catch (final Exception ex) {
                logger.debug("Failed to update currentState." + ex);
                throw ex;
            }
            return true;
        });
    }

    /**
     *
     */
    @Scheduled(cron="${scheduler.refresh.cron:0 * * ? * *}")
    public void refreshStateScheduler(){

        try {
            RequestContextHolder.createEmptyContext();
            init();
        } catch (Exception ex){
            logger.error("Failed to update currentState." + ex);
        }
    }

    /**
     * inline Retry with Retryable
     */
    @Retryable(value = { RuntimeException.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void refreshState(){

        logger.info("Trying to update currentState...");

        if(random.nextInt(2)==0) {
            throw new RuntimeException();
        }

        synchronized (currentState) {
            currentState = random.nextInt(2);
        }
        logger.info("currentState updated to " + currentState);
    }

    /**
     *
     * @param state
     */
    public void setState(final int state){

        retryTemplate.execute(context -> {
            try {
                logger.info("Trying to set currentState...");

                if(random.nextInt(2)==0) {
                    throw new RuntimeException();
                }

                if(random.nextInt(3)==0) {
                    throw new IllegalArgumentException();
                }

                synchronized (currentState) {
                    currentState = state;
                }
                logger.info("currentState set to " + currentState);

            } catch (final Exception ex) {
                logger.debug("Failed to update currentState." + ex);
                throw ex;
            }
            return true;
        });
    }

    /**
     *
     * @return
     */
    public int getCurrentState() {
        return currentState;
    }
}
