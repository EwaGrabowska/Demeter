package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipes ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM recipes WHERE author_sub = :usersub ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAllbySub(Pageable pageable, @Param("usersub") String usersub);
    @Query(value = "SELECT * FROM recipes WHERE id IN :likedRecipeIdList ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAllByIdIn(Pageable pageable, @Param("likedRecipeIdList") List<Integer> likedRecipeIdList);
}
