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
public class Teacher extends BasePerson {


    @Builder
    public Teacher(String firstName, String lastName) throws CustomValidationException {
        super(firstName, lastName);
    }

}