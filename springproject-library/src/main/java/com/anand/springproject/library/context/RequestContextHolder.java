package com.anand.springproject.library.context;

import com.anand.springproject.library.logging.LogHeader;
import org.slf4j.MDC;

/**
 * Holds RequestContext object in thread's local storage.
 * Facilitate easy access of the current thread's request context.
 *
 */
public class RequestContextHolder {

    private static final InheritableThreadLocal<RequestContext> itl = new InheritableThreadLocal<>();

    /**
     *
     * @return
     */
    public static RequestContext createEmptyContext() {
        clearContext();
        itl.set(new RequestContext());
        return itl.get();
    }

    /**
     *
     * @return
     */
    public static RequestContext getContext() {
        return itl.get();
    }

    /**
     *
     */
    public static void clearContext() {
        itl.remove();

        // Clear from MDC for logging
        LogHeader.headers.values().forEach(MDC::remove);
    }

    /**
     *
     * @return
     */
    public static boolean hasContext() {
        return itl.get()!=null;
    }

}
