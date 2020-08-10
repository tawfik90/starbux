package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.data.entity.Customer;

public interface CustomerService {

    Customer create(CustomerRequest customerRequest);

    Customer getCustomerBy(Long id);

    Customer getCustomerBy(String username);

}
