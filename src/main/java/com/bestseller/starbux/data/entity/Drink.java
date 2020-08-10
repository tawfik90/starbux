package com.bestseller.starbux.data.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Drink extends Item{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "drink_id_auto")
    private Integer id;

    public Drink(String name, Double price) {
        super(name, price);
    }

    public Drink(String name, Double price, Integer id) {
        super(name, price);
        this.id = id;
    }
}
