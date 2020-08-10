package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.service.ToppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppings")
public class ToppingServiceController {

    final private ToppingService toppingService;

    public ToppingServiceController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllToppings() {
        return ResponseEntity.ok(toppingService.getAllToppings());
    }
}
