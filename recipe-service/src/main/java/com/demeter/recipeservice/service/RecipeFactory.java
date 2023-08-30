package com.demeter.recipeservice.service;

import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.model.Comment;
import com.demeter.recipeservice.model.Ingredient;
import com.demeter.recipeservice.model.Recipe;
import com.demeter.recipeservice.model.Step;
import org.springframework.stereotype.Component;

@Component
public class RecipeFactory {

    static Step dtoToEntity(StepRequest source){
        return Step.builder()
                .text(source.getText())
                .number(source.getNumber())
                .build();
    }
    static Step dtoToEntity(StepResponse source){
        return Step.builder()
                .id(source.getId())
                .text(source.getText())
                .number(source.getNumber())
                .build();
    }

    static StepResponse dtoToEntity(Step source){
        return StepResponse.builder()
                .id(source.getId())
                .text(source.getText())
                .number(source.getNumber())
                .build();
    }

    static Ingredient dtoToEntity(IngredientRequest source){
        return Ingredient.builder()
                .name(source.getName())
                .quantity(source.getQuantity())
                .measuringUnites(source.getMeasuringUnites())
                .build();
    }

    static Ingredient dtoToEntity(IngredientResponse source){
        return Ingredient.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .measuringUnites(source.getMeasuringUnites())
                .build();
    }

    static IngredientResponse dtoToEntity(Ingredient source){
        return IngredientResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .measuringUnites(source.getMeasuringUnites())
                .build();
    }

    static Recipe dtoToEntity(RecipeRequest source){
        Recipe recipe = Recipe.builder()
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod().stream()
                        .map(step->dtoToEntity(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredientRequest->dtoToEntity(ingredientRequest))
                        .toList())
                .photo(source.getPhoto())
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .build();
        return recipe;
    }
    static Recipe editRecipe(RecipeResponse source){
        Recipe recipe = Recipe.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod().stream()
                        .map(step->dtoToEntity(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredientResponse->dtoToEntity(ingredientResponse))
                        .toList())
                .photo(source.getPhoto())
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .build();
        return recipe;
    }
    static RecipeResponse dtoToEntity(Recipe source) {
        return RecipeResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .method(source.getMethod().stream()
                        .map(step -> dtoToEntity(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredient -> dtoToEntity(ingredient))
                        .toList())
                .photo(source.getPhoto())
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .commentResponseList(source.getComments().stream()
                        .map(comment -> entityToDTO(comment))
                        .toList())
                .build();
    }

    public static Comment dtoToEntity(CommentRequest source) {
        return Comment.builder()
                .author(source.getAuthor())
                .text(source.getText())
                .disLikeCount(source.getDisLikeCount())
                .likeCount(source.getLikeCount())
                .build();
    }
    public static CommentResponse entityToDTO(Comment source){
        return CommentResponse.builder()
                .id(source.getId())
                .author(source.getAuthor())
                .text(source.getText())
                .disLikeCount(source.getDisLikeCount())
                .likeCount(source.getLikeCount())
                .build();
    }

}
