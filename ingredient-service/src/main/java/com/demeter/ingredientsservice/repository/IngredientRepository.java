package com.demeter.ingredientsservice.repository;

import com.demeter.ingredientsservice.model.Ingredient;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findByNameIn(List<String> name);
}
