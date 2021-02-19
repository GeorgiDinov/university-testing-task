package com.georgidinov.universitytestingtask.junit.repository.map;

import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * Defines common behavior through all repositories
 */
public abstract class AbstractMapRepository<T extends BaseEntity, ID extends Long> {


    /**
     * The underlying data structure for the 'fake' repository
     */
    protected Map<Long, T> map = new HashMap<>();


    /**
     * @return All saved objects
     */
    protected Set<T> findAll() {
        return new HashSet<>(map.values());
    }


    /**
     * @param id of the object
     * @return Single instance
     */
    protected T findById(ID id) {
        return map.get(id);
    }


    /**
     * @param object to be saved
     * @return the saved object
     */
    protected T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(this.getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Objects cannot be null");
        }
        return object;
    }


    /**
     * Deletes object with the given id
     *
     * @param id of the object we wish to delete
     */
    protected void deleteById(ID id) {
        map.remove(id);
    }


    /**
     * Deletes the passed object
     *
     * @param object
     */
    protected void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    /**
     * Plays the role of id generator to simulate DB auto increment
     *
     * @return Long value for id
     */
    //== private methods ==
    private Long getNextId() {
        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }
        return nextId;
    }//end of method getNextId

}//end of abstract class AbstractMapService