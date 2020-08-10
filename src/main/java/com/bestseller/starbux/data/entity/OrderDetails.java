package com.bestseller.starbux.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_details_id_auto")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @OneToMany(mappedBy = "orderDetails")
    private List<ToppingDetails> toppingDetails;

    public OrderDetails(Order order, Drink drink) {
        this.order = order;
        this.drink = drink;
    }
}
