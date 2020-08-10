package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;
import com.bestseller.starbux.data.repository.ToppingDetailsRepository;
import com.bestseller.starbux.data.repository.ToppingRepository;
import com.bestseller.starbux.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToppingServiceImpl implements ToppingService {

    final private ToppingRepository toppingRepository;
    final private ToppingDetailsRepository toppingDetailsRepository;

    public ToppingServiceImpl(ToppingRepository toppingRepository, ToppingDetailsRepository toppingDetailsRepository) {
        this.toppingRepository = toppingRepository;
        this.toppingDetailsRepository = toppingDetailsRepository;
    }

    @Override
    public Topping add(ItemRequest itemRequest) {
        Topping topping = new Topping(itemRequest.getName(), itemRequest.getPrice());
        return toppingRepository.save(topping);
    }

    @Override
    public Topping update(Integer id, ItemRequest itemRequest) {
        Topping topping = findToppingByIdOrThrowException(id);
        topping.setName(itemRequest.getName());
        topping.setPrice(itemRequest.getPrice());
        return toppingRepository.save(topping);
    }

    @Override
    public void delete(Integer id) {
        Topping topping = findToppingByIdOrThrowException(id);
        toppingRepository.delete(topping);
    }

    @Override
    public List<Topping> getAllToppings() {
        return toppingRepository.findAll();
    }

    @Override
    public Topping findToppingByIdOrThrowException(Integer id) {
        return toppingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topping id is not exist"));
    }

    @Override
    public List<ToppingDetails> addToppingsToDrink(List<ToppingDetails> toppingDetailsList) {
        return toppingDetailsRepository.saveAll(toppingDetailsList);
    }
}
