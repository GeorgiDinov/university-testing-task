package com.georgidinov.universitytestingtask.domain;

import com.georgidinov.universitytestingtask.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parent extends BasePerson {

    private Student student;

    @Builder
    public Parent(String firstName, String lastName, Student student) throws CustomValidationException {
        super(firstName, lastName);
        this.student = student;
    }
}
