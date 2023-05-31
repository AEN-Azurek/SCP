package com.scp.CalculatorPlus.service.factory;

import com.scp.CalculatorPlus.model.Item;
import com.scp.CalculatorPlus.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepo;

    public Item findItemById(long id) {
        return itemRepo.findById(id).get();
    }

    public Item findByItemName(String name) {
        return itemRepo.findByItemName(name);
    }

    public List<Item> findAll() {
        return itemRepo.findAll();
    }
}
