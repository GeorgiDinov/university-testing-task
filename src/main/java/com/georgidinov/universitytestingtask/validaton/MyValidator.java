package com.georgidinov.universitytestingtask.validaton;

import com.georgidinov.universitytestingtask.exception.CustomValidationException;

@FunctionalInterface
public interface MyValidator<T> {

    void validate(T thing) throws CustomValidationException;

}