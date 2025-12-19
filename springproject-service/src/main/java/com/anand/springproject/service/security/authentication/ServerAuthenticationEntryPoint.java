package com.anand.springproject.service.security.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class ServerAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException {

        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

    }
}
