package org.example.repositories;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {

        Optional<Store> getById(int id);
        public List<Store> findAll();
        public void save(Store store);
        public void delete(int id);

}
