package com.georgidinov.universitytestingtask.junit.api.v1.mapper;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static com.georgidinov.universitytestingtask.junit.util.ApplicationConstants.TEACHER_BASE_URL;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "teacherUrl", expression = "java(teacherUrl(teacher.getId()))")
    TeacherDTO teacherToTeacherDTO(Teacher teacher);


    default Teacher teacherDTOToTeacher(TeacherDTO teacherDTO) throws CustomValidationException {
        return Teacher.builder()
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .build();
    }

    default String teacherUrl(Long id) {
        return TEACHER_BASE_URL + "/" + id;
    }

}