package com.georgidinov.universitytestingtask.domain;

import com.georgidinov.universitytestingtask.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.validaton.MyValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Builder
public class Mark {

    private int markValue;
    private LocalDate markDate;
    private Subject subject;
    private Student student;

    private MyValidator<Integer> markValidator = mark -> {
        if (mark < 2) {
            throw new CustomValidationException("Mark is less than 2");
        }
        if (mark > 6) {
            throw new CustomValidationException("Mark is greater than 6");
        }
    };

    public Mark(int markValue, LocalDate markDate,
                Subject subject, Student student) throws CustomValidationException {
        this.markValue = markValue;
        this.markDate = markDate;
        this.subject = subject;
        this.student = student;
    }



}