package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SubjectTest {

    Long id = 1L;
    Subject subject;
    Mark mark;

    @BeforeEach
    void setUp() throws CustomValidationException {
        subject = new Subject(id, "Math", new ArrayList<>());
    }

    @Test
    void addMark() throws CustomValidationException {
        //given
        mark = new Mark().id(id);
        //when
        mark.subject(subject);
        //then
        assertAll(
                "Multiple assertions",
                () -> assertEquals(1, subject.getMarks().size()),
                () -> assertNotNull(mark.getSubject()),
                () -> assertNull(mark.getStudent()),
                () -> assertEquals(subject.getSubjectName(), mark.getSubject().getSubjectName())
        );
    }
}