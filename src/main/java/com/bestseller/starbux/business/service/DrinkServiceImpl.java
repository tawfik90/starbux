package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.repository.DrinkRepository;
import com.bestseller.starbux.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {

    final private DrinkRepository drinkRepository;

    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Drink add(ItemRequest itemRequest) {
        log.info("Entered add(ItemRequest) method");
        Drink drink = new Drink(itemRequest.getName(), itemRequest.getPrice());
        return drinkRepository.save(drink);
    }

    @Override
    public Drink update(Integer id, ItemRequest itemRequest) {
        log.info("Entered update(Integer, ItemRequest) method");
        Drink drink = findDrinkByIdOrThrowException(id);
        drink.setName(itemRequest.getName());
        drink.setPrice(itemRequest.getPrice());
        return drinkRepository.save(drink);
    }

    @Override
    public void delete(Integer id) {
        log.info("Entered delete(Integer) method");
        Drink drink = findDrinkByIdOrThrowException(id);
        drinkRepository.delete(drink);
    }

    @Override
    public List<Drink> getAllDrinks() {
        log.info("Entered getAllDrinks() method");
        return drinkRepository.findAll();
    }

    @Override
    public Drink findDrinkByIdOrThrowException(Integer id) {
        log.info("Entered findDrinkByIdOrThrowException(Integer) method");
        return drinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This id is not found"));
    }
}
