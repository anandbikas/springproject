package com.anand.springproject.service.config;

import com.anand.springproject.library.context.RequestContext;
import com.anand.springproject.library.context.RequestContextHolder;
import com.anand.springproject.library.context.RequestHeader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Request interceptor to create request context.
 *
 * REF: https://dzone.com/articles/correlation-id-for-logging-in-microservices
 */
@Component
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {

        final RequestContext context = RequestContextHolder.createEmptyContext();

        Enumeration<String> headers = request.getHeaderNames();

        if(headers!=null){
            while (headers.hasMoreElements()){
                String header = headers.nextElement();

                if (StringUtils.startsWithIgnoreCase(header, RequestHeader.PREFIX)) {
                    context.addHeader(header, request.getHeader(header));
                }
            }
        }
        return true;
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) throws Exception {
        RequestContextHolder.clearContext();
    }
}
