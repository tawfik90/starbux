package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.business.service.DrinkService;
import com.bestseller.starbux.data.entity.Drink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * AdminDrinkServiceController exposes end-points for adding, updating and deleting drinks
 */
@RestController
@RequestMapping("/admin/drinks")
@Slf4j
public class AdminDrinkServiceController {

    final private DrinkService drinkService;

    /**
     * Dependency injection default constructor
     *
     * @param drinkService {@link DrinkService}
     */
    public AdminDrinkServiceController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping
    public ResponseEntity<Drink> add(@RequestBody ItemRequest itemRequest) {
        log.info("Entered /admin/drinks end-point for adding new drink");
        return ResponseEntity.ok(drinkService.add(itemRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drink> update(@PathVariable Integer id, @RequestBody ItemRequest itemRequest) {
        log.info("Entered /admin/drinks/{} end-point for updating drink", id);
        return ResponseEntity.ok(drinkService.update(id, itemRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        log.info("Entered /admin/drinks/{} end-point for deleting drink", id);
        drinkService.delete(id);
    }

}
