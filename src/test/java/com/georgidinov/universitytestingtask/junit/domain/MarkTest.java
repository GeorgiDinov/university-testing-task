package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

class MarkTest {

    static Long id = 1L;

    Mark mark;


    static Subject subject;
    static Student student;

    @BeforeAll
    static void setUpBeforeAll() throws CustomValidationException {
        subject = new Subject(id, "Math", new ArrayList<>());
        student = new Student(id, "John", "Doe", new ArrayList<>(), new ArrayList<>());
    }


    @BeforeEach
    void setUp() {
        mark = new Mark();
    }

    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("markFieldsProvider")
    void testConstructorInitializationParameterized(Long id, int markVal, Subject subject,
                                                    Student student, String expectedMessage) {
        Exception exception = assertThrows(CustomValidationException.class,
                () -> mark = new Mark(id, markVal, subject, student)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> markFieldsProvider() {
        return Stream.of(
                Arguments.of(1L, 1, subject, student, "Mark is less than 2"),
                Arguments.of(1L, 7, subject, student, "Mark is greater than 6"),
                Arguments.of(1L, 5, null, student, "Base Entity Is Null"),
                Arguments.of(1L, 5, subject, null, "Base Entity Is Null")
        );
    }


    @Test
    void id() {
        //when
        mark.setId(id);
        //then
        assertNotNull(mark);
        assertEquals(id, mark.getId());
    }

    @Test
    void mark() throws CustomValidationException {
        //given
        int markValue = 5;
        //when
        this.mark.mark(markValue);
        //then
        assertNotNull(mark);
        assertEquals(markValue, mark.getMarkValue());
    }

    @Test
    void student() throws CustomValidationException {
        //given
        //when
        this.mark.id(id);
        this.mark.student(student);
        //then
        assertNotNull(mark);
        assertEquals("John", mark.getStudent().getFirstName());
        assertEquals(1, mark.getStudent().getMarks().size());
    }

    @Test
    void subject() throws CustomValidationException {
        //given
        //when
        mark.id(id);
        mark.subject(subject);
        //then
        assertNotNull(mark);
        assertEquals("Math", mark.getSubject().getSubjectName());
        assertEquals(1, mark.getSubject().getMarks().size());
    }
}