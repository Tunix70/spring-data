package com.springCrudV2.demo.dao;

import com.springCrudV2.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByName(String name);

}
