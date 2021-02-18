package com.georgidinov.universitytestingtask.junit.util;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.validaton.MyValidator;

public final class GlobalValidator {

    //Base Person Name Validator
    public static final MyValidator<String> basePersonNameValidator = name -> {
        if (name == null) {
            throw new CustomValidationException("Name IS Null");
        }
        if (name.isEmpty()) {
            throw new CustomValidationException("Name Is Empty");
        }
        if (name.isBlank()) {
            throw new CustomValidationException("Name Is Blank");
        }
    };


    //Mark Validations
    public static final MyValidator<Integer> markValueValidator = mark -> {
        if (mark < 2) {
            throw new CustomValidationException("Mark is less than 2");
        }
        if (mark > 6) {
            throw new CustomValidationException("Mark is greater than 6");
        }
    };


    private GlobalValidator() {
    }

}