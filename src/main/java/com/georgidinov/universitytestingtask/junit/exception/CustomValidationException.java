package com.georgidinov.universitytestingtask.junit.exception;

public class CustomValidationException extends Exception {

    public CustomValidationException() {
        super();
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public CustomValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomValidationException(Throwable cause) {
        super(cause);
    }
}
