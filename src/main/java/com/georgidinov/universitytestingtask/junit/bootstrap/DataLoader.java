package com.georgidinov.universitytestingtask.junit.bootstrap;

import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.repository.map.TeacherMapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final TeacherMapRepository teacherMapRepository;

    @Autowired
    public DataLoader(TeacherMapRepository teacherMapRepository) {
        this.teacherMapRepository = teacherMapRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadTeachers();
        log.info("teachers bootstrapped successfully... number of teachers = {}", teacherMapRepository.findAll().size());
    }


    private void loadTeachers() throws CustomValidationException {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(Teacher.builder().firstName("John").lastName("Doe").build());
        teacherList.add(Teacher.builder().firstName("Anna").lastName("Smith").build());
        teacherList.add(Teacher.builder().firstName("Ben").lastName("Jones").build());
        teacherList.add(Teacher.builder().firstName("Charlie").lastName("Sheen").build());
        teacherList.forEach(teacherMapRepository::save);
    }
}