package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import com.bestseller.starbux.data.entity.OrderStatus;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.repository.OrderDetailsRepository;
import com.bestseller.starbux.data.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderDetailsRepository orderDetailsRepository;
    @Mock
    private DrinkService drinkService;
    @Mock
    private ToppingService toppingService;
    @Mock
    private DiscountService discountService;
    @Mock
    private CustomerService customerService;

    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository,
                orderDetailsRepository,
                drinkService,
                toppingService,
                discountService,
                customerService);
    }

    @Test
    public void create_of() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setId(1L);
        given(orderRepository.save(any(Order.class))).willReturn(order);

        Order result = orderService.create();

        verify(orderRepository).save(any(Order.class));
        assertThat(result).isEqualTo(order);
    }

    @Test
    public void addOrderDetail() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setId(1L);
        Drink drink = new Drink("Drink1", 4.00, 1);
        List<Integer> toggingIds = new ArrayList<>();
        toggingIds.add(1);
        toggingIds.add(2);
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(new Topping("T1", 1.00, 1));
        toppingList.add(new Topping("T2", 2.00, 2));
        OrderDetailsRequest orderDetailsRequest = new OrderDetailsRequest(1, toggingIds);
        OrderDetails orderDetails = new OrderDetails(order, drink);
        orderDetails.setId(1L);
        given(orderRepository.findByIdAndOrderStatus(any(Long.class), any(OrderStatus.class))).willReturn(Optional.of(order));
        given(drinkService.findDrinkByIdOrThrowException(any(Integer.class))).willReturn(drink);
        given(toppingService.getToppingsInIds(toggingIds)).willReturn(toppingList);
        given(orderDetailsRepository.save(any(OrderDetails.class))).willReturn(orderDetails);

        orderService.addOrderDetail(1L, orderDetailsRequest);

        verify(orderRepository).findByIdAndOrderStatus(any(Long.class), any(OrderStatus.class));
        verify(drinkService).findDrinkByIdOrThrowException(any(Integer.class));
    }

    @Test
    public void getOrderBy() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setId(1L);
        given(orderRepository.findById(any(Long.class))).willReturn(Optional.of(order));

        Order result = orderService.getOrderBy(1L);

        verify(orderRepository).findById(any(Long.class));
        assertThat(result).isEqualTo(order);
    }

    @Test
    public void finalizeOrder() {
        Customer customer = new Customer("tawfik90");
        customer.setId(1L);
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setId(1L);

        given(orderRepository.findByIdAndOrderStatus(any(Long.class), any(OrderStatus.class))).willReturn(Optional.of(order));
        orderService.finalizeOrder(1L, new CustomerRequest("tawfik90"));

        verify(orderRepository).findByIdAndOrderStatus(any(Long.class), any(OrderStatus.class));
        verify(discountService).getDiscountAmount(any(Order.class));
    }


}