package com.demeter.recipeservice.client.repository;

import com.demeter.recipeservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
