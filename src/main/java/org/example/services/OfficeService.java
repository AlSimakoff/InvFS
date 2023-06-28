package org.example.services;

import org.example.repositories.Office;
import org.example.repositories.item;

import java.util.List;
import java.util.Optional;

public interface OfficeService {
    Optional<Office> getOffice(int invId);
    List<Office> getAllOffice();
    void saveOffice(Office office);
    void deleteOffice(int ItemId);
}
