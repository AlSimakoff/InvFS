package org.example.repositories;

import java.util.List;
import java.util.Optional;

public interface OutsideRepository {

        Optional<Outside> getById(int id);
        public List<Outside> findAll();
        public void save(Outside outside);
        public void delete(int id);

}
