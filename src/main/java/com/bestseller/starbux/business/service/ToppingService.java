package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;

import java.util.List;

public interface ToppingService {
    Topping add(ItemRequest itemRequest);

    Topping update(Integer id, ItemRequest itemRequest);

    void delete(Integer id);

    List<Topping> getAllToppings();

    Topping findToppingByIdOrThrowException(Integer id);

    List<ToppingDetails> saveAllToppingDetails(List<ToppingDetails> toppingDetailsList);

    List<Topping> getToppingsInIds(List<Integer> ids);
}
