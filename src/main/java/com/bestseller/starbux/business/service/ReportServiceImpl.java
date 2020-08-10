package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerOrdersReportResponse;
import com.bestseller.starbux.business.domain.MostToppingsReportResponse;
import com.bestseller.starbux.data.entity.Customer;
import com.bestseller.starbux.data.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    final private OrderRepository orderRepository;
    final private CustomerService customerService;

    public ReportServiceImpl(OrderRepository orderRepository,
                             CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Override
    public CustomerOrdersReportResponse getTotalAmountOfOrdersBy(Long customerId) {
        Customer customer = customerService.getCustomerBy(customerId);
        Double totalAmount = orderRepository.findTotalAmountOfOrdersBy(customerId);
        return new CustomerOrdersReportResponse(customer, totalAmount);
    }

    @Override
    public MostToppingsReportResponse getMostUsedTopping() {
        return null;
    }
}
