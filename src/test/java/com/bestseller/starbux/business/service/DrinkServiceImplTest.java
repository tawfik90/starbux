package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.repository.DrinkRepository;
import com.bestseller.starbux.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class DrinkServiceImplTest {

    private DrinkServiceImpl drinkService;

    @Mock
    private DrinkRepository drinkRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        drinkService = new DrinkServiceImpl(drinkRepository);
    }

    @Test
    public void add_ok() {
        ItemRequest itemRequest = new ItemRequest("Black Coffee", 4.00);
        given(drinkRepository.save(any(Drink.class))).willReturn(new Drink(itemRequest.getName(), itemRequest.getPrice(), 1));

        Drink result = drinkService.add(itemRequest);

        verify(drinkRepository).save(any(Drink.class));
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(itemRequest.getName());
        assertThat(result.getPrice()).isEqualTo(itemRequest.getPrice());
    }

    @Test
    public void update_ok() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Black Coffee", 5.00);
        Drink drink = new Drink(itemRequest.getName(), 4.00, 1);
        given(drinkRepository.findById(id)).willReturn(Optional.of(drink));
        given(drinkRepository.save(any(Drink.class))).willReturn(new Drink(itemRequest.getName(), itemRequest.getPrice(), 1));

        Drink result = drinkService.update(id, itemRequest);

        verify(drinkRepository).findById(id);
        verify(drinkRepository).save(any(Drink.class));
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(itemRequest.getName());
        assertThat(result.getPrice()).isEqualTo(itemRequest.getPrice());
    }

    @Test(expected = NotFoundException.class)
    public void update_shouldThrowNotFoundException() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Black Coffee", 5.00);
        given(drinkRepository.findById(id)).willReturn(Optional.empty());
        given(drinkRepository.save(any(Drink.class))).willReturn(new Drink(itemRequest.getName(), itemRequest.getPrice(), 1));

        drinkService.update(id, itemRequest);

        verify(drinkRepository).findById(id);
    }

    @Test
    public void delete_ok() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Black Coffee", 5.00);
        Drink drink = new Drink(itemRequest.getName(), 4.00, id);
        given(drinkRepository.findById(id)).willReturn(Optional.of(drink));

        drinkService.delete(id);

        verify(drinkRepository).delete(drink);
    }

    @Test(expected = NotFoundException.class)
    public void delete_shouldThrowNotFoundException() {
        Integer id = 1;

        drinkService.delete(id);

        verify(drinkRepository).findById(id);
    }

    @Test
    public void getAllDrinks_ok() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Black Coffee", 4.00, 1));
        drinks.add(new Drink("Latte", 5.00, 2));
        given(drinkRepository.findAll()).willReturn(drinks);

        List<Drink> results = drinkService.getAllDrinks();

        verify(drinkRepository).findAll();
        assertThat(results).isEqualTo(drinks);
    }
}