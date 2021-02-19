package com.georgidinov.universitytestingtask.junit.service;

import com.georgidinov.universitytestingtask.junit.api.v1.mapper.TeacherMapper;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherDTO;
import com.georgidinov.universitytestingtask.junit.api.v1.model.TeacherListDTO;
import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import com.georgidinov.universitytestingtask.junit.exception.CustomValidationException;
import com.georgidinov.universitytestingtask.junit.repository.map.TeacherMapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.georgidinov.universitytestingtask.junit.validaton.GlobalValidator.basePersonNameValidator;

/**
 * Service the controller class with business logic
 * Pack, send and return dto objects to and from the data access object(repository)
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {


    private final TeacherMapRepository teacherMapRepository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapRepository teacherMapRepository,
                              TeacherMapper teacherMapper) {
        this.teacherMapRepository = teacherMapRepository;
        this.teacherMapper = teacherMapper;
    }

    /**
     * @return DTO object holding the necessary data for the end user / client
     */
    @Override
    public TeacherListDTO findAllTeachers() {
        log.info("TeacherServiceImpl::findAllTeachers");
        return new TeacherListDTO(
                this.teacherMapRepository.findAll().stream()
                        .map(teacherMapper::teacherToTeacherDTO)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public TeacherDTO findTeacherById(Long id) throws NoSuchElementException {
        log.info("TeacherServiceImpl::findTeacherById -> id passed = {}", id);
        Teacher foundTeacher = this.validateTeacherExists(id);
        return this.teacherMapper.teacherToTeacherDTO(foundTeacher);
    }

    @Override
    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) throws CustomValidationException {
        log.info("TeacherServiceImpl::saveTeacher -> DTO passed = {}", teacherDTO);
        this.validateTeacherDTO(teacherDTO);
        Teacher teacher = this.teacherMapper.teacherDTOToTeacher(teacherDTO);
        return this.saveTeacherToDatabase(teacher);
    }

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        return null;
    }

    @Override
    public void deleteTeacherById(Long id) {

    }

    //== private methods ==
    private void validateTeacherDTO(TeacherDTO teacherDTO) throws CustomValidationException {
        basePersonNameValidator.validate(teacherDTO.getFirstName());
        basePersonNameValidator.validate(teacherDTO.getLastName());
    }

    private TeacherDTO saveTeacherToDatabase(Teacher teacher) {
        return this.teacherMapper.teacherToTeacherDTO(this.teacherMapRepository.save(teacher));
    }

    private Teacher validateTeacherExists(Long id) {
        Teacher teacher = this.teacherMapRepository.findById(id);
        if (teacher == null) {
            throw new NoSuchElementException("Record with ID = " + id + " Not Found");
        }
        return teacher;
    }

}
