package com.georgidinov.universitytestingtask.junit.service;

import com.georgidinov.universitytestingtask.junit.api.v1.mapper.TeacherMapper;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.repository.map.TeacherMapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

import static com.georgidinov.universitytestingtask.junit.util.ApplicationConstants.TEACHER_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeacherServiceImplTest {

    @Mock
    private TeacherMapRepository teacherMapRepository;

    private TeacherService teacherService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.teacherService = new TeacherServiceImpl(this.teacherMapRepository, TeacherMapper.INSTANCE);
    }

    @Test
    void findAllTeachers() throws CustomValidationException {
        //given
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(Teacher.builder().id(1L).firstName("John").lastName("Doe").build());
        teachers.add(Teacher.builder().id(2L).firstName("Anna").lastName("Smith").build());
        when(this.teacherMapRepository.findAll()).thenReturn(teachers);
        //when
        TeacherListDTO responseDTO = this.teacherService.findAllTeachers();

        //then
        assertAll(
                "Multiple assertions on TeacherListDTO object",
                () -> assertNotNull(responseDTO),
                () -> assertEquals(teachers.size(), responseDTO.getTeachers().size())
        );
        verify(this.teacherMapRepository).findAll();
    }

    @Test
    void findTeacherById() throws CustomValidationException {
        //given
        Long id = 1L;
        String dtoUrl = TEACHER_BASE_URL + "/" + id;
        Teacher teacher = Teacher.builder().id(id).firstName("John").lastName("Doe").build();
        when(this.teacherMapRepository.findById(anyLong())).thenReturn(teacher);
        //when
        TeacherDTO teacherDTO = this.teacherService.findTeacherById(id);

        //then
        assertAll(
                "Multiple assertions on TeacherDTO object",
                () -> assertNotNull(teacherDTO),
                () -> assertEquals(teacher.getFirstName(), teacherDTO.getFirstName()),
                () -> assertEquals(teacher.getLastName(), teacherDTO.getLastName()),
                () -> assertEquals(dtoUrl, teacherDTO.getTeacherUrl())
        );
        verify(this.teacherMapRepository).findById(anyLong());
    }

    @Test
    void findTeacherByIdNotFound() {
        //given
        when(this.teacherMapRepository.findById(anyLong())).thenReturn(null);
        //when
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> this.teacherService.findTeacherById(1L));
        //then
        String expectedMessage = "Record with ID = 1 Not Found";
        assertEquals(expectedMessage, exception.getMessage());
        verify(this.teacherMapRepository).findById(anyLong());
    }

    @Test
    void saveTeacher() throws CustomValidationException {
        //given
        long id = 1L;
        String responseDTOUrl = TEACHER_BASE_URL + "/" + id;
        TeacherDTO requestDTO = TeacherDTO.builder().firstName("John").lastName("Doe").build();
        Teacher savedTeacherInDB = new Teacher(id, requestDTO.getFirstName(), requestDTO.getLastName());
        when(this.teacherMapRepository.save(any(Teacher.class))).thenReturn(savedTeacherInDB);

        //when
        TeacherDTO responseDTO = this.teacherService.saveTeacher(requestDTO);

        //then
        assertAll(
                "Multiple assertions on TeacherDTO object",
                () -> assertNotNull(responseDTO),
                () -> assertEquals(requestDTO.getFirstName(), responseDTO.getFirstName()),
                () -> assertEquals(requestDTO.getLastName(), responseDTO.getLastName()),
                () -> assertEquals(responseDTOUrl, responseDTO.getTeacherUrl())
        );
        verify(this.teacherMapRepository).save(any(Teacher.class));
    }


    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("teacherDTOProvider")
    void saveTeacherInvalidTeacherDTO(TeacherDTO teacherDTO, String expectedMessage) {
        //when
        Exception exception = assertThrows(CustomValidationException.class,
                () -> this.teacherService.saveTeacher(teacherDTO)
        );
        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> teacherDTOProvider() {
        return Stream.of(
                Arguments.of(TeacherDTO.builder().lastName("Doe").build(), "Name Is Null"),
                Arguments.of(TeacherDTO.builder().firstName("").lastName("Doe").build(), "Name Is Empty"),
                Arguments.of(TeacherDTO.builder().firstName(" ").lastName("Doe").build(), "Name Is Blank"),
                Arguments.of(TeacherDTO.builder().firstName("John").build(), "Name Is Null"),
                Arguments.of(TeacherDTO.builder().firstName("John").lastName("").build(), "Name Is Empty"),
                Arguments.of(TeacherDTO.builder().firstName("John").lastName(" ").build(), "Name Is Blank")
        );
    }


    @Test
    void updateTeacher() {
    }

    @Test
    void deleteTeacherById() {
    }
}