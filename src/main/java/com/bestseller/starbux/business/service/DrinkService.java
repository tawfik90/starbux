package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Drink;

import java.util.List;

public interface DrinkService {

    Drink add(ItemRequest itemRequest);

    Drink update(Integer id, ItemRequest itemRequest);

    void delete(Integer id);

    List<Drink> getAllDrinks();

    Drink findDrinkByIdOrThrowException(Integer id);
}
