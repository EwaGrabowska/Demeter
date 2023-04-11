package com.demeter.postrecipeservice.repository;

import com.demeter.postrecipeservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
