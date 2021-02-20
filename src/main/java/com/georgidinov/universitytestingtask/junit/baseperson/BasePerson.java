package com.georgidinov.universitytestingtask.junit.baseperson;


import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.Getter;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;

public abstract class BasePerson {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    protected BasePerson() {
    }

    public BasePerson(String firstName, String lastName) throws CustomValidationException {
        basePersonNameValidator.validate(firstName);
        this.firstName = firstName;
        basePersonNameValidator.validate(lastName);
        this.lastName = lastName;
    }

}
