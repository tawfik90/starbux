package com.bestseller.starbux.business.domain;

import com.bestseller.starbux.data.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class CustomerOrdersReportResponse {

    final private Customer customer;
    final private Double AmountOfOrders;


}
