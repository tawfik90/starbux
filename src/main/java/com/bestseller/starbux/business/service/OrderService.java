package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CartResponse;
import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.data.entity.Order;

public interface OrderService {

    Order create();

    CartResponse addDrink(Long orderId, OrderDetailsRequest orderDetailsRequest);

    Order getOrderBy(Long id);

    Order finalizeOrder(Long orderId, CustomerRequest customerRequest);

}
