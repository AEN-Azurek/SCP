package com.scp.CalculatorPlus.repository;

import com.scp.CalculatorPlus.model.buildings.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Attribute findByAttributeName(String attributeName);
}
