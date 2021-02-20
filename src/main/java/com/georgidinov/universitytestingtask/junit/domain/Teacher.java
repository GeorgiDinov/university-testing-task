package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Teacher extends BasePerson implements BaseEntity {

    Long id;

    @Builder
    public Teacher(Long id, String firstName, String lastName) throws CustomValidationException {
        super(firstName, lastName);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id= " + this.id +
                ", first_name=" + this.getFirstName() +
                ", last_name=" + this.getLastName() +
                '}';
    }
}