package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.Getter;
import lombok.Setter;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.baseEntityValidator;
import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.markValueValidator;


@Getter
@Setter
public class Mark implements BaseEntity {

    //== fields ==
    private Long id;
    private int markValue;
    private Subject subject;
    private Student student;

    //== constructors ==
    public Mark() {
    }

    public Mark(Long id, int markValue, Subject subject, Student student) throws CustomValidationException {
        this.setId(id);
        this.setMarkValue(markValue);
        this.setSubject(subject);
        this.setStudent(student);
    }


    //== public methods ==
    public Mark id(Long id) throws CustomValidationException {
        this.setId(id);
        return this;
    }

    public Mark mark(int markValue) throws CustomValidationException {
        this.setMarkValue(markValue);
        return this;
    }

    public Mark student(Student student) throws CustomValidationException {
        this.setStudent(student);
        return this;
    }

    public Mark subject(Subject subject) throws CustomValidationException {
        this.setSubject(subject);
        return this;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        }
    }


    //== private methods ==
    private void setMarkValue(int markValue) throws CustomValidationException {
        markValueValidator.validate(markValue);
        this.markValue = markValue;
    }

    private void setStudent(Student student) throws CustomValidationException {
        baseEntityValidator.validate(student);
        this.student = student;
        this.student.addMark(this);
    }

    private void setSubject(Subject subject) throws CustomValidationException {
        baseEntityValidator.validate(subject);
        this.subject = subject;
        this.subject.addMark(this);
    }

}