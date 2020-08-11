package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.repository.CustomerRepository;
import com.bestseller.starbux.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerServiceImpl customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void create_ok() {
        CustomerRequest customerRequest = new CustomerRequest("tawfik90");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("tawfik90");
        given(customerRepository.save(any(Customer.class))).willReturn(customer);

        Customer result = customerService.create(customerRequest);

        verify(customerRepository).save(any(Customer.class));
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void getCustomerBy_ok() {
        Long id = 1L;
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("tawfik90");
        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        Customer result = customerService.getCustomerBy(id);

        verify(customerRepository).findById(id);
        assertThat(result.getId()).isNotNull();
    }

    @Test(expected = NotFoundException.class)
    public void getCustomerById_shouldThrowException() {
        Long id = 1L;
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("tawfik90");
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        Customer result = customerService.getCustomerBy(id);

        verify(customerRepository).findById(id);
    }

    @Test
    public void GetCustomerByUsername_ok() {
        String username = "tawfik90";
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("tawfik90");
        given(customerRepository.findCustomerByUsername(username)).willReturn(Optional.of(customer));

        Customer result = customerService.getCustomerBy(username);

        verify(customerRepository).findCustomerByUsername(username);
        assertThat(result).isEqualTo(customer);
    }

    @Test(expected = NotFoundException.class)
    public void GetCustomerByUsername_shouldThrowException() {
        String username = "tawfik90";
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUsername("tawfik90");
        given(customerRepository.findCustomerByUsername(username)).willReturn(Optional.empty());

        Customer result = customerService.getCustomerBy(username);

        verify(customerRepository).findCustomerByUsername(username);

    }
}