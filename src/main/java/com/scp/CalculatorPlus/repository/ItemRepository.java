package com.scp.CalculatorPlus.repository;

import com.scp.CalculatorPlus.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByItemName(String name);
}
