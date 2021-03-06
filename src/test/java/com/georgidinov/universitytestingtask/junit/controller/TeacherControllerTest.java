package com.georgidinov.universitytestingtask.junit.controller;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.controlleradvise.ControllerExceptionHandler;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TeacherControllerTest extends AbstractRestControllerTest {

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

    @Test
    void saveTeacher() throws Exception {
        //given
        TeacherDTO requestDTO = TeacherDTO.builder().firstName("John").lastName("Doe").build();
        TeacherDTO expectedDTO = new TeacherDTO(requestDTO.getFirstName(), requestDTO.getLastName(), TEACHER_BASE_URL + "/" + 1);
        when(this.teacherService.saveTeacher(any(TeacherDTO.class))).thenReturn(expectedDTO);
        //when then
        mockMvc.perform(post(TEACHER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name", equalTo(requestDTO.getFirstName())))
                .andExpect(jsonPath("$.last_name", equalTo(requestDTO.getLastName())))
                .andExpect(jsonPath("$.teacher_url", equalTo(expectedDTO.getTeacherUrl())));
    }

    @ParameterizedTest(name = ARGUMENTS_PLACEHOLDER)
    @MethodSource("com.georgidinov.universitytestingtask.junit.service.TeacherServiceImplTest#teacherDTOProvider")
    void saveTeacherBadRequestBodyParametrized(TeacherDTO teacherDTO, String exceptionMessage) throws Exception {
        when(this.teacherService.saveTeacher(any(TeacherDTO.class)))
                .thenThrow(new CustomValidationException(exceptionMessage));
        //when then
        mockMvc.perform(post(TEACHER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(teacherDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_message", equalTo(exceptionMessage)));
    }

    @Test
    void updateTeacher() throws Exception {
        //given
        long id = 1L;
        TeacherDTO requestDTO = TeacherDTO.builder().firstName("John Updated").lastName("Doe Updated").build();
        TeacherDTO expectedDTO = new TeacherDTO(requestDTO.getFirstName(), requestDTO.getLastName(), TEACHER_BASE_URL + "/" + id);
        when(this.teacherService.updateTeacher(anyLong(), any(TeacherDTO.class))).thenReturn(expectedDTO);

        //when then
        mockMvc.perform(put(TEACHER_BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.first_name", equalTo(expectedDTO.getFirstName())))
                .andExpect(jsonPath("$.last_name", equalTo(expectedDTO.getLastName())))
                .andExpect(jsonPath("$.teacher_url", equalTo(expectedDTO.getTeacherUrl())));
    }

    @Test
    void deleteTeacherById() throws Exception {
        mockMvc.perform(delete(TEACHER_BASE_URL + "/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(this.teacherService).deleteTeacherById(anyLong());
    }
}