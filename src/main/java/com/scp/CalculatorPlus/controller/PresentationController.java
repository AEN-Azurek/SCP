package com.scp.CalculatorPlus.controller;

import com.scp.CalculatorPlus.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PresentationController {

    @RequestMapping(value = "/home")
    public String home() {
        return "templates/home";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello User";
    }

    @GetMapping(value = "/test", produces = "application/json")
    public Item item() {
        Item item = new Item();
        return item;
    }
}
