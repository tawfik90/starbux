package com.bestseller.starbux.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_auto")
    private Long id;

    @Column(unique = true)
    private String username;

    public Customer(String username) {
        this.username = username;
    }
}
