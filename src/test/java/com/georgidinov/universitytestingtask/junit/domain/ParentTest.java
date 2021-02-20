package com.georgidinov.universitytestingtask.junit.domain;

import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

class ParentTest {

    static Long id = 1L;

    Parent parent;


    static Student student;

    @BeforeAll
    static void setUpBeforeAll() throws CustomValidationException {
        student = new Student(id, "John", "Doe", new ArrayList<>(), new ArrayList<>());
    }

    @BeforeEach
    void setUp() {
    }


    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("parentFieldProvider")
    void testConstructorInitializationParameterized(Long id, String fName, String lName,
                                                    Student student, String expectedMessage) {
        Exception exception = assertThrows(CustomValidationException.class,
                () -> parent = new Parent(id, fName, lName, student)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> parentFieldProvider() {
        return Stream.of(
                Arguments.of(id, null, "Doe", student, "Name Is Null"),
                Arguments.of(id, "", "Doe", student, "Name Is Empty"),
                Arguments.of(id, " ", "Doe", student, "Name Is Blank"),
                Arguments.of(id, "John", null, student, "Name Is Null"),
                Arguments.of(id, "John", "", student, "Name Is Empty"),
                Arguments.of(id, "John", " ", student, "Name Is Blank"),
                Arguments.of(id, "John", "Doe", null, "Base Entity Is Null")
        );
    }
}