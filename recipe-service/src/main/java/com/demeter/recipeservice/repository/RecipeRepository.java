package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipes ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAll(Pageable pageable);

}
