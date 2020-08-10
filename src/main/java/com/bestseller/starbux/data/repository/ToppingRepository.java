package com.bestseller.starbux.data.repository;

import com.bestseller.starbux.data.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Integer> {
}
