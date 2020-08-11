package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;
import com.bestseller.starbux.data.repository.ToppingDetailsRepository;
import com.bestseller.starbux.data.repository.ToppingRepository;
import com.bestseller.starbux.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("Entered add(ItemRequest) method");
        Topping topping = new Topping(itemRequest.getName(), itemRequest.getPrice());
        return toppingRepository.save(topping);
    }

    @Override
    public Topping update(Integer id, ItemRequest itemRequest) {
        log.info("Entered update(Integer, ItemRequest) method");
        Topping topping = findToppingByIdOrThrowException(id);
        topping.setName(itemRequest.getName());
        topping.setPrice(itemRequest.getPrice());
        return toppingRepository.save(topping);
    }

    @Override
    public void delete(Integer id) {
        log.info("Entered delete(Integer, ItemRequest) method");
        Topping topping = findToppingByIdOrThrowException(id);
        toppingRepository.delete(topping);
    }

    @Override
    public List<Topping> getAllToppings() {
        log.info("Entered getAllToppings() method");
        return toppingRepository.findAll();
    }

    @Override
    public Topping findToppingByIdOrThrowException(Integer id) {
        log.info("Entered findToppingByIdOrThrowException(Integer) method");
        return toppingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topping id is not exist"));
    }

    @Override
    public List<ToppingDetails> saveAllToppingDetails(List<ToppingDetails> toppingDetailsList) {
        log.info("Entered saveAllToppingDetails(List<ToppingDetails>) method");
        return toppingDetailsRepository.saveAll(toppingDetailsList);
    }

    @Override
    public List<Topping> getToppingsInIds(List<Integer> ids) {
        log.info("Entered getToppingsInIds(List<Integer>) method");
        return toppingRepository.findToppingByIdIn(ids);
    }
}
