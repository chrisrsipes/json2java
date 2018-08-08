package com.crs.exception;

/**
 * Created by crs on 8/8/18.
 */
public class InvalidProcessorException extends RuntimeException {
    public InvalidProcessorException() {
    }

    public InvalidProcessorException(String message) {
        super(message);
    }

    public InvalidProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProcessorException(Throwable cause) {
        super(cause);
    }

    public InvalidProcessorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
