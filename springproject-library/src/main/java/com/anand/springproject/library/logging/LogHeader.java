package com.anand.springproject.library.logging;

import java.util.HashMap;
import java.util.Map;
import com.anand.springproject.library.context.RequestHeader;

/**
 *
 */
public class LogHeader {

    public static final String SESSION_ID = "sessionId";

    public static Map<String, String> headers = new HashMap<>();

    static {
        headers.put(RequestHeader.SESSION_ID, SESSION_ID);
    }
}
