package com.bestseller.starbux.business.service;

import com.bestseller.starbux.data.entity.Order;

public interface DiscountService {

    Double getDiscountAmount(Order order);

}
