package com.bestseller.starbux.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class ToppingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "drink_toppings_id_auto")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "topping_id", referencedColumnName = "id")
    private Topping topping;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderDetails orderDetails;

    public ToppingDetails(Topping topping, OrderDetails orderDetails) {
        this.topping = topping;
        this.orderDetails = orderDetails;
    }
}
