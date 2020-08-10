package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.repository.DrinkRepository;
import com.bestseller.starbux.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    final private DrinkRepository drinkRepository;

    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Drink add(ItemRequest itemRequest) {
        Drink drink = new Drink(itemRequest.getName(), itemRequest.getPrice());
        return drinkRepository.save(drink);
    }

    @Override
    public Drink update(Integer id, ItemRequest itemRequest) {
        Drink drink = findDrinkByIdOrThrowException(id);
        drink.setName(itemRequest.getName());
        drink.setPrice(itemRequest.getPrice());
        return drinkRepository.save(drink);
    }

    @Override
    public void delete(Integer id) {
        Drink drink = findDrinkByIdOrThrowException(id);
        drinkRepository.delete(drink);
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    @Override
    public Drink findDrinkByIdOrThrowException(Integer id) {
        return drinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This id is not found"));
    }
}
