package com.bestseller.starbux.business.service;

import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import com.bestseller.starbux.data.entity.OrderStatus;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DiscountServiceImplTest {

    private DiscountServiceImpl discountService;

    @Before
    public void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    public void getDiscountAmount_ifNumberOfItem3() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setOrderAmount(12.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetails(order, new Drink("D1", 1.00, 1)));
        orderDetails.add(new OrderDetails(order, new Drink("D2", 2.00, 2)));
        orderDetails.add(new OrderDetails(order, new Drink("D3", 3.00, 3)));
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(new Topping("T1", 1.00, 1));
        toppingList.add(new Topping("T2", 2.00, 2));
        toppingList.add(new Topping("T3", 3.00, 3));
        List<ToppingDetails> toppingDetailsList1 = new ArrayList<>();
        toppingDetailsList1.add(new ToppingDetails(toppingList.get(0), orderDetails.get(0)));
        List<ToppingDetails> toppingDetailsList2 = new ArrayList<>();
        toppingDetailsList2.add(new ToppingDetails(toppingList.get(1), orderDetails.get(1)));
        List<ToppingDetails> toppingDetailsList3 = new ArrayList<>();
        toppingDetailsList3.add(new ToppingDetails(toppingList.get(2), orderDetails.get(2)));
        orderDetails.get(0).setToppingDetails(toppingDetailsList1);
        orderDetails.get(1).setToppingDetails(toppingDetailsList2);
        orderDetails.get(2).setToppingDetails(toppingDetailsList3);
        order.setOrderDetails(orderDetails);

        double amount = discountService.getDiscountAmount(order);

        assertThat(amount).isEqualTo(2.00);
    }

    @Test
    public void getDiscountAmount_ifAmountMoreThan12() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setOrderAmount(20.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetails(order, new Drink("D1", 10.00, 1)));
        orderDetails.add(new OrderDetails(order, new Drink("D2", 7.00, 2)));
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(new Topping("T1", 1.00, 1));
        toppingList.add(new Topping("T2", 2.00, 2));
        List<ToppingDetails> toppingDetailsList1 = new ArrayList<>();
        toppingDetailsList1.add(new ToppingDetails(toppingList.get(0), orderDetails.get(0)));
        List<ToppingDetails> toppingDetailsList2 = new ArrayList<>();
        toppingDetailsList2.add(new ToppingDetails(toppingList.get(1), orderDetails.get(1)));
        List<ToppingDetails> toppingDetailsList3 = new ArrayList<>();
        orderDetails.get(0).setToppingDetails(toppingDetailsList1);
        orderDetails.get(1).setToppingDetails(toppingDetailsList2);
        order.setOrderDetails(orderDetails);

        double amount = discountService.getDiscountAmount(order);

        assertThat(amount).isEqualTo(5.00);
    }

    @Test
    public void getDiscountAmount_ifNumberOfItem3AndAmountMoreThan12() {
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        order.setOrderAmount(13.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetails(order, new Drink("D1", 1.00, 1)));
        orderDetails.add(new OrderDetails(order, new Drink("D2", 5.00, 2)));
        orderDetails.add(new OrderDetails(order, new Drink("D3", 5.00, 3)));
        List<Topping> toppingList = new ArrayList<>();
        toppingList.add(new Topping("T1", 5.00, 1));
        toppingList.add(new Topping("T2", 2.00, 2));
        toppingList.add(new Topping("T3", 3.00, 3));
        List<ToppingDetails> toppingDetailsList1 = new ArrayList<>();
        toppingDetailsList1.add(new ToppingDetails(toppingList.get(0), orderDetails.get(0)));
        List<ToppingDetails> toppingDetailsList2 = new ArrayList<>();
        toppingDetailsList2.add(new ToppingDetails(toppingList.get(1), orderDetails.get(1)));
        List<ToppingDetails> toppingDetailsList3 = new ArrayList<>();
        toppingDetailsList3.add(new ToppingDetails(toppingList.get(2), orderDetails.get(2)));
        orderDetails.get(0).setToppingDetails(toppingDetailsList1);
        orderDetails.get(1).setToppingDetails(toppingDetailsList2);
        orderDetails.get(2).setToppingDetails(toppingDetailsList3);
        order.setOrderDetails(orderDetails);

        double amount = discountService.getDiscountAmount(order);

        assertThat(amount).isEqualTo(6.00);
    }


}