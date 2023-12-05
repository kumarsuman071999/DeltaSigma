package com.example.deltasigma.service;

import com.example.deltasigma.exception.ItemNotFoundException;
import com.example.deltasigma.exception.ItemValidationException;
import com.example.deltasigma.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ItemService {
    private final List<Item> items=new ArrayList<>();

    private Integer id=1;

    //intail data added ti list of item
    public ItemService(){
        items.add(new Item(id++,"Iphone 14","It is Iphone 14 model"));
        items.add(new Item(id++,"Iphone 13","It is Iphone 13 model"));

    }

    public List<Item> getAllItem(){
        return items;
    }

    public Item createItem(Item item){
        // item name and description must be provided
            if(item==null || item.getDescription()==null || item.getName()== null || item.getDescription().isEmpty() || item.getName().isEmpty() ){
                throw new ItemValidationException("All item should be required");
            }

        item.setId(id++);
        items.add(item);
        return item;
    }

    public Optional<Item> getItemById(int id){
        // Id must be positive
        if(id<=0){
            return Optional.empty();
        }
        return Optional.ofNullable(items.stream().filter(item -> item.getId().equals(id))
                .findFirst().
                orElseThrow(() -> new ItemNotFoundException(id)));
    }
}
