package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.business.service.ToppingService;
import com.bestseller.starbux.data.entity.Topping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/toppings")
public class AdminToppingServiceController {

    final private ToppingService toppingService;

    public AdminToppingServiceController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @PostMapping
    public ResponseEntity<Topping> add(@RequestBody ItemRequest itemRequest) {
        return ResponseEntity.ok(toppingService.add(itemRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topping> update(@PathVariable Integer id, @RequestBody ItemRequest itemRequest) {
        return ResponseEntity.ok(toppingService.update(id, itemRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        toppingService.delete(id);
    }

}
