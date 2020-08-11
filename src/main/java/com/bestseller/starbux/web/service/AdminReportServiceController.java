package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.CustomerOrdersReportResponse;
import com.bestseller.starbux.business.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/reports")
public class AdminReportServiceController {

    final private ReportService reportService;

    public AdminReportServiceController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/customers/{customerId}/orders-amount")
    public ResponseEntity<CustomerOrdersReportResponse> getTotalOrderAmount(@PathVariable Long customerId) {
        log.info("Entered /admin/reports/customers/{}/orders-amount end-point", customerId);
        return ResponseEntity.ok(reportService.getTotalAmountOfOrdersBy(customerId));
    }

}
