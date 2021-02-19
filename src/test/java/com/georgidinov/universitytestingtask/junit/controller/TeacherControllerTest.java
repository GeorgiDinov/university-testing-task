package com.georgidinov.universitytestingtask.junit.controller;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.controlleradvise.ControllerExceptionHandler;
import com.georgidinov.universitytestingtask.junit.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.georgidinov.universitytestingtask.junit.util.ApplicationConstants.TEACHER_BASE_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(teacherController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void findAllTeachers() throws Exception {
        //given
        List<TeacherDTO> teachers = new ArrayList<>();
        teachers.add(new TeacherDTO("John", "Doe", TEACHER_BASE_URL + "/" + 1));
        teachers.add(new TeacherDTO("Anna", "Smith", TEACHER_BASE_URL + "/" + 2));
        TeacherListDTO teacherListDTO = new TeacherListDTO(teachers);
        when(this.teacherService.findAllTeachers()).thenReturn(teacherListDTO);
        //when then
        mockMvc.perform(get(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teachers", hasSize(2)));
    }

    @Test
    void findTeacherById() throws Exception {
        //given
        TeacherDTO teacherDTO = new TeacherDTO("John", "Doe", TEACHER_BASE_URL + "/" + 1);
        when(this.teacherService.findTeacherById(anyLong())).thenReturn(teacherDTO);
        //when then
        mockMvc.perform(get(TEACHER_BASE_URL + "/" + 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo("John")))
                .andExpect(jsonPath("$.last_name", equalTo("Doe")))
                .andExpect(jsonPath("$.teacher_url", equalTo(TEACHER_BASE_URL + "/" + 1)));
    }

    @Test
    void findTeacherByIdNotFound() throws Exception {
        //given
        String exceptionMessage = "Record with ID = 1 Not Found";
        when(this.teacherService.findTeacherById(anyLong()))
                .thenThrow(new NoSuchElementException(exceptionMessage));
        //when then
        mockMvc.perform(get(TEACHER_BASE_URL + "/" + 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_message", equalTo(exceptionMessage)));
    }
}