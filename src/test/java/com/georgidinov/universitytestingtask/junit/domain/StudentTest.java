package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentTest {

    Long id = 1L;
    Student student;
    Mark mark;

    @BeforeEach
    void setUp() throws CustomValidationException {
        student = new Student(id, "John", "Doe", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void addMark() throws CustomValidationException {
        //given
        mark = new Mark().id(id);
        //when
        mark.student(student);
        //then
        assertAll(
                "Multiple assertions",
                () -> assertEquals(1, student.getMarks().size()),
                () -> assertNotNull(mark.getStudent()),
                () -> assertNull(mark.getSubject()),
                () -> assertEquals(student.getFirstName(), mark.getStudent().getFirstName())
        );
    }
}