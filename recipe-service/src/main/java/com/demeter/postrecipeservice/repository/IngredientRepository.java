package com.demeter.postrecipeservice.repository;

import com.demeter.postrecipeservice.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
