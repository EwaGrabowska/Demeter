package com.demeter.postrecipeservice.service;

import brave.Span;
import brave.Tracer;
import com.demeter.postrecipeservice.client.IngredientClient;
import com.demeter.postrecipeservice.dto.IngredientSubstituteResponse;
import com.demeter.postrecipeservice.dto.RecipeRequest;
import com.demeter.postrecipeservice.dto.RecipeResponse;
import com.demeter.postrecipeservice.model.Recipe;
import com.demeter.postrecipeservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RecipeService {

    private final IngredientClient ingredientClient;
    private final RecipeRepository recipeRepository;
    private final Tracer tracer;


    public void createRecipe(final RecipeRequest source) {
        Recipe recipe = RecipeFactory.dtoToEntity(source);

        recipe = recipeRepository.save(recipe);
        log.info("Recipe {} is saved", recipe.getId());
    }

    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe-> RecipeFactory.entityToDto(recipe)).toList();
    }

    private List<IngredientSubstituteResponse> getAllSubstitutesByIngredientsName(List<String> ingredientsName){
        List<IngredientSubstituteResponse> substituteResponses = ingredientClient.hasSubstitute(ingredientsName).stream()
                .filter(substitus -> !substitus.getSubstitute().isEmpty())
                .toList();
        return substituteResponses;
    }

    public List<IngredientSubstituteResponse> getAllSubstitutes(String id) {
        Span ingredientServiceLookup = tracer.nextSpan().name("IngredientServiceLookup");

        try (Tracer.SpanInScope isLookup = tracer.withSpanInScope(ingredientServiceLookup.start())){
            List<String> ingredientsName = recipeRepository.findById(Long.parseLong(id))
                    .map(recipe -> recipe.getIngredientList().stream()
                            .map(ingredient -> ingredient.getName())
                            .toList())
                    .orElseThrow(() -> new IllegalArgumentException("Recipe doesnt exist."));
            return getAllSubstitutesByIngredientsName(ingredientsName);
        }finally {
            ingredientServiceLookup.flush();
        }

    }
}
