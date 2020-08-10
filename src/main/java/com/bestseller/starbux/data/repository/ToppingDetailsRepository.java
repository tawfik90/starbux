package com.bestseller.starbux.data.repository;

import com.bestseller.starbux.data.entity.ToppingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingDetailsRepository extends JpaRepository<ToppingDetails, Long> {
}
