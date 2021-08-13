package com.springCrudV2.demo.dao;

import com.springCrudV2.demo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByName(String name);
}
