package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.domain.SchoolClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default", "map"})
public class SchoolClassMapRepository extends AbstractMapRepository<SchoolClass, Long> {

    @Override
    public Set<SchoolClass> findAll() {
        return super.findAll();
    }

    @Override
    public SchoolClass findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {
        return super.save(schoolClass);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(SchoolClass schoolClass) {
        super.delete(schoolClass);
    }
}
