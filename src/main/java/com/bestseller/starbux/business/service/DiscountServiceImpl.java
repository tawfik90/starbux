package com.bestseller.starbux.business.service;

import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderDetails;
import com.bestseller.starbux.data.entity.ToppingDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscountServiceImpl implements DiscountService {


    @Override
    public Double getDiscountAmount(Order order) {
        log.info("Entered getDiscountAmount(Order) method");
        double amountOfDiscount = 0.00;
        if (order.getOrderAmount() > 12) {
           amountOfDiscount = getAmountOfDiscountIfMoreThan12Euro(order.getOrderAmount());
        }
        if (order.getOrderDetails().size() >= 3) {
            double smallestAmount = getAmountOfDiscountIfMoreThan3Drinks(order);
            if (smallestAmount > amountOfDiscount) {
                return smallestAmount;
            }
        }
        return amountOfDiscount;
    }

    private Double getAmountOfDiscountIfMoreThan12Euro(Double amount) {
        log.info("Entered getAmountOfDiscountIfMoreThan12Euro(Double) method");
        return amount * 25/100;
    }

    private Double getAmountOfDiscountIfMoreThan3Drinks(Order order) {
        log.info("Entered getAmountOfDiscountIfMoreThan3Drinks(Order) method");
        double smallestAmount = 00.00d;
        for (OrderDetails orderDetails : order.getOrderDetails()) {
            double amount = orderDetails.getDrink().getPrice();
            for (ToppingDetails toppingDetails : orderDetails.getToppingDetails()) {
                amount += toppingDetails.getTopping().getPrice();
            }
            if (smallestAmount == 0) {
                smallestAmount = amount;
            }
            else if(amount < smallestAmount) {
                smallestAmount = amount;
            }
        }
        return smallestAmount;
    }
}
