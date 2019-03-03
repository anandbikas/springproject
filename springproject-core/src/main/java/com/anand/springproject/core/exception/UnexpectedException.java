package com.anand.springproject.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedException extends CoreRuntimeException {
    private static final long serialVersionUID = -1234567890000000001L;

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(int errorCode, String message) {
        super(errorCode, message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

    public UnexpectedException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}