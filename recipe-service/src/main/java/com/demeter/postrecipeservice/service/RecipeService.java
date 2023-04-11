package com.demeter.postrecipeservice.service;

import com.demeter.postrecipeservice.dto.IngredientSubstituteResponse;
import com.demeter.postrecipeservice.dto.RecipeRequest;
import com.demeter.postrecipeservice.dto.RecipeResponse;
import com.demeter.postrecipeservice.model.Recipe;
import com.demeter.postrecipeservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final WebClient webClient;


    public void createRecipe(final RecipeRequest source) {
        Recipe recipe = RecipeFactory.dtoToEntity(source);

        recipe = recipeRepository.save(recipe);
        log.info("Product {} is saved", recipe.getId());
    }

    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream().map(recipe-> RecipeFactory.entityToDto(recipe)).toList();
    }

    private List<IngredientSubstituteResponse> getAllSubstitutesByIngredientsName(List<String> ingredientsName){
        IngredientSubstituteResponse[] substitutes = webClient.get()
                .uri("http://localhost:8081/api/ingredient/substitutes", uriBuilder -> uriBuilder.queryParam("name", ingredientsName).build())
                .retrieve()
                .bodyToMono(IngredientSubstituteResponse[].class)
                .block();
        List<IngredientSubstituteResponse> ingredientSubstituteResponseList = Arrays.stream(substitutes)
                .filter(substitus -> !substitus.getSubstitute().isEmpty())
                .toList();
        return ingredientSubstituteResponseList;
    }


    public List<IngredientSubstituteResponse> getAllSubstitutes(String id) {
        List<String> ingredientsName = recipeRepository.findById(Long.parseLong(id))
                .map(recipe -> recipe.getIngredientList().stream()
                        .map(ingredient -> ingredient.getName())
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("Recipe doesnt exist."));
        return getAllSubstitutesByIngredientsName(ingredientsName);
    }
}
