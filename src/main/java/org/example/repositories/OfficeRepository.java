package org.example.repositories;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository {

        Optional<Office> getById(int id);
        public List<Office> findAll();
        public void save(Office office);
        public void delete(int id);

}
