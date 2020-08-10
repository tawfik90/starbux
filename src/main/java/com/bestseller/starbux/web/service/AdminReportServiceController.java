package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.CustomerOrdersReportResponse;
import com.bestseller.starbux.business.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/reports")
public class AdminReportServiceController {

    final private ReportService reportService;

    public AdminReportServiceController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/customers/{customerId}/orders-amount")
    public ResponseEntity<CustomerOrdersReportResponse> getTotalOrderAmount(@PathVariable Long customerId) {
        return ResponseEntity.ok(reportService.getTotalAmountOfOrdersBy(customerId));
    }

}
