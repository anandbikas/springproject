package com.anand.springproject.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends CoreRuntimeException {
    private static final long serialVersionUID = -1234567890000000002L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int errorCode, String message) {
        super(errorCode, message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}