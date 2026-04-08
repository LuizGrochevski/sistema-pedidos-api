package com.nox.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nox.model.Product;
import com.nox.service.ProductService;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductService productService) {
        return args -> {
            productService.save(new Product(101, "Notebook", 2500.0));
            productService.save(new Product(102, "Smartphone", 1500.0));
            productService.save(new Product(103, "Headphones", 300.0));
        };
    }
}