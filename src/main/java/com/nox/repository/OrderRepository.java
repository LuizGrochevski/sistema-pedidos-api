package com.nox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nox.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}