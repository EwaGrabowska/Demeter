package com.demeter.recipeservice.client.service;

import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.model.Ingredient;
import com.demeter.recipeservice.model.MeasuringUnites;
import com.demeter.recipeservice.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeFactory {

    static MeasuringUnites dtoToEntity(final MeasuringUnitesRequest source) {
        return MeasuringUnites.builder()
                .name(source.getName())
                .build();
    }


    static MeasuringUnitesResponse entityToDto(MeasuringUnites source){
        return MeasuringUnitesResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }

    static Ingredient dtoToEntity(IngredientRequest source){
        return Ingredient.builder()
                .name(source.getName())
                .quantity(source.getQuantity())
                .build();
    }

    static Ingredient dtoToEntity(IngredientResponse source){
        return Ingredient.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .build();
    }

    static IngredientResponse entityToDto(Ingredient source){
        return IngredientResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .build();
    }

    static Recipe dtoToEntity(RecipeRequest source){
        Recipe recipe = Recipe.builder()
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(recipeRequest->dtoToEntity(recipeRequest))
                        .toList())
                .build();
        return recipe;
    }
    static Recipe editRecipe(RecipeResponse source){
        Recipe recipe = Recipe.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredientResponse->dtoToEntity(ingredientResponse))
                        .toList())
                .build();
        return recipe;
    }
    static RecipeResponse entityToDto(Recipe source) {
        return RecipeResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredient -> entityToDto(ingredient))
                        .toList())
                .build();
    }
}
