package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipes  WHERE sketch = 'false' ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM recipes WHERE author_sub = :usersub AND sketch = 'false' ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAllbySub(Pageable pageable, @Param("usersub") String usersub);
    @Query("SELECT r FROM Recipe r WHERE r.authorSub = :usersub AND r.sketch = true ORDER BY r.id DESC")
    Page<Recipe> findAllSketchbySub(Pageable pageable, @Param("usersub") String usersub);
    @Query(value = "SELECT * FROM recipes WHERE id IN :likedRecipeIdList  AND sketch = 'false' ORDER BY id DESC", nativeQuery = true)
    Page<Recipe> findAllByIdIn(Pageable pageable, @Param("likedRecipeIdList") List<Integer> likedRecipeIdList);
}
