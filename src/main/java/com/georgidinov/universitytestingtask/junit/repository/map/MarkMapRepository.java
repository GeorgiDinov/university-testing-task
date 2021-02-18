package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.domain.Mark;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({"default", "map"})
public class MarkMapRepository extends AbstractMapRepository<Mark, Long> {

    @Override
    public Set<Mark> findAll() {
        return super.findAll();
    }

    @Override
    public Mark findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Mark save(Mark mark) {
        return super.save(mark);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Mark mark) {
        super.delete(mark);
    }
}
