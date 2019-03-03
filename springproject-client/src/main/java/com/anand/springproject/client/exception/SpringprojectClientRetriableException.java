package com.anand.springproject.client.exception;

import com.anand.springproject.core.exception.CoreRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SpringprojectClientRetriableException extends CoreRuntimeException {

    private static final long serialVersionUID = -1234567890000000001L;

    public SpringprojectClientRetriableException(String message) {
        super(message);
    }

    public SpringprojectClientRetriableException(Throwable cause) {
        super(cause);
    }

    public SpringprojectClientRetriableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringprojectClientRetriableException(int errorCode, String message) {
        super(errorCode, message);
    }

    public SpringprojectClientRetriableException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
