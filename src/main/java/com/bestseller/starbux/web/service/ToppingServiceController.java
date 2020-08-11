package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.service.ToppingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/toppings")
public class ToppingServiceController {

    final private ToppingService toppingService;

    public ToppingServiceController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllToppings() {
        log.info("Entered /toppings end-point");
        return ResponseEntity.ok(toppingService.getAllToppings());
    }
}
