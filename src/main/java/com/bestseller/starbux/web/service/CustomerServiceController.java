package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.service.CustomerService;
import com.bestseller.starbux.data.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerServiceController {

    final private CustomerService customerService;

    public CustomerServiceController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.create(customerRequest));
    }
}
