package com.anand.springproject.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class CoreRuntimeException extends RuntimeException{

    private static final long serialVersionUID = -1234567890000000000L;

    private Integer errorCode;

    public CoreRuntimeException(String message) {
        super(message);
    }

    public CoreRuntimeException(Throwable cause) {
        super(cause);
    }

    public CoreRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreRuntimeException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CoreRuntimeException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
