package org.example.services;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class itemServiceImpl implements itemService {
    @Autowired
    private final itemRepository ItemRepository;
    @Autowired
    private final OfficeRepository officeRepository;
    @Autowired
    private final OutsideRepository outsideRepository;
    @Autowired
    private final StoreRepository storeRepository;
    @Autowired
    private final TransactionRepository transactionRepository;

    public itemServiceImpl(itemRepository ItemRepository, OfficeRepository officeRepository,
                           OutsideRepository outsideRepository, StoreRepository storeRepository,
                           TransactionRepository transactionRepository) {
        this.ItemRepository = ItemRepository;
        this.officeRepository = officeRepository;
        this.outsideRepository = outsideRepository;
        this.storeRepository = storeRepository;
        this.transactionRepository = transactionRepository;

    }

    public Optional<item> getitem(int invId) {
        return ItemRepository.getitemById(invId);
    }

    public List<item> getAllItems() {
        return ItemRepository.findAll();
    }

    public void saveitem(item Item) {
        ItemRepository.save(Item);
    }

    ;

    public void deleteitem(int ItemId) {
        ItemRepository.delete(ItemId);
    }

    ;


    public Optional<Office> getOffice(int invId)
    {
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



    public Optional<Outside> getOutside(int invId)
    {
        return outsideRepository.getById(invId);
    }
    public List<Outside> getAllOutside() {
        return outsideRepository.findAll();
    }
    public void saveOutside(Outside outside){
        outsideRepository.save(outside);
    };
    public void deleteOutside(int invId){
        outsideRepository.delete(invId);
    };



    public Optional<Store> getStore(int invId)
    {
        return storeRepository.getById(invId);
    }
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }
    public void saveStore(Store store){
        storeRepository.save(store);
    };
    public void deleteStore(int invId){
        storeRepository.delete(invId);
    };


    public Optional<Transaction> getTransaction(int invId)
    {
        return transactionRepository.getById(invId);
    }
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    };
    public void deleteTransaction(int invId){
        transactionRepository.delete(invId);
    };
}


