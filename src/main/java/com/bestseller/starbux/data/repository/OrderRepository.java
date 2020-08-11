package com.bestseller.starbux.data.repository;

import com.bestseller.starbux.data.entity.Order;
import com.bestseller.starbux.data.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select sum (a.orderAmount) from Order a where a.customer.id = :customerId ")
    Double findTotalAmountOfOrdersBy(Long customerId);

    Optional<Order> findByIdAndAndOrderStatus(Long id, OrderStatus orderStatus);

}
