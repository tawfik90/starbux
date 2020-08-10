package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CartResponse;
import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.entity.Drink;
import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import com.bestseller.starbux.data.entity.OrderStatus;
import com.bestseller.starbux.data.entity.ToppingDetails;
import com.bestseller.starbux.data.repository.OrderDetailsRepository;
import com.bestseller.starbux.data.repository.OrderRepository;
import com.bestseller.starbux.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Order order = new Order(LocalDate.now(), OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    @Override
    public CartResponse addDrink(Long orderId, OrderDetailsRequest orderDetailsRequest) {
        Order order = findOrderByIdOrThrowException(orderId);
        Drink drink = drinkService.findDrinkByIdOrThrowException(orderDetailsRequest.getDrinkId());

        List<ToppingDetails> toppingDetailsList = new ArrayList<>();
        if (Objects.nonNull(orderDetailsRequest.getToppingIds()) && !orderDetailsRequest.getToppingIds().isEmpty()) {
            orderDetailsRequest
                    .getToppingIds()
                    .forEach(id -> {
                        ToppingDetails toppingDetails = new ToppingDetails();
                        toppingDetails.setTopping(toppingService.findToppingByIdOrThrowException(id));
                        toppingDetailsList.add(toppingDetails);
                    });
        }
        OrderDetails orderDetails = orderDetailsRepository.save(new OrderDetails(order, drink));
        toppingDetailsList.forEach(toppingDetails -> {
            toppingDetails.setOrderDetails(orderDetails);
        });
        orderDetails.setToppingDetails(toppingService.addToppingsToDrink(toppingDetailsList));
        orderRepository.save(addToOrderAmount(orderDetails));
        return new CartResponse(orderDetails, orderRepository.findById(orderId).get());
    }

    @Override
    public Order getOrderBy(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("OrderId is not exist"));
    }

    @Override
    public Order finalizeOrder(Long orderId, CustomerRequest customerRequest) {
        Customer customer = customerService.getCustomerBy(customerRequest.getUsername());
        Order order = findOrderByIdOrThrowException(orderId);
        double discountAmount = discountService.getDiscountAmount(order);
        order.setDiscountAmount(discountAmount);
        order.setOriginalAmount(order.getOrderAmount() - discountAmount);
        order.setOrderStatus(OrderStatus.FINISHED);
        order.setCustomer(customer);
        orderRepository.save(order);
        return order;
    }

    private Order addToOrderAmount(OrderDetails orderDetails) {
        Double totalAmount = 0.00d;
        totalAmount += orderDetailsRepository.getTotalAmountForDrinks(orderDetails.getOrder().getId());
        totalAmount += orderDetailsRepository.getTotalAmountForToppings(orderDetails.getOrder().getId());
        Order order = orderDetails.getOrder();
        order.setOrderAmount(totalAmount);
        order.getOrderDetails().add(orderDetails);
        return order;
    }


    private Order findOrderByIdOrThrowException(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order id is not exist"));
    }
}
