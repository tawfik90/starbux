package com.bestseller.starbux.business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@ToString
public class OrderDrinkToppingRequest {

    private final List<Integer> toppingIds;

}
