package com.georgidinov.universitytestingtask.junit.domain;


import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject implements BaseEntity {

    private Long id;

    private String subjectName;

    public Subject(String subjectName) throws CustomValidationException {
        basePersonNameValidator.validate(subjectName);
        this.subjectName = subjectName;
    }

}