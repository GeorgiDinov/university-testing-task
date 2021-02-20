package com.georgidinov.universitytestingtask.junit.controller;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.georgidinov.universitytestingtask.junit.util.ApplicationConstants.TEACHER_BASE_URL;

@Slf4j
@RestController
@RequestMapping(TEACHER_BASE_URL)
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TeacherListDTO findAllTeachers() {
        log.info("TeacherController::findAllTeachers");
        return this.teacherService.findAllTeachers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO findTeacherById(@PathVariable String id) throws CustomValidationException {
        log.info("TeacherController::findTeacherById -> id passed = {}", id);
        return this.teacherService.findTeacherById(Long.valueOf(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) throws CustomValidationException {
        log.info("TeacherController::saveTeacher -> DTO passed = {}", teacherDTO);
        return this.teacherService.saveTeacher(teacherDTO);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO updateTeacher(@PathVariable String id,
                                    @RequestBody TeacherDTO teacherDTO) throws CustomValidationException {
        log.info("TeacherController::saveTeacher -> id passed = {}, DTO passed = {}", id, teacherDTO);
        return this.teacherService.updateTeacher(Long.valueOf(id), teacherDTO);
    }

}