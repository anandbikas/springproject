package com.anand.springproject.service.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class HouseKeepingAdvice {
    private static final Logger logger = LoggerFactory.getLogger(HouseKeepingAdvice.class);

    @Around("execution(* com.anand.springproject.service.DocService.getDocuments(..))")
    public Object aroundGetDocuments(final ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            final String className = joinPoint.getSignature().getDeclaringTypeName();
            final String methodName = joinPoint.getSignature().getName();
            final Object[] arguments = joinPoint.getArgs();

            //Do processing
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.debug("Method {}.{}() execution time: {} ms", className, methodName, elapsedTime);
            return result;

        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }

    @Before("execution(* com.anand.springproject.service.DocService.getDocuments(..))")
    public void beforeGetDocuments(final JoinPoint joinPoint){

        try {
            final String className = joinPoint.getSignature().getDeclaringTypeName();
            final String methodName = joinPoint.getSignature().getName();
            final Object[] arguments = joinPoint.getArgs();

            logger.debug("Method starts: Method {}.{}()", className, methodName);
            //return result;

        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }

    @After("execution(* com.anand.springproject.service.DocService.getDocuments(..))")
    public void afterGetDocuments(final JoinPoint joinPoint){

        try {
            final String className = joinPoint.getSignature().getDeclaringTypeName();
            final String methodName = joinPoint.getSignature().getName();
            final Object[] arguments = joinPoint.getArgs();

            logger.debug("Method ends: Method {}.{}()", className, methodName);
            //return result;

        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }

}
