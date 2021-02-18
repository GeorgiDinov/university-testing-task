package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.domain.Parent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default", "map"})
public class ParentMapRepository extends AbstractMapRepository<Parent, Long> {

    @Override
    public Set<Parent> findAll() {
        return super.findAll();
    }

    @Override
    public Parent findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Parent save(Parent parent) {
        return super.save(parent);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Parent parent) {
        super.delete(parent);
    }
}