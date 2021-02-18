package com.georgidinov.universitytestingtask.junit.service;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;

/**
 * Defines teacher service behaviour
 */
public interface TeacherService {

    TeacherListDTO findAllTeachers();

    TeacherDTO findTeacherById(Long id);

    TeacherDTO saveTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);

    void deleteTeacherById(Long id);

}