package org.example.services;

import org.example.repositories.*;

import java.util.List;
import java.util.Optional;

public interface itemService {
    Optional<item> getitem(int invId);
    List<item> getAllItems();
    void saveitem(item Item);
    void deleteitem(int ItemId);
}
