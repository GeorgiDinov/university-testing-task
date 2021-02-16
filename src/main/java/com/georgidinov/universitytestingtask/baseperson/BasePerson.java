package com.georgidinov.universitytestingtask.baseperson;


import com.georgidinov.universitytestingtask.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.validaton.MyValidator;
import lombok.Getter;

public abstract class BasePerson implements BaseEntity {

    private static long instanceCounter = 1;

    @Getter
    private Long id;
    @Getter
    private String firstName;
    @Getter
    private String lastName;

    private final MyValidator<String> nameValidator = name -> {
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

    protected BasePerson() {
    }

    public BasePerson(String firstName, String lastName) throws CustomValidationException {
        this.id = instanceCounter++;
        this.nameValidator.validate(firstName);
        this.firstName = firstName;
        this.nameValidator.validate(lastName);
        this.lastName = lastName;
    }

}
