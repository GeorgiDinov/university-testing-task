package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.baseEntityValidator;
import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.markValueValidator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mark implements BaseEntity {

    private Long id;

    private int markValue;
    private Subject subject;
    private Student student;


    public Mark(int markValue, Subject subject, Student student) throws CustomValidationException {
        markValueValidator.validate(markValue);
        this.markValue = markValue;
        baseEntityValidator.validate(subject);
        this.subject = subject;
        baseEntityValidator.validate(student);
        this.student = student;
    }

    public void setStudent(Student student) throws CustomValidationException {
        this.student = student;
        this.student.addMark(this);
    }

}