package com.relatospapel.bookspayments.repository;

import com.relatospapel.bookspayments.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
