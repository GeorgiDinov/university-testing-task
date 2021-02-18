package com.georgidinov.universitytestingtask.junit.api.v1.mapper;

import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static com.georgidinov.universitytestingtask.junit.util.ApplicationConstants.TEACHER_BASE_URL;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "teacherUrl", expression = "java(teacherUrl(teacher.getId()))")
    TeacherDTO teacherToTeacherDTO(Teacher teacher);


    default String teacherUrl(Long id) {
        return TEACHER_BASE_URL + "/" + id;
    }

}