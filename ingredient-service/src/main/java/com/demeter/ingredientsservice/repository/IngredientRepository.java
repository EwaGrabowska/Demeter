package com.demeter.ingredientsservice.repository;

import com.demeter.ingredientsservice.model.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    List<Ingredient> findByNameIn(List<String> name);
}
