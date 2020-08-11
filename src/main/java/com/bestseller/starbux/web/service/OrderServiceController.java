package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.business.service.OrderService;
import com.bestseller.starbux.data.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    final private OrderService orderService;

    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder() {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create());
    }

    @PostMapping("/{id}/drinks")
    public ResponseEntity<?> addItem(@PathVariable Long id,
                                     @Valid @RequestBody OrderDetailsRequest orderDetailsRequest) {
        return ResponseEntity.ok(orderService.addOrderDetail(id, orderDetailsRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderBy(id));
    }

    @PostMapping("/{id}/finalize")
    public ResponseEntity<Order> finalize(@PathVariable Long id,
                                          @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(orderService.finalizeOrder(id, customerRequest));
    }

}
