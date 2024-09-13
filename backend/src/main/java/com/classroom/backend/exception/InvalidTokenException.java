package com.classroom.backend.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class InvalidTokenException extends RuntimeException {

    private static final String LOG_ERROR_EXCEPTION_OCCURRED_MSG =
            "An exception occurred, which will cause a '{}' response.";
    private static final String LOG_ERROR_EXCEPTION_OCCURRED_WITH_CAUSE_MSG =
            "An exception occurred, which will cause a '{}' response with cause '{}'.";

    public InvalidTokenException(String message) {
        super(message);
        log.warn(LOG_ERROR_EXCEPTION_OCCURRED_MSG, message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
        log.warn(LOG_ERROR_EXCEPTION_OCCURRED_WITH_CAUSE_MSG, message, cause);
    }
}
