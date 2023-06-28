package org.example.services;
import org.example.repositories.item;
import org.example.repositories.itemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class itemServiceImpl implements itemService  {
    private final itemRepository ItemRepository;
    public itemServiceImpl(itemRepository ItemRepository) {
        this.ItemRepository = ItemRepository;
    }

    public Optional<item> getitem(int invId){
        return ItemRepository.getitemById(invId);
    }

    public List<item> getAllItems() {
        return ItemRepository.findAll();
    }
    public void saveitem(item Item){
    ItemRepository.save(Item);
    };
    public void deleteitem(int ItemId){
    ItemRepository.delete(ItemId);
    };
}
