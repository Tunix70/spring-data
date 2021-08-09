package com.springCrudV2.demo.dao;

import com.springCrudV2.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findFirstByNameContains(String name);

    boolean existsById(Long id);

    boolean existsByName(String name);
}