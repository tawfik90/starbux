package com.bestseller.starbux.business.domain;


import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class CartResponse {

    private final OrderDetails orderDetails;

    private final Order cart;


}
