package com.anand.springproject.service.security.authentication;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * This filter checks whether SSL mutual authentication happened or not.
 * If authentication happened, a valid cert should be preset in the request.
 */
public class SSLAuthenticationFilter extends GenericFilterBean {

    private static final XLogger logger = XLoggerFactory.getXLogger(SSLAuthenticationFilter.class);

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        final X509Certificate[] certs =
                (X509Certificate[]) httpServletRequest.getAttribute("javax.servlet.request.X509Certificate");

        if (Objects.isNull(certs) || certs.length == 0) {

            logger.warn("Missing client certificate in servlet request.");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
