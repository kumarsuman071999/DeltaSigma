package com.example.deltasigma.controller;

import com.example.deltasigma.model.Item;
import com.example.deltasigma.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem(){
        return new ResponseEntity<>(itemService.getAllItem(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody Item item){
        itemService.createItem(item);
        return new ResponseEntity<>("Item added to list: ",HttpStatus.CREATED);

    }
    @GetMapping ("/{id}")
    ResponseEntity<Optional<Item>> getItemById(@PathVariable int id){
        Optional<Item> item=itemService.getItemById(id);

        return new ResponseEntity<>(item, HttpStatus.FOUND);
    }
}
