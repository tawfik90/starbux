package com.bestseller.starbux.business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@ToString
public class OrderDetailsRequest {

    @NotNull
    private final Integer drinkId;
    private List<Integer> toppingIds;

}
