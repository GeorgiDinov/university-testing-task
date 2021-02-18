package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.domain.Teacher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default", "map"})
public class TeacherMapRepository extends AbstractMapRepository<Teacher, Long> {

    @Override
    public Set<Teacher> findAll() {
        return super.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return super.save(teacher);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Teacher teacher) {
        super.delete(teacher);
    }
}