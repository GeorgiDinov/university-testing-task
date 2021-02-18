package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.domain.Subject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default", "map"})
public class SubjectMapRepository extends AbstractMapRepository<Subject, Long> {

    @Override
    public Set<Subject> findAll() {
        return super.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return super.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Subject subject) {
        super.delete(subject);
    }
}
