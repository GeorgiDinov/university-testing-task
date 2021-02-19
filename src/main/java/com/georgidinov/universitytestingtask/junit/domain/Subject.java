package com.georgidinov.universitytestingtask.junit.domain;


import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.baseEntityValidator;
import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;

@Getter
@Setter
@NoArgsConstructor
public class Subject implements BaseEntity {

    private Long id;

    private String subjectName;
    private List<Mark> marks;

    public Subject(Long id, String subjectName, List<Mark> marks) throws CustomValidationException {
        this.id = id;
        basePersonNameValidator.validate(subjectName);
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public void addMark(Mark mark) throws CustomValidationException {
        baseEntityValidator.validate(mark);
        this.marks.add(mark);
    }


}