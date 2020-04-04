package com.anand.springproject.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends CoreRuntimeException {
    private static final long serialVersionUID = -1234567890000000003L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(int errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}