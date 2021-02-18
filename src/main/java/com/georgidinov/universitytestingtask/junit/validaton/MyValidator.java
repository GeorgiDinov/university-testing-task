package com.georgidinov.universitytestingtask.junit.validaton;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;

@FunctionalInterface
public interface MyValidator<T> {

    void validate(T thing) throws CustomValidationException;

}