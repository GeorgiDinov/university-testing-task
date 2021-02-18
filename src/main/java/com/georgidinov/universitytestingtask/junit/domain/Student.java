package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class Student extends BasePerson {

    @Getter
    @Setter
    private Long id;

    @Getter
    private List<Parent> parents;

    @Getter
    @Setter
    private List<Mark> marks;

    @Builder
    public Student(String firstName, String lastName,
                   List<Parent> parents, List<Mark> marks) throws CustomValidationException {
        super(firstName, lastName);
        this.parents = parents;
        this.marks = marks;
    }


}