package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.service.DrinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drinks")
public class DrinkServiceController {

    final private DrinkService drinkService;

    public DrinkServiceController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDrinks() {
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }
}
