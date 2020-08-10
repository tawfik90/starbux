package com.bestseller.starbux.data.repository;

import com.bestseller.starbux.data.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query("select sum (a.drink.price) from OrderDetails a where a.order.id = :orderId")
    public Double getTotalAmountForDrinks(Long orderId);

    @Query("select sum (b.topping.price) from OrderDetails a join a.toppingDetails b where a.order.id = :orderId")
    public Double getTotalAmountForToppings(Long orderId);

}
