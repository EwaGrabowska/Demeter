package com.demeter.recipeservice.service;

import com.demeter.recipeservice.dto.*;
import com.demeter.recipeservice.model.*;
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

    static StepResponse entityToDTO(Step source){
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
                .measuringUnites(source.getMeasuringUnits())
                .build();
    }

    static Ingredient dtoToEntity(IngredientResponse source){
        return Ingredient.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .measuringUnites(source.getMeasuringUnits())
                .build();
    }

    static IngredientResponse entityToDTO(Ingredient source){
        return IngredientResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .quantity(source.getQuantity())
                .measuringUnits(source.getMeasuringUnits())
                .build();
    }

    static Recipe dtoToEntity(RecipeRequest source){
        Recipe recipe = Recipe.builder()
                .name(source.getName())
                .author(source.getAuthor())
                .authorSub(source.getAuthorSub())
                .method(source.getMethod().stream()
                        .map(step-> dtoToEntity(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredientRequest-> dtoToEntity(ingredientRequest))
                        .toList())
                .photo(source.getPhoto())
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .comments(source.getComments().stream()
                        .map(commentRequest -> dtoToEntity(commentRequest))
                        .toList())
                .sketch(source.isSketch())
                .build();
        recipe.setLastModification();
        return recipe;
    }
    static Recipe editRecipe(RecipeResponse source){
        Recipe recipe = Recipe.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .authorSub(source.getAuthorSub())
                .method(source.getMethod().stream()
                        .map(step-> dtoToEntity(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredientResponse-> dtoToEntity(ingredientResponse))
                        .toList())
                .photo(dtoToEntity(source.getPhoto()))
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .sketch(source.isSketch())
                .comments(source.getCommentResponseList().stream()
                        .map(comment-> dtoToEntity(comment))
                        .toList())
                .build();
        recipe.setLastModification();
        return recipe;
    }
    static RecipeResponse entityToDTO(Recipe source) {
        return RecipeResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .author(source.getAuthor())
                .authorSub(source.getAuthorSub())
                .method(source.getMethod().stream()
                        .map(step -> entityToDTO(step))
                        .toList())
                .preparationTime(source.getPreparationTime())
                .cookingTime(source.getCookingTime())
                .restingTime(source.getRestingTime())
                .servingSize(source.getServingSize())
                .ingredientList(source.getIngredientList().stream()
                        .map(ingredient -> entityToDTO(ingredient))
                        .toList())
                .photo(entityToDTO(source.getPhoto()))
                .likes(source.getLikes())
                .disLikes(source.getDisLikes())
                .commentResponseList(source.getComments().stream()
                        .map(comment -> entityToDTO(comment))
                        .toList())
                .sketch(source.isSketch())
                .lastModification(source.getLastModification())
                .build();
    }

    public static Comment dtoToEntity(CommentRequest source) {
        return Comment.builder()
                .author(source.getAuthor())
                .text(source.getText())
                .build();
    }
    public static CommentResponse entityToDTO(Comment source){
        return CommentResponse.builder()
                .id(source.getId())
                .author(source.getAuthor())
                .text(source.getText())
                .build();
    }
    public static Comment dtoToEntity(CommentResponse source){
        return Comment.builder()
                .id(source.getId())
                .author(source.getAuthor())
                .text(source.getText())
                .build();
    }

    public static PhotoResponse entityToDTO(Photo source){
        return PhotoResponse.builder()
                .id(source.getId())
                .photoUrl(source.getPhotoUrl())
                .build();
    }

    public static Photo dtoToEntity(PhotoResponse source){
        return Photo.builder()
                .id(source.getId())
                .photoUrl(source.getPhotoUrl())
                .build();
    }
}
