package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}
