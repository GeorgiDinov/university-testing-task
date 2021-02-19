package com.georgidinov.universitytestingtask.junit.controlleradvise;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleItemInvalidFormatException(InvalidFormatException exception) {
        return new ErrorMessage(logError(exception));
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNumberFormatException(NumberFormatException exception) {
        return new ErrorMessage(logError(exception));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException exception) {
        return new ErrorMessage(logError(exception));
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCustomValidationException(CustomValidationException exception){
        return new ErrorMessage(logError(exception));
    }

    //== private methods ==
    private String logError(Exception exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Handling {} with message = {}", exception.getClass().getSimpleName(), exceptionMessage);
        return exceptionMessage;
    }

}