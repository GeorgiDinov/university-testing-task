package com.georgidinov.universitytestingtask.junit.service;

import com.georgidinov.universitytestingtask.junit.api.v1.mapper.TeacherMapper;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.repository.map.TeacherMapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void findTeacherById() {
    }

    @Test
    void saveTeacher() {
    }

    @Test
    void updateTeacher() {
    }

    @Test
    void deleteTeacherById() {
    }
}