package com.scp.CalculatorPlus.service.factory;

import com.scp.CalculatorPlus.model.buildings.Attribute;
import com.scp.CalculatorPlus.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepo;

    public Attribute findByAttributeName(String attributeName) {
        return attributeRepo.findByAttributeName(attributeName);
    }


}
