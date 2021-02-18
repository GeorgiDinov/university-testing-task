package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BasePerson {

    private Long id;


    @Builder
    public Teacher(Long id, String firstName, String lastName) throws CustomValidationException {
        super(id, firstName, lastName);
    }

}