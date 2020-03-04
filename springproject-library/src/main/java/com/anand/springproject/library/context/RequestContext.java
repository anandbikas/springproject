package com.anand.springproject.library.context;

import com.anand.springproject.library.logging.LogHeader;
import org.slf4j.MDC;

import java.util.*;

/**
 *  Holds all the request headers for easy accessing.
 */
public class RequestContext {

    private final Stack<Map<String, String>> headersStack = new Stack<>();


    /**
     *
     */
    public RequestContext() {
        headersStack.push(new HashMap<>());
        addHeader(RequestHeader.SESSION_ID, UUID.randomUUID().toString());
    }

    /**
     *
     * @param header
     * @param value
     */
    public void addHeader(final String header, final String value) {

        // Add to MDC for logging
        if (LogHeader.headers.containsKey(header)) {
            MDC.put(LogHeader.headers.get(header), value);
        }

        headersStack.peek().put(header, value);
    }

    /**
     *
     * @param header
     * @return
     */
    public String getHeader(final String header) {
        return headersStack.peek().get(header);
    }

    /**
     *
     * @return
     */
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headersStack.peek());
    }

    /**
     *
     * @return
     */
    public String getSessionId() {
        return getHeader(RequestHeader.SESSION_ID);
    }

    /**
     *
     */
    public void push() {
        headersStack.push(new HashMap<>(headersStack.peek()));
    }

    /**
     *
     * @param headers
     */
    public void push(final HashMap<String, String> headers) {
        headersStack.push(headers);
    }

    /**
     *
     */
    public void pop() {
        headersStack.pop();
    }

    /**
     *
     * @return
     */
    public Map<String, String> peek() {
        return headersStack.peek();
    }

}
