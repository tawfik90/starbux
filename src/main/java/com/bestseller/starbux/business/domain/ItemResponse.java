package com.bestseller.starbux.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ItemResponse {

    private Integer id;
    private String name;
    private Double price;
    private List<ItemResponse> toppings;
}
