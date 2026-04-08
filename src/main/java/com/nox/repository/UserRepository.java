package com.nox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nox.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}