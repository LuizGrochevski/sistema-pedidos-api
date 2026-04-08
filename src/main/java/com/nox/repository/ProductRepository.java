package com.nox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nox.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}