package com.bestseller.starbux.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "online_order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_id_auto")
    private Long id;
    @Column(name = "total_amount")
    private Double orderAmount;
    @Column(name = "discount_amount")
    private Double discountAmount;
    @Column(name = "original_amount")
    private Double originalAmount;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;



    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    public Order(LocalDate creationDate, OrderStatus orderStatus) {
        this.orderAmount = 0.00;
        this.creationDate = creationDate;
        this.orderStatus = orderStatus;

    }
}
