package org.example.repositories;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

        Optional<Transaction> getById(int id);
        public List<Transaction> findAll();
        public void save(Transaction transaction);
        public void delete(int id);

}
