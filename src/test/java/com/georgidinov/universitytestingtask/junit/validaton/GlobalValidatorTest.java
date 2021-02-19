package com.georgidinov.universitytestingtask.junit.validaton;

import com.georgidinov.universitytestingtask.junit.baseperson.BasePerson;
import com.georgidinov.universitytestingtask.junit.domain.Mark;
import com.georgidinov.universitytestingtask.junit.domain.Student;
import com.georgidinov.universitytestingtask.junit.domain.Subject;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;
import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.markValueValidator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

class GlobalValidatorTest {


    @BeforeEach
    void setUp() {
    }


    @Test
    void testBasePersonNameOk() throws CustomValidationException {
        BasePerson basePerson = new Teacher(1L, "John", "Doe");
    }

    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("nameProvider")
    void testBasePersonNameValidatorParametrized(String name, String expectedMessage) throws CustomValidationException {
        Exception exception = assertThrows(CustomValidationException.class,
                () -> basePersonNameValidator.validate(name)
        );
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    private static Stream<Arguments> nameProvider() {
        return Stream.of(
                Arguments.of(null, "Name Is Null"),
                Arguments.of("", "Name Is Empty"),
                Arguments.of(" ", "Name Is Blank")
        );
    }


    @Test
    void testCreatingMarkOk() throws CustomValidationException {
        Subject subject = new Subject(1L, "History");
        Student student = new Student(1L, "John", "Doe", new ArrayList<>(), new ArrayList<>());
        Mark mark = new Mark(2, subject, student);
    }

    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("markValueProvider")
    void testMarkValueValidatorParametrized(int markValue, String expectedMessage) {
        Exception exception = assertThrows(CustomValidationException.class,
                () -> markValueValidator.validate(markValue)
        );
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    private static Stream<Arguments> markValueProvider() {
        return Stream.of(
                Arguments.of(1, "Mark is less than 2"),
                Arguments.of(7, "Mark is greater than 6")
        );
    }

}