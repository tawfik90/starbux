package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;
import com.bestseller.starbux.data.repository.ToppingDetailsRepository;
import com.bestseller.starbux.data.repository.ToppingRepository;
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


public class ToppingServiceImplTest {

    private ToppingServiceImpl toppingService;

    @Mock
    private ToppingRepository toppingRepository;

    @Mock
    private ToppingDetailsRepository toppingDetailsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        toppingService = new ToppingServiceImpl(toppingRepository, toppingDetailsRepository);
    }

    @Test
    public void add_ok() {
        ItemRequest itemRequest = new ItemRequest("Milk", 2.00);
        given(toppingRepository.save(any(Topping.class))).willReturn(new Topping(itemRequest.getName(), itemRequest.getPrice(), 1));

        Topping result = toppingService.add(itemRequest);

        verify(toppingRepository).save(any(Topping.class));
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(itemRequest.getName());
        assertThat(result.getPrice()).isEqualTo(itemRequest.getPrice());
    }

    @Test
    public void update_ok() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Milk", 2.00);
        Topping topping = new Topping(itemRequest.getName(), 4.00, 1);
        given(toppingRepository.findById(id)).willReturn(Optional.of(topping));
        given(toppingRepository.save(any(Topping.class))).willReturn(new Topping(itemRequest.getName(), itemRequest.getPrice(), 1));

        Topping result = toppingService.update(id, itemRequest);

        verify(toppingRepository).findById(id);
        verify(toppingRepository).save(any(Topping.class));
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(itemRequest.getName());
        assertThat(result.getPrice()).isEqualTo(itemRequest.getPrice());
    }

    @Test(expected = NotFoundException.class)
    public void update_shouldThrowNotFoundException() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Milk", 4.00);
        given(toppingRepository.findById(id)).willReturn(Optional.empty());
        given(toppingRepository.save(any(Topping.class))).willReturn(new Topping(itemRequest.getName(), itemRequest.getPrice(), 1));

        toppingService.update(id, itemRequest);

        verify(toppingRepository).findById(id);
    }

    @Test
    public void delete_ok() {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Milk", 4.00);
        Topping topping = new Topping(itemRequest.getName(), 4.00, id);
        given(toppingRepository.findById(id)).willReturn(Optional.of(topping));

        toppingService.delete(id);

        verify(toppingRepository).delete(topping);
    }

    @Test(expected = NotFoundException.class)
    public void delete_shouldThrowNotFoundException() {
        Integer id = 1;

        toppingService.delete(id);

        verify(toppingRepository).findById(id);
    }

    @Test
    public void getAllToppings_ok() {
        List<Topping> toppings = new ArrayList<>();
        toppings.add(new Topping("Milk", 2.00, 1));
        toppings.add(new Topping("Hazelnut syrup", 2.00, 2));
        given(toppingRepository.findAll()).willReturn(toppings);

        List<Topping> results = toppingService.getAllToppings();

        verify(toppingRepository).findAll();
        assertThat(results).isEqualTo(toppings);
    }

    @Test
    public void saveAllToppingDetails() {

        toppingService.saveAllToppingDetails(new ArrayList<>());

        verify(toppingDetailsRepository).saveAll(any(List.class));
    }

    @Test
    public void getToppingsInIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(new Topping("T1", 1.00, 1));
        toppingList.add(new Topping("T2", 2.00, 2));
        given(toppingRepository.findToppingByIdIn(ids)).willReturn(toppingList);

        toppingService.getToppingsInIds(ids);

        verify(toppingRepository).findToppingByIdIn(ids);
    }
}