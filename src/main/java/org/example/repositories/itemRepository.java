package org.example.repositories;


import java.util.List;
import java.util.Optional;

public interface itemRepository {
    Optional<item> getitemById(int id);
    public List<item> findAll();
    public void save(item Item);
    public Optional<item> save_and_get(item Item);
    public void delete(int id);

}
