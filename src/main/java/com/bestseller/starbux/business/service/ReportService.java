package com.bestseller.starbux.business.service;

import com.bestseller.starbux.business.domain.CustomerOrdersReportResponse;
import com.bestseller.starbux.business.domain.MostToppingsReportResponse;

import java.util.List;

public interface ReportService {

    CustomerOrdersReportResponse getTotalAmountOfOrdersBy(Long customerId);

    MostToppingsReportResponse getMostUsedTopping();

}
