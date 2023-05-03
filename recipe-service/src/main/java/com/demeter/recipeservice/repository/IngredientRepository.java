package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
