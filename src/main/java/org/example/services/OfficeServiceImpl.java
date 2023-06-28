package org.example.services;
import org.example.repositories.Office;
import org.example.repositories.OfficeRepository;
import org.example.repositories.item;
import org.example.repositories.itemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class OfficeServiceImpl implements OfficeService  {
    private final OfficeRepository officeRepository;
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Optional<Office> getOffice(int invId){
        return officeRepository.getById(invId);
    }

    public List<Office> getAllOffice() {
        return officeRepository.findAll();
    }
    public void saveOffice(Office Office){
        officeRepository.save(Office);
    };
    public void deleteOffice(int OfficeId){
        officeRepository.delete(OfficeId);
    };
}
