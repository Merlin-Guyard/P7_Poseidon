package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
    Optional<RuleName> findByName(String name);
}
