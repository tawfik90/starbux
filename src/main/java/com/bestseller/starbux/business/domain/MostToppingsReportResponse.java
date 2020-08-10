package com.bestseller.starbux.business.domain;

import com.bestseller.starbux.data.entity.Topping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class MostToppingsReportResponse {

    final private Topping mostUsedTopping;


}
