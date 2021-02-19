package com.georgidinov.universitytestingtask.junit.baseperson;


import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.Getter;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;

public abstract class BasePerson implements BaseEntity {


    @Getter
    @Setter
    protected Long id;

    @Getter
    private String firstName;
    @Getter
    private String lastName;

    protected BasePerson() {
    }

    public BasePerson(Long id, String firstName, String lastName) throws CustomValidationException {
        this.id = id;
        basePersonNameValidator.validate(firstName);
        this.firstName = firstName;
        basePersonNameValidator.validate(lastName);
        this.lastName = lastName;
    }

}
