package com.nox.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nox.model.Product;
import com.nox.service.ProductService;
import com.nox.model.User;
import com.nox.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductService productService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            productService.save(new Product(101, "Notebook", 2500.0));
            productService.save(new Product(102, "Smartphone", 1500.0));
            productService.save(new Product(103, "Headphones", 300.0));
        
            if(userRepository.findByEmailOrName("luiz@email.com", "luiz").isEmpty()){
                userRepository.save(new User(1, "luiz", "luiz.@email.com", passwordEncoder.encode("123")));
            }
        
        };
    }
}