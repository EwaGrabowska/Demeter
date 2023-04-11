package com.demeter.ingredientsservice.service;

import com.demeter.ingredientsservice.dto.IngredientRequest;
import com.demeter.ingredientsservice.dto.IngredientResponse;
import com.demeter.ingredientsservice.dto.IngredientSubstituteResponse;
import com.demeter.ingredientsservice.model.Ingredient;
import com.demeter.ingredientsservice.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public void createIngredient(IngredientRequest ingredientRequest) {
        Ingredient ingredient = Ingredient.builder()
                .name(ingredientRequest.getName())
                .description(ingredientRequest.getDescription())
                .build();

        ingredientRepository.save(ingredient);
        log.info("Ingredient {} is saved", ingredient.getId());
    }

    public List<IngredientResponse> getAllIngredients() {
        List<Ingredient> products = ingredientRepository.findAll();

        return products.stream().map(this::mapToIngredientResponse).toList();
    }


    private IngredientResponse mapToIngredientResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .substitute(ingredient.getSubstitute())
                .taste(ingredient.getTaste())
                .description(ingredient.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public List<IngredientSubstituteResponse> getAllSubstitutes(List<String> ingredientsName) {
        return ingredientRepository.findByNameIn(ingredientsName).stream()
                .map(ingredient -> IngredientSubstituteResponse.builder()
                        .name(ingredient.getName())
                        .substitute(ingredient.getSubstitute())
                        .build()
                ).toList();
    }
}
