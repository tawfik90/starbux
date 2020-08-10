package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.repository.CustomerRepository;
import com.bestseller.starbux.exception.AlreadyExistException;
import com.bestseller.starbux.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(CustomerRequest customerRequest) {
        if (customerRepository.findCustomerByUsername(customerRequest.getUsername()).isPresent()) {
            throw new AlreadyExistException("This username is already exist");
        }
        return customerRepository.save(new Customer(customerRequest.getUsername()));
    }

    @Override
    public Customer getCustomerBy(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("CustomerId is not exist"));
    }

    @Override
    public Customer getCustomerBy(String username) {
        return customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new NotFoundException("This username is not found"));
    }


}
