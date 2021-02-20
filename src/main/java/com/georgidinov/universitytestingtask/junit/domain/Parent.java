package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.baseEntityValidator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parent extends BasePerson implements BaseEntity {

    private Long id;

    private Student student;

    @Builder
    public Parent(Long id, String firstName, String lastName, Student student) throws CustomValidationException {
        super(firstName, lastName);
        this.id = id;
        baseEntityValidator.validate(student);
        this.student = student;
    }
}
