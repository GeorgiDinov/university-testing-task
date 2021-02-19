package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.baseEntityValidator;


@NoArgsConstructor
@AllArgsConstructor
public class Student extends BasePerson implements BaseEntity {

    @Getter
    @Setter
    private Long id;

    @Getter
    private List<Parent> parents;

    @Getter
    @Setter
    private List<Mark> marks;

    @Builder
    public Student(Long id, String firstName, String lastName,
                   List<Parent> parents, List<Mark> marks) throws CustomValidationException {
        super(firstName, lastName);
        this.id = id;
        this.parents = parents;
        this.marks = marks;
    }

    public void addMark(Mark mark) throws CustomValidationException {
        baseEntityValidator.validate(mark);
        this.marks.add(mark);
    }


}