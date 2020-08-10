package com.bestseller.starbux.data.repository;

import com.bestseller.starbux.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select sum (a.orderAmount) from Order a where a.customer.id = :customerId ")
    Double findTotalAmountOfOrdersBy(Long customerId);

}
