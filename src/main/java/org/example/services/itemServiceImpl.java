package org.example.services;
import jakarta.persistence.EntityNotFoundException;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Optional<Office> getOffice(Integer invId)
    {
        return officeRepository.getById(invId);
    }


    public List<Office> getAllOffice() {
        return officeRepository.findAll();
    }
    public OfficeFull getOfficeFull(Integer invId){
        item itm=ItemRepository.getitemById(invId).orElse(new item(0,"","",""));
        Office ofic=officeRepository.getById(invId).orElse(new Office(0,"","",""));
        OfficeFull oficFll= new OfficeFull(itm.id(),itm.name(),itm.ser_numb(),itm.inv_numb(), ofic.Date_delivery(),ofic.Action(),ofic.note());
        return oficFll;
    }
    public List<OfficeFull> getAllOfficeFull() {
        List<Office> oficList=officeRepository.findAll();
        List<OfficeFull> OfcFllsList=new ArrayList<OfficeFull>();
        for(Office ofc : oficList){
            item itm=ItemRepository.getitemById(ofc.id()).orElse(new item(0,"","",""));
            OfcFllsList.add(new OfficeFull(itm.id(),itm.name(), itm.ser_numb(), itm.inv_numb(),ofc.Date_delivery(),ofc.Action(),ofc.note()));
        }
    return OfcFllsList;
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
    public OutsideFull getOutsideFull(Integer invId){
        item itm=ItemRepository.getitemById(invId).orElse(new item(0,"","",""));
        Outside out=outsideRepository.getById(invId).orElse(new Outside(0,"","","",""));
        OutsideFull outFll= new OutsideFull(itm.id(),itm.name(),itm.ser_numb(),itm.inv_numb(), out.Date_delivery(), out.Date_take(), out.Action(),out.note());
        return outFll;
    }
    public List<OutsideFull> getAllOutsideFull() {
        List<Outside> outList=outsideRepository.findAll();
        List<OutsideFull> outFllsList=new ArrayList<OutsideFull>();
        for(Outside out : outList){
            item itm=ItemRepository.getitemById(out.id()).orElse(new item(0,"","",""));
            outFllsList.add(new OutsideFull(itm.id(),itm.name(), itm.ser_numb(), itm.inv_numb(), out.Date_delivery(), out.Date_take(), out.Action(),out.note()));
        }
        return outFllsList;
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

    public StoreFull getStoreFull(Integer invId){
        item itm=ItemRepository.getitemById(invId).orElse(new item(0,"","",""));
        Store str=storeRepository.getById(invId).orElse(new Store(0,"",""));
        StoreFull strFll= new StoreFull(itm.id(),itm.name(),itm.ser_numb(),itm.inv_numb(), str.Action(),str.note());
        return strFll;
    }
    public List<StoreFull> getAllStoreFull() {
        List<Store> strList=storeRepository.findAll();
        List<StoreFull> strFllsList=new ArrayList<StoreFull>();
        for(Store str : strList){
            item itm=ItemRepository.getitemById(str.id()).orElse(new item(0,"","",""));
            strFllsList.add(new StoreFull(itm.id(),itm.name(), itm.ser_numb(), itm.inv_numb(),str.Action(),str.note()));
        }
        return strFllsList;
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


