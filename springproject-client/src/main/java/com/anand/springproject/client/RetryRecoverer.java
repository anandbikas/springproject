package com.anand.springproject.client;

import com.anand.springproject.core.exception.UnexpectedException;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.retry.interceptor.MethodInvocationRecoverer;
import org.springframework.stereotype.Component;

@Component
public class RetryRecoverer<T> implements MethodInvocationRecoverer<T> {

    private static final XLogger logger = XLoggerFactory.getXLogger(RetryRecoverer.class);

    @Override
    public T recover(final Object[] args, final Throwable cause) {

        logger.debug("Retries exhausted...");
        throw new UnexpectedException("Retries exhausted", cause);
    }
}