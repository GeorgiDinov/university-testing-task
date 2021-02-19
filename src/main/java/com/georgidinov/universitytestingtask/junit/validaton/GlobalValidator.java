package com.georgidinov.universitytestingtask.junit.validaton;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;

public final class GlobalValidator {

    //BaseEntity validation
    public static final MyValidator<BaseEntity> baseEntityValidator = baseEntity -> {
        if (baseEntity == null) {
            throw new CustomValidationException("Base Entity Is Null");
        }
        if (baseEntity.getId() == null) {
            throw new CustomValidationException("Base Entity ID Is Null");
        }
    };


    //Base Person Name Validator
    public static final MyValidator<String> basePersonNameValidator = name -> {
        if (name == null) {
            throw new CustomValidationException("Name Is Null");
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