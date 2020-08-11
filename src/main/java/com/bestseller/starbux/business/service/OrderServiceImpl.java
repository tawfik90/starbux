package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import com.bestseller.starbux.data.entity.OrderStatus;
import com.bestseller.starbux.data.entity.Topping;
import com.bestseller.starbux.data.entity.ToppingDetails;
import com.bestseller.starbux.data.repository.OrderDetailsRepository;
import com.bestseller.starbux.data.repository.OrderRepository;
import com.bestseller.starbux.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    final private OrderRepository orderRepository;
    final private OrderDetailsRepository orderDetailsRepository;
    final private DrinkService drinkService;
    final private ToppingService toppingService;
    final private DiscountService discountService;
    final private CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailsRepository orderDetailsRepository,
                            DrinkService drinkService,
                            ToppingService toppingService,
                            DiscountService discountService,
                            CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.drinkService = drinkService;
        this.toppingService = toppingService;
        this.discountService = discountService;
        this.customerService = customerService;
    }

    @Override
    public Order create() {
        log.info("Entered create() method");
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    @Override
    public Order addOrderDetail(Long orderId, OrderDetailsRequest orderDetailsRequest) {
        log.info("Entered addOrderDetail(Long, OrderDetailsRequest) method for orderId {}", orderId);
        log.info("Start Lod order data by id {}", orderId);
        Order order = getOrderByIdAndStatusOrThrowException(orderId, OrderStatus.IN_PROGRESS);
        Drink drink = drinkService.findDrinkByIdOrThrowException(orderDetailsRequest.getDrinkId());

        List<Topping> toppings = toppingService.getToppingsInIds(orderDetailsRequest.getToppingIds());

        List<ToppingDetails> toppingDetailsList = new ArrayList<>();

        OrderDetails orderDetails = orderDetailsRepository.save(new OrderDetails(order, drink));
        toppings.forEach(topping -> toppingDetailsList.add(new ToppingDetails(topping, orderDetails)));

        orderDetails.setToppingDetails(toppingService.saveAllToppingDetails(toppingDetailsList));

        order.setOrderAmount(getOrderTotalAmount(orderDetails));
        return update(order);
    }

    private Double getOrderTotalAmount(OrderDetails orderDetails) {
        log.info("Entered getOrderTotalAmount(OrderDetails)");
        Double totalAmount = 0.00d;
        totalAmount += orderDetailsRepository.getTotalAmountForDrinks(orderDetails.getOrder().getId());
        totalAmount += orderDetailsRepository.getTotalAmountForToppings(orderDetails.getOrder().getId());
        return totalAmount;
    }

    @Override
    public Order getOrderBy(Long id) {
        log.info("Entered getOrderBy(Long) {}", id);
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("OrderId is not exist"));
    }

    @Override
    public Order finalizeOrder(Long orderId, CustomerRequest customerRequest) {
        log.info("Entered finalizeOrder(Long, CustomerRequest) for orderId {}", orderId);
        Customer customer = customerService.getCustomerBy(customerRequest.getUsername());
        Order order = getOrderByIdAndStatusOrThrowException(orderId, OrderStatus.IN_PROGRESS);
        double discountAmount = discountService.getDiscountAmount(order);
        order.setDiscountAmount(discountAmount);
        order.setOriginalAmount(order.getOrderAmount() - discountAmount);
        order.setOrderStatus(OrderStatus.FINISHED);
        order.setCustomer(customer);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        log.info("Entered update(Order)");
        return orderRepository.saveAndFlush(order);
    }

    private Order getOrderByIdAndStatusOrThrowException(Long id, OrderStatus orderStatus) {
        log.info("Entered getOrderByIdAndStatusOrThrowException(Long, OrderStatus)");
        return orderRepository.findByIdAndOrderStatus(id, orderStatus)
                .orElseThrow(() -> new NotFoundException("Order id is not exist"));
    }
}
